package com.jcet.springmvc.biz.serverinfo.service;

import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;

import java.util.List;
import java.util.Map;

public interface IServerInfoService {

    public List<ServerInfo> searchByMap(Map map);
}
