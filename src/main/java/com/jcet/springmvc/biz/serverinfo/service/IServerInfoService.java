package com.jcet.springmvc.biz.serverinfo.service;

import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;

import java.util.List;
import java.util.Map;

public interface IServerInfoService {

    List<ServerInfo> searchByMap(Map map);

    int updateByPrimaryKey(ServerInfo serverInfo);

    int insertBySelective(ServerInfo serverInfo);
}
