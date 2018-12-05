<%--
  Created by IntelliJ IDEA.
  User: ckt
  Date: 2018/12/4 0004
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        function addIndex() {
            $("#ff").form("submit", {
                url: "${pageContext.request.contextPath}/addIndex",
                success: function (data) {
                    //alert(data);
                    if (data) {
                        alert("添加成功");
                        location = "${pageContext.request.contextPath}/datagrid/index.jsp";

                    } else {
                        alert("添加失败")
                    }
                }
            })
        }
    </script>
</head>
<body>
<h1>添加文章</h1>
<form id="ff" action="" method="post" enctype="multipart/form-data">
    标题:<input type="text" name="title"><br/>
    作者:<input type="text" name="author"><br/>
    内容:<input type="text" name="content"><br/>
    封面:<input type="file" name="img"><br/>
    日期:<input type="text" name="date"><br/>
    <input type="button" value="提交" onclick="addIndex()"><br/>
</form>


</body>
</html>
