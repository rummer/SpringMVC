package com.jcet.springmvc.test.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/main")
public class Maincontroller {
    private static Logger logger = Logger.getLogger(Maincontroller.class);

    public void getServerInfo(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            //String s = JSON.toJSONString()
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}