package com.project.oa.base.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ClassName: User
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/8 15:05
 * @Version: 1.0
 */
public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private String status;
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date birthday;
    private String cardType;
    private String cardCode;
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date inDate;
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date outDate;
    private String phone;
    private String orgId;
    private String roleId;
    private String ownRole;

    public User(){

    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOwnRole() {
        return ownRole;
    }

    public void setOwnRole(String ownRole) {
        this.ownRole = ownRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
