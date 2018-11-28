﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/queryAllMenu",
                type: "post",
                //data:{},
                success: function (data) {
                    <!--菜单处理-->
                    for (var i = 0; i < data.length; i++) {
                        var sonTitle = '';
                        for (var j = 0; j < (data[i].menus).length; j++) {
                            sonTitle += '<div style="color:#f00; width:211px;  text-align: center; font-size: 20px' +
                                '" onclick="openTitle(this)">' + data[i].menus[j].title + '</div>';
                        }
                        $('#aa').accordion('add', {
                            iconCls: data[i].iconcls,
                            title: data[i].title,
                            content: sonTitle,
                            selected: false
                        });

                    }
                },
                dateType: "json"

            });

        });

        function openTitle(data) {
            //console.log(data[i].menus[j].title);
            var sonTitle = $(data).html();
            //alert(sonTitle)
            //标题页签是否存在
            var isExists = $("#tt").tabs("exists", sonTitle);
            if (isExists) {
                //存在
                $("#tt").tabs("select", sonTitle);
            } else {
                //不存在
                $("#tt").tabs("add", {
                    title: sonTitle,
                    closable: true,
                    iconCls: "icon-arrow_nsew",
                    content: sonTitle
                    //content:'<iframe src="bookList.jsp?id='+node.id+'" width="100%" height="100%"></iframe>'

                });
            }

        }

    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${user.username}
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
            href="${pageContext.request.contextPath}/logout"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">


    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>