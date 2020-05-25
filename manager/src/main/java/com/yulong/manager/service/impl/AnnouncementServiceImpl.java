package com.yulong.manager.service.impl;

import com.yulong.manager.dao.AnnouncementDao;
import com.yulong.manager.entity.Announcement;
import com.yulong.manager.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    /**
     * 公告列表
     * @return
     */
    @Override
    public List<Announcement> getAnnouncement() {
        return announcementDao.getAnnouncement();
    }
}
