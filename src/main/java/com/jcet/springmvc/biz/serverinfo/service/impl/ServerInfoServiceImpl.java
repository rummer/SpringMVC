package com.jcet.springmvc.biz.serverinfo.service.impl;

import com.jcet.springmvc.biz.serverinfo.dao.IServerInfoMapper;
import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import com.jcet.springmvc.biz.serverinfo.service.IServerInfoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Service("ServerInfoService")
public class ServerInfoServiceImpl implements IServerInfoService {

    @Resource
    private IServerInfoMapper IServerInfoMapper;

    @Override
    public List<ServerInfo> searchByMap(Map map) {
        return this.IServerInfoMapper.searchByMap(map);
    }

    @Override
    public int updateByPrimaryKey(ServerInfo serverInfo) {
        return this.IServerInfoMapper.updateByPrimaryKey(serverInfo);
    }

    @Override
    public int insertBySelective(ServerInfo serverInfo) {
        return this.IServerInfoMapper.insertBySelective(serverInfo);
    }
}