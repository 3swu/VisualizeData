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
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script>
        function validForm(form) {
            for(var i=0;i<form.elements.length;i++){
                if(form.elements[i].type == "text" && form.elements[i].value == "") {
                    alert("输入不能为空");
                    return false;
                }
            }
            $.ajax({
                url:'action/login',
                type:'POST',
                data:{'username':form.elements[0].value,'password':form.elements[1].value},
                success:function(data){
                    alert(data);
                }
            })
        }
    </script>
    <title>Title</title>
</head>
<body>
<div id="login">
    <form action="/account/login" method="post" onsubmit="validForm(this)">
        username:<input name="input-username" id="username"/>
        password:<input name="input-password" id="password"/>
        <button type="submit"/>
    </form>
</div>
</body>
</html>
