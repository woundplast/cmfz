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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var toolbar = [{
            iconCls: 'icon-add',
            handler: function () {
                alert('编辑按钮')
            }
        }, '-', {
            iconCls: 'icon-edit',
            handler: function () {
                alert('帮助按钮')
            }
        }, '-', {
            iconCls: 'icon-remove',
            handler: function () {
                alert('帮助按钮')
            }
        }, '-', {
            iconCls: 'icon-save',
            handler: function () {
                alert('帮助按钮')
            }
        }]

        $(function () {
            /*展示全部面板*/
            $("#slidegdatagrid").datagrid({
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/getSlideByPage",
                pagination: true,//分页
                type: "post",
                iconCls: "icon-tip",//图标
                title: "所有用户信息",
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
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.imgPath + '" style="height:80px;"></td>' +
                        '<td style="border:0">' +
                        '<p>时间: ' + rowData.date + '</p>' +
                        '<p>描述:' + rowData.desc + '</p>' +
                        '<p>路径:' + rowData.imgPath + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }


            })
        });
    </script>
</head>
<body>
<table id="slidegdatagrid"></table>
</body>

</html>