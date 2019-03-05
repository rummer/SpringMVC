<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>长电科技服务器监控系统</title>
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../style/default.css">
    <link rel="stylesheet" type="text/css" href="../js/zTree/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/zTree/js/jquery.ztree.all.js"></script>
    <script type="text/javascript" src="../js/main.js"></script>

    <style type="text/css">
        .north{
            font-size:25px;
            font-weight:bold;
            padding:5px 10px;
            background:#336699;
            color:#fff;
        }

    </style>

    <script type="text/javascript">

    </script>
</head>
<body>
<div class="easyui-layout" style="width:100%;height:100%;">
    <div region="north" border="false" class="north">
        <div>
            长电科技服务器监控系统
        </div>
    </div>
    <div region="west" split="true" title="导航菜单" class="west" style="width:200px;padding:10px;">
        <ul id="menuTree" class="ztree"></ul>
    </div>
    <div data-options="region:'center',border:false,plain:true">
        <div id="tabs" class="easyui-tabs" fit="true">
            <div title="Home" data-options="tools:'#p-tools'" style="text-align:center;font-size:24px">
                <iframe id="content" src="/index.jsp" frameborder="0" border="0" scrolling="no"
                        style="width:100%;height:100%"></iframe>
            </div>
        </div>
    </div>
    <div data-options="region:'south',border:false" style="width:100%;height:30px;background:#D2E0F2;padding:5px;overflow: hidden;">
        <div style="float:left;height:100%;width:20%"></div>
        <div class="footer">Copy right(C) 江苏长电科技 Powered By JCET</div>
        <div id="nowTime"></div>
    </div>
</div>
</body>
</html>
