package com.jcet.springmvc.biz.main.maincontrol;

import com.jcet.springmvc.biz.serverinfo.domain.ServerInfo;
import com.jcet.springmvc.biz.serverinfo.service.IServerInfoService;
import com.sun.mail.iap.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @Resource(name="ServerInfoService")
    private IServerInfoService serverInfoService;

    @RequestMapping("/main")
    public String mainpage(HttpServletRequest request, ModelMap modelMap)
    {
        return "main";
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request,ModelMap modelMap)
    {
        return "index";
    }

}