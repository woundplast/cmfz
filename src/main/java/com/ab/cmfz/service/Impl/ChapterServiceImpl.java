package com.ab.cmfz.service.Impl;

import com.ab.cmfz.dao.ChapterDao;
import com.ab.cmfz.entity.Chapter;
import com.ab.cmfz.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao chapterDao;

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.addChapter(chapter);

    }
}
