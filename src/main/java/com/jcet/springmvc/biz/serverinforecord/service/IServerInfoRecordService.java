package com.jcet.springmvc.biz.serverinforecord.service;

import com.jcet.springmvc.biz.serverinforecord.domain.ServerInfoRecord;

import java.util.List;
import java.util.Map;

public interface IServerInfoRecordService {
    int insertByRecord(ServerInfoRecord serverInfoRecord);

    List<ServerInfoRecord> SelectByMap(Map map);
}
