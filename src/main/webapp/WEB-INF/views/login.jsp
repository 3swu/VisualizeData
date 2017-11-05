<%--
  Created by IntelliJ IDEA.
  User: wulei
  Date: 2017/10/21
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#alertTxt").hide();
            $("#loginBtn").click(function () {
                $("#alertTxt").hide();
                var username = $("#user_login").val();
                var password = $("#user_pass").val();
                $.ajax({
                    url:'account/login',
                    data:{"username":username,"password":password},
                    type:'POST',
                    success:function (data) {
                        if(data == 'success') {
                            window.location.href = 'main/' + username.slice(0, username.indexOf('@'));
                        }else if(data == 'failed'){
                            $("#alertTxt").show();
                            $("#alertTxt").text('用户名或密码错误!');
                        }
                    }
                });
            });
        });
    </script>
    <style>
        body{
            background-color: #eeeeee;
        }
        #loginDiv{

            margin: 150px auto;
            height: 310px;
            width: 400px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 2px 2px 2px gainsboro;
        }
        .input{
            width: 350px;
            height: 50px;
            border-style: groove;
            border-radius: 5px;
            background-color: #f5f5f5;
            font-size: x-large;
            text-align: center;
            margin-left: 25px;
            margin-bottom: 2px;
        }
        .input_text{
            padding-left: 30px;
            color: gray;
        }
        #loginBtn{
            width: 70px;
            height: 40px;
            margin-left: 165px;
            margin-top: 10px;
            border-radius: 5px;
            border-style: none;
            background-color: #007bff;
            font-size: large;
            color: white;
        }
        #alertTxt{
            color: #ff5346;
            padding-left: 140px;
        }
    </style>
    <title>Title</title>
</head>
<body>
<div id="loginDiv">
    <form>
        <br>
        <p>
            <label for="user_login">
                <a class="input_text">电子邮件地址</a>
                <br>
                <input name="username" id="user_login" class="input">
            </label>
        </p>
        <p>
            <label for="user_pass">
                <a class="input_text">密码</a>
                <br>
                <input name="password" type="password" id="user_pass" class="input">
            </label>
        </p>
    </form>
    <button id="loginBtn">登录</button>
    <p>
        <a id="alertTxt">错误</a>
    </p>
</div>
<a style="margin-top: 95%; color: #7c7b83; margin-left: 40%">不推荐使用IE或Microsoft Edge浏览器</a>
</body>
</html>