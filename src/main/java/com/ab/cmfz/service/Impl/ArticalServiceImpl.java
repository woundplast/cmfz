package com.ab.cmfz.service.Impl;

import com.ab.cmfz.entity.Artical;
import com.ab.cmfz.service.ArticalService;
import com.ab.cmfz.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("articalService")
@Transactional
public class ArticalServiceImpl implements ArticalService {

    @Override
    public List<Artical> queryIndex(String params) {
        List<Artical> articals = new ArrayList<>();
        String[] strings = {"title", "author", "content"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strings, new IKAnalyzer());
        Query query = null;
        int pageSize = 4;
        int pageNum = 1;
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        try {
            query = multiFieldQueryParser.parse(params);
            //制定高亮的样式
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            //提供查询关键词
            Scorer scorer = new QueryTermScorer(query);
            Highlighter highlighter = new Highlighter(formatter, scorer);

            TopDocs topDocs = indexSearcher.search(query, pageNum * pageSize);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = (pageNum - 1) * pageSize; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                float score = scoreDoc.score;
                ;
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Artical artical = getArtFromDoc(document);
                String title = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
                String author = highlighter.getBestFragment(new IKAnalyzer(), "author", document.get("author"));
                String content = highlighter.getBestFragment(new IKAnalyzer(), "content", document.get("content"));
                if (title != null) {
                    artical.setTitle(title);
                }
                if (author != null) {
                    artical.setAuthor(author);
                }
                if (content != null) {
                    artical.setContent(content);
                }

                articals.add(artical);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
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
        document.add(new TextField("id", artical.getId(), Field.Store.YES));
        document.add(new TextField("title", artical.getTitle(), Field.Store.YES));
        document.add(new TextField("author", artical.getAuthor(), Field.Store.YES));
        document.add(new TextField("content", artical.getContent(), Field.Store.YES));
        document.add(new TextField("date", artical.getDate(), Field.Store.YES));
        document.add(new TextField("url", artical.getUrl(), Field.Store.YES));
        return document;

    }
}
