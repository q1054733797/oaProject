package com.project.oa.base.service;

import com.project.oa.base.bean.Announcement;

import java.util.List;

/**
 * @ClassName: IAnnouncementService
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/27 10:36
 * @Version: 1.0
 */
public interface IAnnouncementService {
    List<Announcement> getAnnouncementByUserId(Announcement announcement);
    int addAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncement(Announcement announcement);
    int releaseAnnouncement(Announcement announcement);
    List<Announcement> getReleaseAnnouncement();
}
