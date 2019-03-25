<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>图表信息</title>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.min.js"></script>
</head>
<body>
    <div id="chartmain" style="width:100%;height:400px;"></div>

    <script type="text/javascript">

        //获取地址的参数值
        function GetQueryString(name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }

        <!--页面刷新的时候执行 -->
        $(function(){
            setFsChartBar();
        });
        function setFsChartBar(){
            var Chart=echarts.init(document.getElementById("chartmain"));//初始化
            //用户等待
            Chart.showLoading(
                {text: 'Loading...'  }
            );

            var serverIp = GetQueryString("ip");
            getData(Chart,serverIp); //先执行一次，以防等十秒后才出现图表
            setInterval(function(){//定时器
                getData(Chart, serverIp);
            }, 10*1000);//每隔10秒刷新一次
        }
        //获取数据的方法
        function getData(Chart,serverIp){
            //发送Ajax请求
            $.ajax({
                url : "/serverInfoRecord/queryAllRecord",
                dataType:"json",
                data:{serverIp:serverIp},
                type:'post',
                success:function(data){
                    var chartDatas = [];

                    for(var i=0;i<data.Record.length;i++){
                        var obj=new Object();
                        obj.name=data.Record[i].cREATED_DATE;
                        obj.value=data.Record[i].cPU;
                        chartDatas[i]=obj;
                    }

                    var option = {
                        title: {
                            text: 'CPU使用率统计'
                        },

                        tooltip: {
                            trigger: 'axis'
                        },

                        legend: {
                            bottom:10,
                            left:'center',
                            data:['CPU(%)']
                        },

                        xAxis: {
                            trigger:'axis',
                            data:["2019-3-22 00:00","2019-3-22 01:00","2019-3-22 02:00","2019-3-22 03:00","2019-3-22 04:00"]
                        },

                        yAxis: {
                            type: 'value'
                        },
                        series : [{
                            name:'CPU',
                            type:'line',
                            data:chartDatas
                        }]
                    };
                    //结束
                    Chart.hideLoading();
                    Chart.setOption(option);
                }
            });
        }

    </script>
</body>
</html>
