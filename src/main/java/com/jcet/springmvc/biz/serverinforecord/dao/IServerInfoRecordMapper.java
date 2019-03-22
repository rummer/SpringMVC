package com.jcet.springmvc.biz.serverinforecord.dao;

import com.jcet.springmvc.biz.serverinforecord.domain.ServerInfoRecord;

import java.util.List;
import java.util.Map;

public interface IServerInfoRecordMapper {
    int insertByRecord(ServerInfoRecord serverInfoRecord);

    List<ServerInfoRecord> SelectByMap(Map map);
}
