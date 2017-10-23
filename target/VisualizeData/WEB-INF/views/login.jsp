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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
       // var path_content = "http://localhost:8080/VisualizeData/";
        function validForm(form) {
            for(var i=0;i<form.elements.length;i++){
                if(form.elements[i].type == "text" && form.elements[i].value == "") {
                    alert("输入不能为空");
                    return false;
                }
            }
            $.ajax({
                url:'account/login',
                type:'POST',
                data:$("#form").serialize(),
                success:function(){
                    $.ajax({
                        url:form.elements[0].value,
                        type:'POST'
                    })
                }
            })

        }
    </script>
    <title>Title</title>
</head>
<body>
<div id="login">
    <form id="form" action="/account/login" method="post" onsubmit="validForm(this)">
        username:<input name="username" id="username"/><br/>
        password:<input name="password" id="password"/><<br/>
        <button type="submit" id="formsubmit"/>
    </form>
</div>
</body>
</html>
