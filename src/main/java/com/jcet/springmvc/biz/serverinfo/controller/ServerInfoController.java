package com.jcet.springmvc.biz.serverinfo.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import com.jcet.springmvc.biz.serverinfo.service.IServerInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/serverInfo")
public class  ServerInfoController {
    //private static Logger logger = Logger.getLogger(ServerInfoController.class);

    @Resource(name="ServerInfoService")
    private IServerInfoService serverInfoService;

    @RequestMapping("/serverConfig")
    public String serverConfig(HttpServletRequest request,ModelMap map)
    {
        return "serverconfig";
    }

    @Transactional
    @ResponseBody
    @RequestMapping("/searchByMap")
    public String searchServerByIp (HttpServletRequest request, ModelMap modelMap) {
        Map para = new HashMap();
        Map resultMap = new HashMap();

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

        try{
            //根据参数查询信息
            List<ServerInfo> serverInfoList = this.serverInfoService.searchByMap(para);
            PageInfo<ServerInfo> pageInfo = new PageInfo<>(serverInfoList);
            resultMap.put("total",pageInfo.getTotal());
            resultMap.put("rows",serverInfoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(resultMap);
    }

    @Transactional
    @ResponseBody
    @RequestMapping("deleteBySelected")
    public String deleteBySelected(HttpServletRequest request,ModelMap map){
        Map para = new HashMap();
        Map resultMap = new HashMap();

        String sysids = request.getParameter("sysids")==null?"":request.getParameter("sysids");
        if(!"".equals(sysids)){
            String[] sysid = sysids.split(",");

            try{
                for(int i=0;i<sysid.length;i++){
                    if(!"".equals(sysid[i])){
                        para.put("sysid",sysid[i]);

                        //先判断该记录是否存在
                        List<ServerInfo> serverInfoList = this.serverInfoService.searchByMap(para);

                        if(serverInfoList!=null && serverInfoList.size()==1){
                            ServerInfo serverInfo = serverInfoList.get(0);
                            serverInfo.setFlag("F");
                            int updatenum = this.serverInfoService.updateByPrimaryKey(serverInfo);
                            if(updatenum == 0){
                                resultMap.put("errorMsg","删除失败");
                                return JSON.toJSONString(resultMap);
                            }
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "{}";
    }

    @Transactional
    @ResponseBody
    @RequestMapping("insertByRow")
    public String insertByRow(HttpServletRequest request,ModelMap modelMap) {

        Map para = new HashMap();
        Map resultMap = new HashMap();

        String ip = request.getParameter("ip") == null ? "" : request.getParameter("ip");
        String loginname = request.getParameter("loginname") == null ? "" : request.getParameter("loginname");
        String loginpwd = request.getParameter("loginpwd") == null ? "" : request.getParameter("loginpwd");
        String pcharge = request.getParameter("pcharge") == null ? "" : request.getParameter("pcharge");
        String remark = request.getParameter("remark") == null ? "" : request.getParameter("remark");

        if ("".equals(ip))
        {
            resultMap.put("errorMsg", "ip不能为空");
            return JSON.toJSONString(resultMap);
        }
        else if("".equals(loginname))
        {
            resultMap.put("errorMsg", "loginname不能为空");
            return JSON.toJSONString(resultMap);
        }
        else if ("".equals(loginpwd))
        {
            resultMap.put("errorMsg", "loginpwd不能为空");
            return JSON.toJSONString(resultMap);
        }
        else if ("".equals(pcharge))
        {
            resultMap.put("errorMsg", "pcharge不能为空");
            return JSON.toJSONString(resultMap);
        }
        else if ("".equals(remark))
        {
            resultMap.put("errorMsg", "remark不能为空");
            return JSON.toJSONString(resultMap);
        }

        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIp(ip);
        serverInfo.setLoginname(loginname);
        serverInfo.setLoginpwd(loginpwd);
        serverInfo.setPersoncharge(pcharge);
        serverInfo.setRemark(remark);
        serverInfo.setCreated_by("sys");
        serverInfo.setFlag("T");

        int insertNum = this.serverInfoService.insertBySelective(serverInfo);
        if(insertNum == 0)
        {
            resultMap.put("errorMsg","新增失败");
            return JSON.toJSONString(resultMap);
        }

        return "{}";
    }

    @Transactional
    @ResponseBody
    @RequestMapping("updateServerInfo")
    public String updateServerInfo(HttpServletRequest request,ModelMap modelMap) {

        Map para = new HashMap();
        Map resultMap = new HashMap();

        String rows = request.getParameter("rows") == null ? "" : request.getParameter("rows");

            try{
                List<ServerInfo> serverInfoList = JSON.parseArray(rows,ServerInfo.class);
                if(serverInfoList != null && serverInfoList.size() >0){
                    for(int i=0;i<serverInfoList.size();i++){

                        int updatenum = this.serverInfoService.updateByPrimaryKey(serverInfoList.get(i));
                        if(updatenum == 0){
                            resultMap.put("errorMsg","更新失败");
                            return JSON.toJSONString(resultMap);
                        }
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }

            return "{}";
    }

    @ResponseBody
    @RequestMapping("/queryServerIp")
    public String QueryServerIp(HttpServletRequest request,ModelMap modelMap){

        Map map = new HashMap();
        Map resultMap = new HashMap();
        List<ServerInfo> serverInfoList = this.serverInfoService.searchByMap(map);
        resultMap.put("ips",serverInfoList);

        return JSON.toJSONString(resultMap);
    }
}