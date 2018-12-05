package com.ab.cmfz.controller;

import com.ab.cmfz.entity.Artical;
import com.ab.cmfz.service.ArticalService;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章
 */
@Controller
@RequestMapping
public class ArticalController {

    @Autowired
    ArticalService articalService;

    @RequestMapping("/index")
    public @ResponseBody
    void index() {

        IndexWriter indexWriter = null;

//            try {
//                //创建索引库  不存在创建该目录 作为索引库
//                Directory dir= FSDirectory.open(new File("E:/index"));
//                Analyzer analyzer = new IKAnalyzer();
//                //参数1 版本号  参数2 分词器
//                IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_44,analyzer);
//                //创建索引写入对象   参数1 索引库位置  参数2 索引写入对象的相关配置
//                indexWriter = new IndexWriter(dir,conf);
//                Document document = new Document();
//                document.add(new StringField("title", "分数高的文章", Field.Store.YES));
//                document.add(new StringField("author", "朱自清", Field.Store.YES));
//                document.add(new StringField("date", "2018-12-4", Field.Store.YES));
//                document.add(new TextField("content", "你站在这里不要动,我去给你买几个橘子", Field.Store.YES));
//                indexWriter.addDocument(document);
//                indexWriter.commit();
//                indexWriter.close();
//            } catch (IOException e) {
//                try {
//                    indexWriter.rollback();
//                    indexWriter.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//
//                e.printStackTrace();
//            }

    }

    @RequestMapping("/index2")
    public @ResponseBody
    void index2() {
//        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
//        for (int i = 11; i < 12; i++) {
//
//            //Field.Store.YES会为当前列创建索引并且在元数据区中存储
//            //Field.Store.NO会为当前列创建索引但是不会在元数据区中存储
//            //应用场景
//            Document document = new Document();
//            document.add(new StringField("id", String.valueOf(i), Field.Store.YES));
//            document.add(new StringField("title", "背影", Field.Store.YES));
//            document.add(new StringField("author", "朱自清", Field.Store.YES));
//            document.add(new StringField("date", "2018-12-4", Field.Store.YES));
//            document.add(new TextField("content", "百知教育你站在这里不要动,我去给你买几个橘子" + i, Field.Store.YES));
//            try {
//                indexWriter.addDocument(document);
//            } catch (IOException e) {
//                LuceneUtil.rollback(indexWriter);
//                e.printStackTrace();
//            }
//        }
//        LuceneUtil.commit(indexWriter);


    }


    @RequestMapping("/queryIndex")
    public @ResponseBody
    Map queryIndex(String params) {
        List<Artical> articals = null;
        try {
            articals = articalService.queryIndex(params);
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("articals", articals);

        return map;
    }

    @RequestMapping("/addIndex")
    public @ResponseBody
    boolean addIndex(Artical artical, HttpServletRequest request, MultipartFile img) {

        String realPath = request.getSession().getServletContext().getRealPath("/");  //webapp当前项目的路径
        File file = new File(realPath + "/upload");
        if (!file.exists()) {
            file.mkdir();
        }

        String extension = FilenameUtils.getExtension(img.getOriginalFilename());
        String s = Long.toString(new Date().getTime());
        String newName = s + "." + extension;
        try {
            img.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String fileName = img.getOriginalFilename();
            //获取新文件名
            artical.setId(s);
            artical.setUrl(newName);
            articalService.addIndex(artical);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
