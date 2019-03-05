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
<body>
<table id="dg" class="easyui-datagrid" title="Server Configuration" style="table-layout:fixed;width:99%;height:98%"
       data-options="
                iconCls:'icon-edit',
                singleSelect:false,
                nowrap:true,
                fitColumns:true,
                idField:'sysid',
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
        <th data-options="field:'ip',align:'center',halign:'center',editor:'textbox',width:'50px'">Server IP</th>
        <th data-options="field:'loginname',align:'center',halign:'center',editor:'textbox',width:'50px'">Login Name</th>
        <th data-options="field:'loginpwd',align:'center',halign:'center',editor:'textbox',width:'50px'">Login Password</th>
        <th data-options="field:'personcharge',align:'center',halign:'center',editor:'textbox',width:'50px'">Charge Person</th>
        <th data-options="field:'remark',align:'center',halign:'center',editor:'textbox',width:'150px'">Remarks</th>
    </tr>
    </thead>
</table>
    <div id="tb" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
    </div>

    <script type="text/javascript">
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                var id = $('#dg').datagrid('getEditor', {index:editIndex,field:'sysid'});
                $('#dg').datagrid('getRows')[editIndex]['sysid'] = id;
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
        function append(){
            if (endEditing()){
                $('#dg').datagrid('appendRow',{status:'P'});
                editIndex = $('#dg').datagrid('getRows').length-1;
                $('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
                }
        }
        function removeit(){
            if (editIndex == undefined){return}
            //$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
            //$('#dg').datagrid('reload');
            $('dg').datagrid('deleteRow', editIndex);
        }
        function accept(){
            if (endEditing()){
                $('#dg').datagrid('acceptChanges');
            }
        }

        function reject(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }

        function getChanges(){
            if(endEditing())
            {
                var rows = $('#dg').datagrid('getChanges');
                alert(rows.length+' rows are changed!');
            }

        }
    </script>

</body>
</html>
