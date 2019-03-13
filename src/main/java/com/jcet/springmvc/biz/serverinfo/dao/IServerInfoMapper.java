package com.jcet.springmvc.biz.serverinfo.dao;

import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import net.sf.jsqlparser.schema.Server;

import java.util.List;
import java.util.Map;

public interface IServerInfoMapper {
    List<ServerInfo> searchByMap(Map map);

    int updateByPrimaryKey(ServerInfo serverInfo);

    int insertBySelective(ServerInfo serverInfo);
}
