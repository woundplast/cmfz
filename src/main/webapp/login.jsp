<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <script type="text/javascript">


        $(function () {
            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                //alert("自己做");
                $(this).prop("src", "getKaptcha?t=" + new Date().getTime());
            });

        });

        /*===验证用户名===*/
        function textphoneNum() {
            var textphoneNum = $("#phoneNum").val();
            if (textphoneNum.length < 1) {
                $("#phoneNumspan").html("不能为空");
                return false;
            } else {
                $("#phoneNumspan").html("");
                return true;
            }
            return true;
        }

        /*===验证用户名END===*/

        /*===验证密码===*/
        function textpassword() {
            var textpassword = $("#password").val();
            if (textpassword.length < 1) {
                $("#passwordspan").html("不能为空");
                return false;
            } else {
                $("#passwordspan").html("");
                return true;
            }
        }

        /*===验证密码END===*/


        /*===验证码===*/
        function tesxtCode() {
            var textCode = $("#enCode").val();
            if (textCode.length < 1) {
                $("#textCode").html("不能为空");
                return false;
            } else {
                $("#textCode").html("");
                return true;
            }

        }

        /*===验证码END===*/

        function checkform() {
            if (!textphoneNum() || !textpassword() || !tesxtCode()) {
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post" onsubmit="return checkform()">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input type="text" id="phoneNum" name="phoneNum" class="text" value="" maxlength="20"
                           onblur="textphoneNum()"/>
                    <span id="phoneNumspan"></span>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input type="password" id="password" name="password" class="text" value="" maxlength="20"
                           onblur="textpassword()"
                           autocomplete="off"/>
                    <span id="passwordspan"></span>

                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="textcode" class="text captcha" maxlength="4" autocomplete="off"
                           onblur="tesxtCode()"/>
                    <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/getKaptcha"
                         title="点击更换验证码"/>
                    <div id="textCode"></div>
                    <span style="font-size: 10px;color: red; font-weight: bolder;">${requestScope.errorMessage }</span>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value=""
                           onclick="location.href='${pageContext.request.contextPath}/main/main.jsp'"><input
                        type="submit"
                        class="loginButton"
                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>