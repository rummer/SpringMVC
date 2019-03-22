var zNodes=[
    {id:1,pId:0,name:"服务器监控",file:"/index",open:false},
    {id:11,pId:1,name:"参数配置",file:"serverInfo/serverConfig"},
    {id:12,pId:1,name:"实时监控"}

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
        onExpand:zTreeOnExpand,
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

function zTreeOnExpand(){
    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    var parentZNode = treeObj.getNodeByParam("id", 12, null);//获取指定父节点

    //获取所有的服务器
    var url = getRealPath()+'/serverInfo/queryServerIp';
    $.post(url,{},function(result,status) {
        var result = JSON.parse(result);

        for(var i=0;i<result.ips.length;i++){
            treeObj.addNodes(parentZNode,{id:(100+i),pId:12,name:result.ips[i].ip,file:"/serverInfoRecord/serverInfoShow?ip="+result.ips[i].ip});
        }

    });
}

function getRealPath(){
    //获取当前网址，如： http://localhost:8083/myproj/view/my.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： myproj/view/my.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/myproj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    //得到了 http://localhost:8083/myproj
    var realPath=localhostPaht+projectName;

    return realPath;
}