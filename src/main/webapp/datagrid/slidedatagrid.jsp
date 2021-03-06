<%--
  Created by IntelliJ IDEA.
  User: ckt
  Date: 2018/11/28 0028
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>轮播图</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#adddiv").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: "修改",
            handler: function () {
                /*获取选中行*/
                var row = $("#slidegdatagrid").edatagrid("getSelected")
                if (row == null) {
                    $.messager.show({
                        title: '警告',
                        msg: '请选中修改行。',
                        showType: 'show',
                        style: {
                            right: '',
                            top: document.body.scrollTop + document.documentElement.scrollTop,
                            bottom: ''
                        }
                    });
                } else {
                    /*将当前行变成可编辑模式*/
                    var index = $("#slidegdatagrid").edatagrid("getRowIndex", row);
                    $("#slidegdatagrid").edatagrid("editRow", index);
                    $("#slidegdatagrid").edatagrid("saveRow");
                }
            }
        }, '-', {
            iconCls: 'icon-remove',
            text: "删除",
            handler: function () {
                var row = $("#slidegdatagrid").edatagrid("getSelected");
                if (row == null) {
                    $.messager.alert("提示框", "请选中要删除的数据", "warning");
                } else {
                    $.messager.confirm("确认框", "确认真的要删除选中的内容吗？", function (result) {
                        if (result) {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/deleteSlide",
                                data: "id=" + row.id,
                                success: function (data) {
                                    if (data) {
                                        $.messager.show({
                                            title: '提示',
                                            msg: '删除成功。',
                                            showType: 'show',
                                            style: {
                                                right: '',
                                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                                bottom: ''
                                            }
                                        });
                                        $("#slidegdatagrid").datagrid("reload");
                                    } else {
                                        $.messager.show({
                                            title: '提示',
                                            msg: '删除失败。',
                                            showType: 'show',
                                            style: {
                                                right: '',
                                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                                bottom: ''
                                            }
                                        });
                                    }

                                },
                                dataType: "json"

                            })
                        }
                    });
                }

            }
        }, '-', {
            iconCls: 'icon-save',
            text: "保存",
            handler: function () {
                $("#slidegdatagrid").edatagrid("saveRow");
            }
        }]

        $(function () {
            /*展示全部面板*/
            $("#slidegdatagrid").edatagrid({
                toolbar: toolbar,
                updateUrl: "${pageContext.request.contextPath}/updateSlideByidAndstatus",
                saveUrl: "",
                destroyUrl: "${pageContext.request.contextPath}/deleteSlide",
                url: "${pageContext.request.contextPath}/getSlideByPage",
                pagination: true,//分页
                type: "post",
                iconCls: "icon-tip",//图标
                title: "轮播图",
                columns: [[
                    //{checkbox: true},
                    {title: "编号", field: "id", hidden: "true"},
                    {title: "名字", field: "title", width: 100},
                    {
                        title: "状态", field: "status", width: 100,
                        formatter: function (value, row, index) {
                            if (row.status == 0) {
                                return "展示";
                            } else {
                                return "不展示";
                            }
                        },
                        editor: {
                            type: "text",
                            options: {
                                required: true
                            }
                        }

                    },
                    {title: "路径", field: "imgPath", width: 100},
                    {title: "时间", field: "date", width: 100}
                ]],
                fit: true,
                fitColumns: true,
                pagination: true,
                pageSize: 3,
                pageList: [3, 6, 9],
                view: detailview,
                detailFormatter: function (rowIndex, rowData) {
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/shouye/' + rowData.imgPath + '" style="height:80px;"></td>' +
                        '<td style="border:0">' +
                        '<p>时间: ' + rowData.date + '</p>' +
                        '<p>描述:' + rowData.ldesc + '</p>' +
                        '<p>路径:' + rowData.imgPath + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }

            })
            /*添加面板*/
            $("#adddiv").dialog({
                //iconCls:"icon-add",//图标
                iconCls: "icon-help",//图标
                title: "添加面板",
                height: 600,
                width: 551,
                resizable: false,//设置面板大小是否可变
                modal: true,//灯罩效果
                closed: true,//是否初始化时显示
            });
            /*标题验证*/
            $("#title").validatebox({
                required: true,
                missingMessage: "不能为空!!",
            });
            /*标题===END===*/
            /*内容验证*/
            $("#ldesc").validatebox({
                required: true,
                missingMessage: "不能为空!!",
            });
            /*内容===END===*/
        });


        /*执行添加*/
        function goAdd() {
            //alert(0)
            $("#addForm").form("submit", {
                url: "${pageContext.request.contextPath}/addSilde",
                type: "post",
                onSubmit: function () {
                    var OK = $("#addForm").form("validate");
                    return OK
                },
                success: function (result) {
                    //alert(result);
                    if (result) {
                        $.messager.show({
                            title: '提示',
                            msg: '添加成功。',
                            showType: 'show',
                            style: {
                                right: '',
                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                bottom: ''
                            }
                        });
                        $("#pagingdatagrid").datagrid("reload");
                        $("#adddiv").dialog("close");
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: '添加失败。',
                            showType: 'show',
                            style: {
                                right: '',
                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                bottom: ''
                            }
                        });
                    }
                },
            })
        }

        /*执行添加===END===*/
    </script>
</head>
<body>
<table id="slidegdatagrid"></table>

<%--添加div--%>
<div id="adddiv">
    <form id="addForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    姓名：
                </td>
                <td>
                    <input id="title" name="title">
                </td>
            </tr>
            <tr>
                <td>
                    路径：
                </td>
                <td>
                    <input type="file" id="imgPath" name="img">
                </td>
            </tr>
            <tr>
                <td>
                    描述：
                </td>
                <td>
                    <input id="ldesc" name="ldesc">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="JavaScript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                       onclick="goAdd()">添加</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--添加div===END===--%>
</body>

</html>