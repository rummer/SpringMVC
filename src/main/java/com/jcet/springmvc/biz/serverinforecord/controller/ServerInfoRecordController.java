package com.jcet.springmvc.biz.serverinforecord.controller;


import com.alibaba.fastjson.JSON;
import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import com.jcet.springmvc.biz.serverinfo.service.IServerInfoService;
import com.jcet.springmvc.biz.serverinforecord.domain.ServerInfoRecord;
import com.jcet.springmvc.biz.serverinforecord.service.IServerInfoRecordService;
import com.jcet.springmvc.common.LinuxStateForShell;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jcet.springmvc.common.LinuxStateForShell.COMMANDS;

@Controller
@RequestMapping("/serverInfoRecord")
public class ServerInfoRecordController {

    private static Logger logger = Logger.getLogger(ServerInfoRecordController.class);

    @Resource(name="ServerInfoService")
    private IServerInfoService serverInfoService;

    @Resource(name="ServerInfoRecordService")
    private IServerInfoRecordService serverInfoRecordService;

    @RequestMapping("/serverInfoShow")
    public String serverInfoShow(HttpServletRequest request, ModelMap modelMap) {
        return "serverinfoshow";
    }

    @RequestMapping("/getServerInfo")
    public void getServerInfo() {
        Map para = new HashMap();
        List<ServerInfo> serverInfoList = this.serverInfoService.searchByMap(para);
        if(serverInfoList != null && serverInfoList.size()>0){
            for(int i=0;i<serverInfoList.size();i++){
                LinuxStateForShell linuxinfo = new LinuxStateForShell(serverInfoList.get(i).getLoginname(),serverInfoList.get(i).getLoginpwd(),serverInfoList.get(i).getIp());
                Map<String, String> result = linuxinfo.runDistanceShell(COMMANDS);
                String s = linuxinfo.disposeResultMessage(result);
                List<String> sinfo = new ArrayList<>();
                //CPU使用占有率
                Pattern p = Pattern.compile("\\d+(\\.\\d+)?");
                Matcher m = p.matcher(s);
                while(m.find()) {
                    sinfo.add(m.group());
                }

                System.out.println(serverInfoList.get(i).getIp());
                System.out.println("CPU占有率:"+sinfo.get(0)+"%");
                System.out.println("内存使用情况：总计："+linuxinfo.disposeUnit(sinfo.get(1)+"K")+"G，已使用："
                        +linuxinfo.disposeUnit(sinfo.get(2)+"K")+"G，空闲："+linuxinfo.disposeUnit(sinfo.get(3)+"K")+"G，缓存："+linuxinfo.disposeUnit(sinfo.get(4)+"K")+"G");
                System.out.println("磁盘使用情况：已使用："+sinfo.get(5)+"G，剩余："+sinfo.get(6)+"G，总共："+sinfo.get(7)+"G");

                //将记录保存至数据库
                ServerInfoRecord serverInfoRecord = new ServerInfoRecord();
                serverInfoRecord.setIP(serverInfoList.get(i).getIp());
                serverInfoRecord.setCPU(sinfo.get(0));
                serverInfoRecord.setMEMTOTOAL(Double.toString(linuxinfo.disposeUnit(sinfo.get(1)+"K")));
                serverInfoRecord.setMEMUSED(Double.toString(linuxinfo.disposeUnit(sinfo.get(2)+"K")));
                serverInfoRecord.setMEMREMAIN(Double.toString(linuxinfo.disposeUnit(sinfo.get(3)+"K")));
                serverInfoRecord.setMEMCACHE(Double.toString(linuxinfo.disposeUnit(sinfo.get(4)+"K")));
                serverInfoRecord.setDISKTOTAL(sinfo.get(7));
                serverInfoRecord.setDISKUSED(sinfo.get(5));
                serverInfoRecord.setDISKREMAIN(sinfo.get(6));
                serverInfoRecord.setCREATED_BY("sys");
                int insertnum = this.serverInfoRecordService.insertByRecord(serverInfoRecord);
                if(insertnum == 0){
                    logger.error("数据插入失败!");
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping("/queryAllRecord")
    public String QueryAllRecord(HttpServletRequest request,ModelMap modelMap){

        Map map = new HashMap();
        Map resultMap = new HashMap();
        List<ServerInfoRecord> serverInfoList = this.serverInfoRecordService.SelectByMap(map);
        resultMap.put("ips",serverInfoList);

        return JSON.toJSONString(resultMap);
    }

}