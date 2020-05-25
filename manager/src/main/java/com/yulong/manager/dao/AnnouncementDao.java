package com.yulong.manager.dao;

import com.yulong.manager.entity.Announcement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnnouncementDao {

    @Select("select * from announcement where status = 1")
    public List<Announcement> getAnnouncement();

}
