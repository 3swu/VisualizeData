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
            overflow: auto;
        }
        .maindiv{
            background-color: white;
            border-radius: 5px;
            box-shadow: 2px 2px 2px gainsboro;
        }
        #filelist{

        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.7.2/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/js/charts.js"></script>
</head>

<body>
<div id="head">
    <a id="userDisplay">当前用户：</a>
    <a id="logout" href="javascript:logout()">   退出</a>
</div>
<div id="chartDiv" class="maindiv" style="overflow: auto">此处显示  id "chartDiv" 的内容</div>
<div id="settingDiv" class="maindiv">

    <div id="uploadDiv">
        上传文件<input type="file" name="file" id="uploadfile">
        <button id="uploadBtn">上传</button>
    </div>
    <hr>
    <a>文件列表</a>
    <div id="filelist" style="height: 15%;overflow: auto">
        <div id="filelistform">

        </div>
    </div>
    <a>Sheet列表</a>
    <div id="sheetlist"style="height: 10%;overflow: auto">
        <div id="sheetlistform">

        </div>
    </div>
    <a>属性列表</a>
    <div id="propertylist" style="height: 15%; overflow: auto">
        <div id="propertylistform">

        </div>
    </div>
    <button id="generateChart">生成</button>
</div>
</body>
</html>

