package com.project.oa.base.mapper;

import com.project.oa.base.bean.Announcement;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @ClassName: AnnouncementMapper
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/27 10:28
 * @Version: 1.0
 */
public interface AnnouncementMapper {
    @Select("select a.*,b.name userName from t_announcement a " +
            "left join t_user b on a.userId = b.id " +
            "where releaseTime is not null and endTime >= current_Date()")
    List<Announcement> getReleaseAnnouncement();

    @Update("update t_announcement " +
            "set releaseTime = #{releaseTime} " +
            "where id = #{id}")
    int releaseAnnouncement(Announcement announcement);

    @Delete("delete from t_announcement where id = #{id}")
    int deleteAnnouncement(Announcement announcement);

    @Update("update t_announcement set " +
            "title = #{title}," +
            "content = #{content}," +
            "startTime = #{startTime}," +
            "endTime = #{endTime} " +
            "where id = #{id}")
    int updateAnnouncement(Announcement announcement);

    @Insert("insert into t_announcement(title,content,userId,startTime,endTime) " +
            "values(#{title},#{content},#{userId},#{startTime},#{endTime})")
    int addAnnouncement(Announcement announcement);

    @SelectProvider(type = AnnouncementProvider.class,method = "getAnnouncementByUserId")
    List<Announcement> getAnnouncementByUserId(Announcement announcement);

    class AnnouncementProvider{
        public String getAnnouncementByUserId(Announcement announcement){
            SQL sql = new SQL();
            sql.FROM("t_announcement a");
            sql.SELECT("*");
            sql.LEFT_OUTER_JOIN("t_user b on a.userId = b.id");
            sql.SELECT("b.name userName");
            if(announcement.getUserId() != null){
                sql.WHERE("userId = #{userId}");
            }
            if(announcement.getTitle() != null){
                sql.WHERE("title like concat('%',#{title},'%')");
            }
            return sql.toString();
        }
    }
}
