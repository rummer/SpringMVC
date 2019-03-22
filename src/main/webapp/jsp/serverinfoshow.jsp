<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>图表信息</title>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
</head>
<body>
    <div id="chartmain" style="width:600px;height:400px;"></div>

    <script type="text/javascript">
        var option={
            title:{
              text:'ECharts 数据统计'
            },
            tooltip:{
                trigger:'axis'
            },
            legend:{
              data:['用户来源']
            },
            xAxis:{
              data:["Android","IOS","PC","Other"]
            },
            yAxis:{

            },
            series:[{
               name:'用户来源',
               type:'line',
               data:[500,200,360,100]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('chartmain'));

        //使用制定的配置项和数据显示图表
        myChart.setOption(option);

    </script>
</body>
</html>
