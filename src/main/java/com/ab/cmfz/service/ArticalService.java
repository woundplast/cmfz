package com.ab.cmfz.service;

import com.ab.cmfz.entity.Artical;

import java.util.List;

/**
 * 文章
 */
public interface ArticalService {

    List<Artical> queryIndex(String params);

    void addIndex(Artical artical);

    void deleteIndex(String params);

    void updateInde(Artical artical);

}
