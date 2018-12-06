<%--
  Created by IntelliJ IDEA.
  User: ckt
  Date: 2018/12/2 0002
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户数据</title>
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
            iconCls: 'icon-edit',
            text: "导入",
            handler: function () {
                $("#adddiv").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: "导出",
            handler: function () {
                location = "${pageContext.request.contextPath}/exportUserData";
            }

        }]

        $(function () {
            /*展示全部面板*/
            $("#userdatagrid").datagrid({
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/getUserAll",
                pagination: true,//分页
                type: "post",
                iconCls: "icon-tip",//图标
                title: "用户",
                columns: [[
                    //{checkbox: true},
                    {title: "编号", field: "id", hidden: "true"},
                    {title: "姓名", field: "username", width: 100},
                    {
                        title: "性别", field: "sex", width: 100,
                        formatter: function (value, row, index) {
                            if (row.sex == 1) {
                                return "男";
                            }
                            if (row.sex == 0) {
                                return "女";
                            }
                        }
                    },
                    {
                        title: "状态", field: "status", width: 100,
                        formatter: function (value, row, index) {
                            if (row.status == 0) {
                                return "正常";
                            } else {
                                return "冻结";
                            }
                        },
                        editor: {
                            type: "text",
                            options: {
                                required: true
                            }
                        }

                    },
                    {title: "城市", field: "province", width: 100},
                    {title: "注册日期", field: "date", width: 100}
                ]],
                fit: true,
                fitColumns: true,
                pagination: true,
                pageSize: 3,
                pageList: [3, 6, 9],


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
            $("#username").validatebox({
                required: true,
                missingMessage: "不能为空!!",
            });
            /*标题===END===*/
            /*内容验证*/
            $("#province").validatebox({
                required: true,
                missingMessage: "不能为空!!",
            });
            /*内容===END===*/


            /*批量添加*/

        });


        /*执行添加*/
        function goAdd() {

            $("#addForm").form("submit", {
                url: "${pageContext.request.contextPath}/importUserData",
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

        var i = 1;

        function addMany() {

            var td = $("#td1").html("书1:");

            $("#t1").append(
                " <tr>\n" +
                "                <td>\n" +
                "                    姓名：\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input id=\"username\" name=\"users[" + i + "].username\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    性别：\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    男<input type=\"radio\" value=\"1\" name=\"users[" + i + "].sex\" class=\"sex" + i + "\" checked>\n" +
                "                    女<input type=\"radio\" value=\"0\" name=\"users[" + i + "].sex\" class=\"sex0" + i + "\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    城市：\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input id=\"province\" name=\"users[" + i + "].province\">\n" +
                "                </td>\n" +
                "            </tr>"
            );
            i++;

        }
    </script>
</head>
<body>
<table id="userdatagrid"></table>

<%--添加div--%>
<div id="adddiv">
    <form id="addForm" method="post" enctype="multipart/form-data">
        <table id="t1">
            <tr>
                <td>
                    姓名：
                </td>
                <td>
                    <input id="username" name="users[0].username">
                </td>
            </tr>
            <tr>
                <td>
                    性别：
                </td>
                <td>
                    男<input type="radio" value="1" name="users[0].sex" class="sex0" checked>
                    女<input type="radio" value="0" name="users[0].sex" class="sex0">
                </td>
            </tr>
            <tr>
                <td>
                    城市：
                </td>
                <td>
                    <input id="province" name="users[0].province">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="JavaScript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                       onclick="goAdd()">添加</a>
                    <input type="button" value="添加多本书籍" class="button" onclick="addMany()"/>
                    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加多人</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--添加div===END===--%>
</body>
</html>
