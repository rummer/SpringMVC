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
        $(function(){
            setFsChartBar();
        });

        function setFsChartBar(){
            var Chart=echarts.init(document.getElementById("chartmain"));//初始化
            //用户等待
            Chart.showLoading({text: 'Loading...'  });

            var serverIp = GetQueryString("ip");
            getData(Chart,serverIp); //先执行一次，以防等十秒后才出现图表
            setInterval(function(){//定时器
                getData(Chart, serverIp);

            }, 10*1000);//每隔10秒刷新一次
        }

        //获取地址的参数值
        function GetQueryString(name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
        }

        function splitData(rawData){
            var categoryData=[];
            var values=[];
            for(var i=0;i<rawData.length;i++){
                categoryData.push(rawData[i].cREATED_DATE);
                //values.push(rawData[i].cPU);
                var data = [];
                data.push(rawData[i].cREATED_DATE);
                data.push(rawData[i].cPU);
                values.push(data);
            }

            return {
                categoryData:categoryData,
                values:values
            };
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
                    var chartDatas = splitData(data.Record);

                    var option = {
                        title: {
                            text: 'CPU使用率统计'
                        },

                        legend: {
                            top:10,
                            left:'center',
                            data:['CPU']
                        },

                        tooltip: {
                            trigger: 'axis',
                        },

                        toolbox: { //可视化的工具箱
                            show: true,
                            feature: {
                                dataView: { //数据视图
                                    show: true
                                },
                                restore: { //重置
                                    show: true
                                },
                                dataZoom: { //数据缩放视图
                                    show: true
                                },
                                saveAsImage: {//保存图片
                                    show: true
                                },
                                magicType: {//动态类型切换
                                    type: ['bar', 'line']
                                }
                            }
                        },

                        xAxis: {
                            type:'time',
                            splitLine:{
                                show:true
                            }

                        },

                        yAxis: {
                            type: 'value',
                            boundaryGap:[0,'100%'],
                            splitLine:{
                                show:true
                            }
                        },

                        series : [{
                            name:'CPU模拟数据',
                            type:'line',
                            data:chartDatas.values
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
