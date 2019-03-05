package com.jcet.springmvc.biz.serverinfo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import com.jcet.springmvc.biz.serverinfo.service.IServerInfoService;
import com.jcet.springmvc.common.LinuxStateForShell;
import com.jcet.springmvc.common.Mail;
import net.sf.jsqlparser.schema.Server;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jcet.springmvc.common.LinuxStateForShell.COMMANDS;

@Controller
@RequestMapping("/serverInfo")
public class  ServerInfoController {
    //private static Logger logger = Logger.getLogger(ServerInfoController.class);

    @Resource(name="ServerInfoService")
    private IServerInfoService serverInfoService;

    @RequestMapping("/getServerInfo")
    public void getServerInfo()
    {
        LinuxStateForShell linuxinfo = new LinuxStateForShell("root","123456","172.17.253.80");
        Map<String, String> result = linuxinfo.runDistanceShell(COMMANDS);
        String s = linuxinfo.disposeResultMessage(result);
        try{
            Mail mail = new Mail(s);
            mail.SendMail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/serverConfig")
    public String serverConfig(HttpServletRequest request,ModelMap map)
    {
        return "serverconfig";
    }

    @ResponseBody
    @RequestMapping(value="/searchByMap",produces = "text/html;charset=UTF-8")
    public String searchServerByIp (HttpServletRequest request, ModelMap modelMap)
    {
        Map para = new HashMap();

        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        String ip = request.getParameter("ip");
        String personcharge = request.getParameter("personcharge");

        int currentPage = Integer.parseInt(page == null ? "1" : page);
        int pageSize = Integer.parseInt(rows == null ? "10" : rows);
        int currentRow = (currentPage - 1) * pageSize;

        para.put("currentRow",currentRow);
        para.put("pageSize",pageSize);

        if(!"".equalsIgnoreCase(ip) && !"null".equalsIgnoreCase(ip)&& ip !=null)
        {
            para.put("ip",ip);
        }

        if(!"".equalsIgnoreCase(personcharge) && !"null".equalsIgnoreCase(personcharge)&& personcharge !=null)
        {
            para.put("personcharge",personcharge);
        }
        PageHelper.startPage(currentPage,pageSize);

        //根据参数查询信息
        List<ServerInfo> serverInfoList = this.serverInfoService.searchByMap(para);
        PageInfo<ServerInfo> pageInfo = new PageInfo<>(serverInfoList);

        try{
            Map resultMap = new HashMap();
            resultMap.put("total",pageInfo.getTotal());
            resultMap.put("rows",serverInfoList);


            return JSON.toJSONString(resultMap);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}