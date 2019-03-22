package com.jcet.springmvc.biz.serverinforecord.service.impl;

import com.jcet.springmvc.biz.serverinforecord.dao.IServerInfoRecordMapper;
import com.jcet.springmvc.biz.serverinforecord.domain.ServerInfoRecord;
import com.jcet.springmvc.biz.serverinforecord.service.IServerInfoRecordService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Service("ServerInfoRecordService")
public class ServerInfoRecordServiceImpl implements IServerInfoRecordService {

    @Resource
    private IServerInfoRecordMapper iServerInfoRecordMapper;

    @Override
    public int insertByRecord(ServerInfoRecord serverInfoRecord) {
        return this.iServerInfoRecordMapper.insertByRecord(serverInfoRecord);
    }

    @Override
    public List<ServerInfoRecord> SelectByMap(Map map) {
        return this.iServerInfoRecordMapper.SelectByMap(map);
    }
}