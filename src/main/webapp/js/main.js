Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

setInterval("document.getElementById('nowTime').innerHTML=new Date().format('yyyy年MM月dd日 hh:mm:ss')+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);


var zNodes=[
    {id:1,name:"服务器监控",file:"/index",open:"True",isParent:true,
        children:[
            {id:11,name:"参数配置",file:"serverInfo/serverConfig",open:1}
        ]},
];

var setting = {
    data:{
      simpleData:{
          enable:true
      }
    },
    view:{
        selectedMulti:false,
        fontCss:{"font-size":"20px"},
        showTitle:true
    },
    callback:{
        onClick:function (e,id,node) {
            var zTree = $.fn.zTree.getZTreeObj("menuTree");
            if(node.isParent){
                zTree.expandNode();
            }else{
                addTab(node.name,node.file);
            }
        }
    }
}

function addTab(title, url){
    if ($('#tabs').tabs('exists', title))
    {
        $('#tabs').tabs('select', title);
    } else {
        var content = '<iframe id='+title+' src="'+url+'" scrolling="no" frameborder="0" style="width:100%;height:100%;"/>';
        $('#tabs').tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
}

function  closeTab(title) {
    if($('#tabs').tabs('exists',title))
    {
        $('#tabs').tabs('close',title);
    }
}

$(function(){
    $.fn.zTree.init($("#menuTree"),setting,zNodes);
    var zTree = $.fn.zTree.getZTreeObj("menuTree");

    //中间部分tab
    $('#tabs').tabs({
        border:false,
        fit:true,
        onSelect:function (title,index) {
            var treeNode = zTree.getNodeByParam("name",title,null);
            zTree.selectNode(treeNode);
        }
    });

    $('.index_panel').panel({
        width:300,
        height:200,
        closable:true,
        minimizable:true,
        title:'My Panel'
    });
});