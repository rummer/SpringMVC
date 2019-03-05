package com.jcet.springmvc.biz.serverinfo.dao;

import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;

import java.util.List;
import java.util.Map;

public interface IServerInfoMapper {
    public List<ServerInfo> searchByMap(Map map);
}
