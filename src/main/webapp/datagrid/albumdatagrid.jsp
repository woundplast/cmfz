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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        var toolbar = [{
            iconCls: 'icon-tip',
            text: "专辑详情",
            handler: function () {
                $("#adddiv").dialog("open");
                var row = $("#albumdatagrid").edatagrid("getSelected");
                if (row == null || row.score == null) {
                    $.messager.show({
                        title: '警告',
                        msg: '请选中专辑。',
                        showType: 'show',
                        style: {
                            right: '',
                            top: document.body.scrollTop + document.documentElement.scrollTop,
                            bottom: ''
                        }
                    });
                } else {
                    var row = $("#albumdatagrid").edatagrid("getSelected");
                    $("#albumform").form("load", row);
                    console.log("-----" + row);
                    console.log(row.acount);
                    console.log(row.title)
                    $("#showImg").prop("src", "${pageContext.request.contextPath}/upload/" + row.coverImg);
                    $("#albumshow").dialog("open");

                }
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "添加专辑",
            handler: function () {
                $("#addAlbumDiv").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "添加章节",
            handler: function () {
                var row = $("#albumdatagrid").edatagrid("getSelected");
                if (row == null || row.score == null) {
                    $.messager.show({
                        title: '警告',
                        msg: '请选中专辑。',
                        showType: 'show',
                        style: {
                            right: '',
                            top: document.body.scrollTop + document.documentElement.scrollTop,
                            bottom: ''
                        }
                    });
                } else {
                    $("#addChapterForm").form("load", row);
                    $("#addChapterDiv").dialog("open");
                }


            }
        }, '-', {
            iconCls: 'icon-undo',
            text: "下载音频",
            handler: function () {
                var row = $("#albumdatagrid").treegrid("getSelected");
                console.log("---" + row)
                if (row == null || row.size == null) {
                    $.messager.show({
                        title: '警告',
                        msg: '请选中章节。',
                        msg: '请选中章节。',
                        showType: 'show',
                        style: {
                            right: '',
                            top: document.body.scrollTop + document.documentElement.scrollTop,
                            bottom: ''
                        }
                    });
                } else {
                    if (row.size != null) {
                        location.href = "${pageContext.request.contextPath}/download?url=" + row.downPath + "&title=" + row.title
                    }

                }
            }
        }]
        $(function () {
            /*展示全部面板*/
            $('#albumdatagrid').treegrid({
                toolbar: toolbar,
                url: '${pageContext.request.contextPath}/queryAllAlbum',
                idField: 'id',
                treeField: 'title',
                columns: [[
                    {title: '名字', field: 'title', width: 180},
                    {field: 'downPath', title: '下载路径', width: 60, align: 'right'},
                    {field: 'size', title: '章节大小', width: 80},
                    {field: 'duration', title: '章节时长', width: 80}
                ]],
                fit: true,
                fitColumns: true,
                pagination: true,
                pageSize: 3,
                pageList: [3, 6, 9],
                //双击事件
                onDblClickRow: function (rowIndex, rowData) {
                    var row = $("#albumdatagrid").treegrid("getSelected");
                    console.log(row.size)
                    if (row.size != null) {

                        $("#audio").dialog("open")
                        $("#audio_id").prop("src", "${pageContext.request.contextPath}/upload/" + row.downPath)
                    } else {
                        $.messager.show({
                            title: '警告',
                            msg: '请选中章节可以播放。',
                            showType: 'show',
                            style: {
                                right: '',
                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                bottom: ''
                            }
                        });
                    }
                },
            });

            $('#audio').dialog({
                title: '播放',
                width: 400,
                height: 200,
                closed: true,
            });

            /*展示面板*/
            $("#albumshow").dialog({
                //iconCls:"icon-add",//图标
                iconCls: "icon-help",//图标
                title: "添加面板",
                height: 357,
                width: 550,
                resizable: false,//设置面板大小是否可变
                modal: true,//灯罩效果
                closed: true,//是否初始化时显示
            });
            /*展示面板===EDN===*/
            /*添加专辑*/
            $("#addAlbumDiv").dialog({
                //iconCls:"icon-add",//图标
                iconCls: "icon-help",//图标
                title: "添加面板",
                height: 185,
                width: 490,
                resizable: true,//设置面板大小是否可变
                modal: true,//灯罩效果
                closed: true,//是否初始化时显示
            });
            /*添加专辑===END===*/
            /*添加章节*/
            $("#addChapterDiv").dialog({
                //iconCls:"icon-add",//图标
                iconCls: "icon-help",//图标
                title: "添加面板",
                height: 173,
                width: 490,
                resizable: true,//设置面板大小是否可变
                modal: true,//灯罩效果
                closed: true,//是否初始化时显示
            });
            /*添加章节===END===*/
            /*添加专辑验证*/
            $("#ctitle").validatebox({
                required: true,
                missingMessage: "不能为空!!",
            });
            /*添加专辑验证===END===*/

        });

        /*执行添加专辑操作*/
        function goaddAlbum() {
            $("#addAlbumForm").form("submit", {
                url: "${pageContext.request.contextPath}/addAlbum",
                type: "post",
                onSubmit: function () {
                    var OK = $("#addAlbumForm").form("validate");
                    return OK
                },
                success: function (result) {
                    //alert(result);
                    if (result) {

                        $.messager.show({
                            title: '提示',
                            msg: '添加专辑成功。',
                            showType: 'show',
                            style: {
                                right: '',
                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                bottom: ''
                            }
                        });
                        $("#addAlbumDiv").dialog("close");
                        $("#albumdatagrid").treegrid("reload");
                        $("#albumshow").dialog("reload")

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

        /*执行添加专辑操作===END==*/
        /*----执行添加章节---*/
        function goaddChapter() {
            $("#addChapterForm").form("submit", {
                url: "${pageContext.request.contextPath}/addChapter",
                type: "post",
                onSubmit: function () {
                    var OK = $("#addChapterForm").form("validate");
                    return OK
                },
                success: function (result) {
                    //alert(result);
                    if (result) {
                        $.messager.show({
                            title: '提示',
                            msg: '添加章节成功。',
                            showType: 'show',
                            style: {
                                right: '',
                                top: document.body.scrollTop + document.documentElement.scrollTop,
                                bottom: ''
                            }
                        });
                        $("#albumdatagrid").treegrid("reload");
                        $("#addChapterDiv").dialog("close");
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

        /*-------*/

    </script>
</head>
<body>
<table id="albumdatagrid"></table>

<%--专辑展示div--%>
<div id="albumshow">
    <form id="albumform">
        <table border="10" cellspacing="0" align="center" bgcolor="antiquewhite">
            <tr>
                <td><input type="hidden" name="aid"></td>
            </tr>
            <tr>
                <td>专辑名:</td>
                <td><input name="title"></td>
                <td>专辑描述:</td>
                <td><input name="brief"></td>
            </tr>
            <tr>
                <td>专辑集数:</td>
                <td><input name="acount"/></td>
                <td>专辑评分:</td>
                <td><input name="score"></td>
            </tr>
            <tr>
                <td>专辑作者:</td>
                <td><input name="author"></td>
                <td>专辑播音:</td>
                <td><input name="brodCast"></td>
            </tr>
            <tr>
                <td>专辑图片:</td>
                <td colspan="10"><img src="" id="showImg" height="200" width="400"></td>
            </tr>

        </table>
    </form>

</div>
<%--专辑展示div===END===--%>

<%--添加专辑--%>
<div id="addAlbumDiv">
    <form id="addAlbumForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>专辑名:</td>
                <td><input id="title" name="title"></td>
                <td>专辑描述:</td>
                <td><input id="brief" name="brief"></td>
            </tr>
            <tr>
                <td>专辑集数:</td>
                <td><input id="acount" name="acount"></td>
                <td>专辑评分:</td>
                <td><input id="score" name="score"></td>
            </tr>
            <tr>
                <td>专辑作者:</td>
                <td><input id="author" name="author"></td>
                <td>专辑播音:</td>
                <td><input id="brodCast" name="brodCast"></td>
            </tr>
            <tr>
                <td>专辑图片:</td>
                <td colspan="3"><input type="file" name="img"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="JavaScript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                       onclick="goaddAlbum()">添加</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--添加专辑===END===--%>
<%--添加章节--%>
<div id="addChapterDiv">
    <form id="addChapterForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <input type="hidden" name="id">
                <td>章节名:</td>
                <td><input id="ctitle" name="ctitle"></td>
            </tr>
            <tr>
                <td>章节文件:</td>
                <td colspan="3"><input type="file" name="img"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="JavaScript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                       onclick="goaddChapter()">添加</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
<%--添加章节===END===--%>
<div id="audio">
    <audio id="audio_id" src="" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>

</body>

</html>