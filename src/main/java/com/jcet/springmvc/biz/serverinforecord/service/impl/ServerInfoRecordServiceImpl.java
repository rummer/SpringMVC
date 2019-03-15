package com.jcet.springmvc.biz.serverinforecord.service.impl;

import com.jcet.springmvc.biz.serverinforecord.domain.ServerInfoRecord;
import com.jcet.springmvc.biz.serverinforecord.service.IServerInfoRecordService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
@Service("ServerInfoRecordService")
public class ServerInfoRecordServiceImpl implements IServerInfoRecordService {

    @Resource
    private IServerInfoRecordService iServerInfoRecordService;

    @Override
    public int insertByRecord(ServerInfoRecord serverInfoRecord) {
        return this.iServerInfoRecordService.insertByRecord(serverInfoRecord);
    }
}