package com.jcet.springmvc.biz.serverinforecord.domain;

public class ServerInfoRecord {

    private String SYSID;
    private String IP;
    private String CPU;
    private String MEMTOTAL;
    private String MEMUSED;
    private String MEMREMAIN;
    private String MEMCACHE;
    private String DISKTOTAL;
    private String DISKUSED;
    private String DISKREMAIN;
    private String CREATED_BY;
    private String CREATED_DATE;

    public String getSYSID() {
        return SYSID;
    }

    public void setSYSID(String SYSID) {
        this.SYSID = SYSID;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getMEMTOTAL() {
        return MEMTOTAL;
    }

    public void setMEMTOTOAL(String MEMTOTOAL) {
        this.MEMTOTAL = MEMTOTOAL;
    }

    public String getMEMUSED() {
        return MEMUSED;
    }

    public void setMEMUSED(String MEMUSED) {
        this.MEMUSED = MEMUSED;
    }

    public String getMEMREMAIN() {
        return MEMREMAIN;
    }

    public void setMEMREMAIN(String MEMREMAIN) {
        this.MEMREMAIN = MEMREMAIN;
    }

    public String getMEMCACHE() {
        return MEMCACHE;
    }

    public void setMEMCACHE(String MEMCACHE) {
        this.MEMCACHE = MEMCACHE;
    }

    public String getDISKTOTAL() {
        return DISKTOTAL;
    }

    public void setDISKTOTAL(String DISKTOTAL) {
        this.DISKTOTAL = DISKTOTAL;
    }

    public String getDISKUSED() {
        return DISKUSED;
    }

    public void setDISKUSED(String DISKUSED) {
        this.DISKUSED = DISKUSED;
    }

    public String getDISKREMAIN() {
        return DISKREMAIN;
    }

    public void setDISKREMAIN(String DISKREMAIN) {
        this.DISKREMAIN = DISKREMAIN;
    }

    public String getCREATED_BY() {
        return CREATED_BY;
    }

    public void setCREATED_BY(String CREATED_BY) {
        this.CREATED_BY = CREATED_BY;
    }

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }
}