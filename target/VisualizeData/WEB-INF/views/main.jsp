<%--
  Created by IntelliJ IDEA.
  User: wulei
  Date: 2017/10/22
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="POST" action="/file/uploadfile" enctype="multipart/form-data">
    File to upload:<input type="file" name="file">
    <input type="submit" value="Upload">
</form>


</body>
</html>
-->
<html>
<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
    <style>
        body{
            background-color: #EEEEEE;
        }
        #chartDiv{
            background-color: #FFFFFF;
            width: 65%;
            height: 95%;
            min-height: 550px;
            float: left;
        }
        #settingDiv{
            float: right;
            width: 33%;
            height: 95%;
            min-height: 550px;
            background-color: #FFFFFF;
        }
        .maindiv{
            background-color: white;
            border-radius: 5px;
            box-shadow: 2px 2px 2px gainsboro;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script>
        $(document).ready(function () {
            loadFileList();
        });

        function loadFileList(){
            $.ajax({
                url:'http://localhost:8080/file/getfilelist',
                type:'GET',
                success:function(data){
                    var filelistobj = JSON.parse(data);
                    for(var i in filelistobj){
                        $("#filelistform").append(
                            '<input type="radio" class="fileradio" name="radio" id="file'+i+'">'+filelistobj[i]['fileName']+'</input><br>'
                        )
                    }
                }
            })
        }
        
        function uploadfile() {
            var formData = new FormData();
            formData.append("file",document.getElementById("file").files[0]);
            $.ajax({
                url:'file/uploadfile',
                type:'POST',
                data:formData,
                processData:false,
                contentType:false,
                success:function(data){
                    alert(data.slice(0,6));
                    if(data.slice(0,6) == 'success'){
                        loadFileList();
                    }
                }
            })
        }
    </script>
</head>

<body>
<div id="head">此处显示  id "head" 的内容</div>
<div id="chartDiv" class="maindiv">此处显示  id "chartDiv" 的内容</div>
<div id="settingDiv" class="maindiv">

    <form method="POST" action="/file/uploadfile" enctype="multipart/form-data">
        上传文件<input type="file" name="file" id="uploadfile">
        <button onclick="uploadfile()">上传</button>
    </form>
    <hr>
    <a>文件列表</a>
    <div id="filelist">
        <form id="filelistform">

        </form>
    </div>
</div>
</body>
</html>

