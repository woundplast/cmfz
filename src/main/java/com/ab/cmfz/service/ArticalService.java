package com.ab.cmfz.service;

import com.ab.cmfz.entity.Artical;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.util.List;

/**
 * 文章
 */
public interface ArticalService {

    List<Artical> queryIndex(String params) throws InvalidTokenOffsetsException;

    void addIndex(Artical artical);

    void deleteIndex(String params);

    void updateInde(Artical artical);

}
