<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>服务器配置</title>
<%--    <link rel="stylesheet" type="text/css" href="../js/bootstrap-4.0.0-dist/css/bootstrap.css">
    <script type="text/javascript" src="../js/bootstrap-4.0.0-dist/js/bootstrap.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../style/default.css">
    <link rel="stylesheet" type="text/css" href="../js/zTree/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/zTree/js/jquery.ztree.all.js"></script>
    <script type="text/javascript" src="../js/main.js"></script>

</head>
<body class="easyui-layout">
<table id="dg" class="easyui-datagrid" title="服务器信息配置" style="table-layout:fixed;width:99%;height:98%"
       data-options="
                iconCls:'icon-edit',
                singleSelect:false,
                nowrap:true,
                fitColumns:true,
                rownumbers:true,
                showFooter:true,
                showPageList:true,
                pagination:true,
                pageSize:'20',
                toolbar:'#tb',
                url:'<%=basePath%>/serverInfo/searchByMap',
                method:'get',
                onClickRow:onClickRow">
    <thead>
    <tr>
        <th data-options="field:'checkbox',align:'center',halign:'center',checkbox:true"></th>
        <th data-options="field:'sysid',align:'center',halign:'center',hidden:'hidden'">sysid</th>
        <th data-options="field:'ip',align:'center',halign:'center',editor:'textbox',width:'50px'">服务器IP</th>
        <th data-options="field:'category',align:'center',halign:'center',editor:'textbox',width:'50px'">服务器类型</th>
        <th data-options="field:'loginname',align:'center',halign:'center',editor:'textbox',width:'50px'">登录名</th>
        <th data-options="field:'loginpwd',align:'center',halign:'center',editor:'textbox',width:'50px'">登录密码</th>
        <th data-options="field:'personcharge',align:'center',halign:'center',editor:'textbox',width:'50px'">负责人</th>
        <th data-options="field:'remark',align:'center',halign:'center',editor:'textbox',width:'150px'">备注</th>
    </tr>
    </thead>
</table>
    <div id="tb" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="openDialog()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteData()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveData()">保存</a>
    </div>

    <div id="rdlg" class="easyui-dialog" style="width:380px;height:350px;padding:10px 20px;"
            data-options="title:'新增服务器',closed:true,modal:true,buttons:'#rdlg-buttons'">
     <table width="100%" border="0" align="center" cellpadding="3">
         <tr>
             <td width="30%" align="right">
                 <label for="txtip">服务器IP</label>
             </td>
             <td>
                 <input id="txtip" name="txtip" class="easyui-textbox" style="width:200px;"/>
             </td>
         </tr>
         <tr>
             <td width="30%" align="right">
                 <label for="txtcategory">服务器类型</label>
             </td>
             <td>
                 <input id="txtcategory" name="txtcategory" class="easyui-textbox" style="width:200px;"/>
             </td>
         </tr>
         <tr>
             <td width="30%" align="right">
                 <label for="txtloginname">登录名</label>
             </td>
             <td>
                 <input id="txtloginname" name="txtloginname" class="easyui-textbox" style="width:200px;"/>
             </td>
         </tr>
         <tr>
             <td width="30%" align="right">
                 <label for="txtloginpwd">登录密码</label>
             </td>
             <td>
                 <input id="txtloginpwd" name="txtloginpwd" class="easyui-textbox" style="width:200px;"/>
             </td>
         </tr>
         <tr>
             <td width="30%" align="right">
                 <label for="txtpcharge">负责人</label>
             </td>
             <td>
                 <input id="txtpcharge" name="txtpcharge" class="easyui-textbox" style="width:200px;"/>
             </td>
         </tr>
         <tr>
             <td width="30%" align="right">
                 <label for="txtremark">备注</label>
             </td>
             <td>
                 <input id="txtremark" name="txtremark" class="easyui-textbox" data-options="multiline:true" style="width:200px;height:80px;"/>
             </td>
         </tr>
     </table>

    </div>

    <div id="rdlg-buttons">
        <table cellpadding="0" cellspacing="0" style="width:100%">
            <tr>
                <td style="text-align:right">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="javascript:addData()">保存</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#rdlg').dialog('close')">取消</a>
                </td>
            </tr>
        </table>
    </div>

    <script type="text/javascript">
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                //var id = $('#dg').datagrid('getEditor', {index:editIndex,field:'sysid'});
                //$('#dg').datagrid('getRows')[editIndex]['sysid'] = id;
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
                } else {
                return false;
                }
        }

        function onClickRow(index){
            if (editIndex != index){
                if (endEditing()){
                    $('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                    }
            }
        }

        function deleteData(){
            var rows = $('#dg').datagrid('getSelections');
            var allRows = $('#dg').datagrid('getRows');
            if(rows.length == 0){
                $.messager.alert('操作提示','请至少选择一行数据','warning');
                return false;
            }

            $.messager.confirm('操作提示','确认删除吗？',function(data){
                if(data)
                {
                    var sysids = "";
                    for(var i = 0;i<rows.length;i++){
                        var sysid = ((rows[i].sysid == undefined)?'':rows[i].sysid);
                        sysids =sysids + sysid + ",";
                    }
                    $.post('<%=basePath%>/serverInfo/deleteBySelected',{"sysids":sysids},function(result,status){

                        var result = JSON.parse(result);
                        if(result && result.errorMsg){
                            $.messager.alert('操作提示','删除失败','Warning');
                        }else{
                            $.messager.alert('操作提示','删除成功','info');
                            $('#dg').datagrid('reload');
                        }

                    });

                }else{
                    return false;
                }
            });

        }

        function openDialog(){
            //将弹框内文本框置为空
            $('#txtip').textbox('setValue','');
            $('#txtcategory').textbox('setValue','');
            $('#txtloginname').textbox('setValue','');
            $('#txtloginpwd').textbox('setValue','');
            $('#txtpcharge').textbox('setValue','');
            $('#txtremark').textbox('setValue','');

            //打开弹框
            $('#rdlg').dialog('open');
        }

        function addData(){
            //获取填写的值
            var ip = $('#txtip').val();
            var loginname = $('#txtloginname').val();
            var loginpwd = $('#txtloginpwd').val();
            var pcharge = $('#txtpcharge').val();
            var remark = $('#txtremark').val();

            if(ip == ''||loginname == ''||loginpwd == ''|| pcharge == ''||remark ==''){
                $('#txtip').validatebox();
                $.messager.alert('error','不允许为空','Warning');
                return false;
            }

            $.messager.confirm('操作提示','确认新增吗？',function(data){
                $.post('<%=basePath%>/serverInfo/insertByRow',{"ip":ip,"loginname":loginname,"loginpwd":loginpwd,"pcharge":pcharge,"remark":remark},function(result,status){
                    var result = JSON.parse(result);
                    if(result && result.errorMsg){
                        $.messager.alert('操作提示','新增失败','Warning');
                    }else{
                        $.messager.alert('操作提示','新增成功','info');
                        $('#rdlg').dialog('close');
                        $('#dg').datagrid('reload');
                    }

                });

            });

        }

        function saveData(){
            if(endEditing()){
                var rows = $('#dg').datagrid('getChanges');
                if(rows.length>0){
                    var rows = JSON.stringify(rows);
                    $.post('<%=basePath%>/serverInfo/updateServerInfo',{"rows":rows},function(result,status){
                        var result = JSON.parse(result);
                        if(result && result.errorMsg){
                            $.messager.alert('失败','保存失败','Warning');
                        }else{
                            $.messager.alert('成功','保存成功！','info');
                            $('dg').datagrid('reload');
                        }
                    });
                }else{
                    $.messager.alert('提示','未有修改！','info');
                    $('dg').datagrid('reload');
                }
            }
        }

    </script>

</body>
</html>
