package com.ab.cmfz.service.Impl;

import com.ab.cmfz.entity.Artical;
import com.ab.cmfz.service.ArticalService;
import com.ab.cmfz.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("articalService")
@Transactional
public class ArticalServiceImpl implements ArticalService {

    @Override
    public List<Artical> queryIndex(String params) {
        System.out.println("---");
        List<Artical> articals = new ArrayList<>();
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        System.out.println("---" + indexSearcher);

        try {
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("content", params)), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("--" + scoreDocs);
            for (int i = 0; i < scoreDocs.length; i++) {
                System.out.println(scoreDocs[i]);
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Artical artical = getArtFromDoc(document);
                articals.add(artical);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articals;
    }

    @Override
    public void addIndex(Artical artical) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromArt = getDocFromArt(artical);
        try {
            indexWriter.addDocument(docFromArt);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }


    }

    @Override
    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void updateInde(Artical artical) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromArt = getDocFromArt(artical);
        try {
            indexWriter.updateDocument(new Term("id", artical.getId()), docFromArt);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }

    }


    public Artical getArtFromDoc(Document document) {
        Artical artical = new Artical();
        artical.setId(document.get("id"));
        artical.setTitle(document.get("title"));
        artical.setAuthor(document.get("author"));
        artical.setContent(document.get("content"));
        artical.setDate(document.get("date"));
        artical.setUrl(document.get("url"));


        return artical;
    }

    public Document getDocFromArt(Artical artical) {
        Document document = new Document();
        document.add(new StringField("id", artical.getId(), Field.Store.YES));
        document.add(new StringField("title", artical.getTitle(), Field.Store.YES));
        document.add(new StringField("author", artical.getAuthor(), Field.Store.YES));
        document.add(new StringField("content", artical.getContent(), Field.Store.YES));
        document.add(new StringField("date", artical.getDate(), Field.Store.YES));
        document.add(new StringField("url", artical.getUrl(), Field.Store.YES));
        return document;

    }
}
