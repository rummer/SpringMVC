package com.jcet.springmvc.biz.serverinfo.domain;

import java.util.Date;

public class ServerInfo {
    private String sysid;
    private String ip;
    private String loginname;
    private String loginpwd;
    private String remark;
    private String personcharge;
    private String created_by;
    private Date created_date;
    private String flag;

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid == null?null:sysid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPersoncharge() {
        return personcharge;
    }

    public void setPersoncharge(String personcharge) {
        this.personcharge = personcharge;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getFlag() { return flag; }

    public void setFlag(String flag) { this.flag = flag; }
}