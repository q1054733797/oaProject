package com.project.oa.base.controller;

import com.project.oa.base.bean.Announcement;
import com.project.oa.base.bean.User;
import com.project.oa.base.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: AnnouncementController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/27 10:39
 * @Version: 1.0
 */
@Controller
public class AnnouncementController {
    @Autowired
    private IAnnouncementService announcementService;

    @RequestMapping("getReleaseAnnouncement")
    @ResponseBody
    public List<Announcement> getReleaseAnnouncement(){
        return announcementService.getReleaseAnnouncement();
    }

    @RequestMapping("releaseAnnouncement")
    @ResponseBody
    public String releaseAnnouncement(@RequestBody List<Announcement> announcements){
        String result = "ok";
        try {
            for (Announcement announcement : announcements) {
                if(announcement.getReleaseTime() == null){
                    announcement.setReleaseTime(new Date());
                    announcementService.releaseAnnouncement(announcement);
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("deleteAnnouncement")
    @ResponseBody
    public String deleteAnnouncement(@RequestBody List<Announcement> announcements){
        String result = "ok";
        try {
            for (Announcement announcement : announcements) {
                if(announcement.getReleaseTime() == null){
                    announcementService.deleteAnnouncement(announcement);
                }
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("updateAnnouncement")
    @ResponseBody
    public String updateAnnouncement(Announcement announcement){
        String result = "ok";
        try {
            announcementService.updateAnnouncement(announcement);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("addAnnouncement")
    @ResponseBody
    public String addAnnouncement(Announcement announcement, HttpServletRequest request){
        String result = "ok";
        User user = (User) request.getSession().getAttribute("user");
        announcement.setUserId(user.getId());
        try {
            announcementService.addAnnouncement(announcement);
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @RequestMapping("getAnnouncementByUserId")
    @ResponseBody
    public List<Announcement> getAnnouncementByUserId(Announcement announcement, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        announcement.setUserId(user.getId());
        return announcementService.getAnnouncementByUserId(announcement);
    }
}
