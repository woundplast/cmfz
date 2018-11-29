<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/queryAllMenu",
                type: "post",
                //data:{},
                success: function (data) {
                    <!--菜单处理-->
                    $.each(data, function (index, first) {
                        var sonTitle = '';
                        $.each(first.menus, function (indexs, menus) {
                            sonTitle += "<div style=\"text-align: center;\"" +
                                " onclick=\"openTitle('" + menus.title + "','" + menus.iconCls + "','" + menus.url + "')\">" +
                                "<a  class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-pencil'\" " +
                                "style=\"width: 211px;\">" + menus.title + "</a></div>";

                        });

                        $('#aa').accordion('add', {
                            iconCls: first.iconcls,
                            title: first.title,
                            content: sonTitle,
                            selected: true
                        });

                    });
                },
                dateType: "json"

            });

        });

        function openTitle(title, iconCls, url) {
            //标题页签是否存在
            var isExists = $("#tt").tabs("exists", title);
            if (isExists) {
                //存在
                $("#tt").tabs("select", title);
            } else {
                //不存在
                $("#tt").tabs("add", {
                    title: title,
                    closable: true,
                    iconCls: iconCls,
                    //href:"${pageContext.request.contextPath}"+url,
                    content: '<iframe src="${pageContext.request.contextPath}/datagrid/' + url + '" width="100%" height="100%"></iframe>'


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