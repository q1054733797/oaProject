package com.project.oa.base.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: Vacate
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/21 16:44
 * @Version: 1.0
 */
public class Vacate {
    private String id;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startTime;
    private String dayNum;
    private String reason;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date applyTime;
    private String processStatus;
    private String processInstId;
    private String applyUserId;
    private String userName;

    public Vacate(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }
}
