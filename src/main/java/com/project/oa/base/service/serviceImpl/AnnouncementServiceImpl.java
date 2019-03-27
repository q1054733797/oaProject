package com.project.oa.base.service.serviceImpl;

import com.project.oa.base.bean.Announcement;
import com.project.oa.base.mapper.AnnouncementMapper;
import com.project.oa.base.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: AnnouncementServiceImpl
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/27 10:37
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AnnouncementServiceImpl implements IAnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getReleaseAnnouncement() {
        return announcementMapper.getReleaseAnnouncement();
    }

    @Override
    public int releaseAnnouncement(Announcement announcement) {
        return announcementMapper.releaseAnnouncement(announcement);
    }

    @Override
    public int deleteAnnouncement(Announcement announcement) {
        return announcementMapper.deleteAnnouncement(announcement);
    }

    @Override
    public int updateAnnouncement(Announcement announcement) {
        return announcementMapper.updateAnnouncement(announcement);
    }

    @Override
    public int addAnnouncement(Announcement announcement) {
        return announcementMapper.addAnnouncement(announcement);
    }

    @Override
    public List<Announcement> getAnnouncementByUserId(Announcement announcement) {
        return announcementMapper.getAnnouncementByUserId(announcement);
    }
}
