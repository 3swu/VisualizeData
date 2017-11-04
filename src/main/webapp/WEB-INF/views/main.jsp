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
    <script>
        $(document).ready(function () {
            loadFileList();

            $("#uploadBtn").click(function () {
                var formData = new FormData();
                formData.append('file',$('#uploadfile')[0].files[0]);
                $.ajax({
                    url:'http://localhost:8080/file/uploadfile',
                    type:'POST',
                    cache:false,
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function (data) {
                        if(data.slice(0,7) == 'success') {
                            loadFileList();
                        }
                        else if(data == 'file existed'){
                            alert('文件已存在');
                        }
                        else if(data == 'failed'){
                            alert('上传失败');
                        }
                    }
                })
            })

//            $(".fileradio").change(function () {
//                alert($(this).id);
//                var fileId = $(this).id.slice(3,$(this).id.length);
//                alert(fileId);
////                $.ajax({
////                    url:'http://localhost:8080/file/getFileSheetList/'++'/tag'
////                })
//            })
        });

        var filelistobj;
        function loadFileList(){
            $("#filelistform").empty();
            $.ajax({
                url:'http://localhost:8080/file/getfilelist',
                type:'GET',
                success:function(data){
                    filelistobj = JSON.parse(data);
                    for(var i in filelistobj){
                        $("#filelistform").append(
                            '<input type="radio" class="fileradio" name="radio" onclick="radioChange(this)" id="file'+i+'">'+filelistobj[i]['fileName']+'</input><br>'
                        )
                    }
                }
            })
        }

        var fileSheetList;
        var fileName;
        function radioChange(radio) {
            $("#sheetlistform").empty();
            fileName = filelistobj[radio.id.slice(4,radio.id.length)]['fileName'];
            $.ajax({
                url:'http://localhost:8080/file/getFileSheetList/'+ fileName +'/tag',
                type:'GET',
                success:function (data) {
                    fileSheetList = JSON.parse(data);
                    for(var i in fileSheetList){
                        $("#sheetlistform").append(
                            '<input type="radio" class="sheetradio" name="sheetradio" onclick="sheetRadioChange(this)" id="sheet'+i+'">'+fileSheetList[i]+'</input><br>'
                        )
                    }
                }
            })

        }

        function sheetRadioChange(radio) {
            $("#chartDiv").empty();
            var sheetName = fileSheetList[radio.id.slice(5,radio.id.length)];
            $.ajax({
                url:'/file/getContent/' + fileName + '/' + sheetName,
                type:'GET',
                success:function (data) {
                    $("#chartDiv").append(data);
                }
            })
        }

    </script>
</head>

<body>
<div id="head">此处显示  id "head" 的内容</div>
<div id="chartDiv" class="maindiv" style="overflow: auto">此处显示  id "chartDiv" 的内容</div>
<div id="settingDiv" class="maindiv">

    <div id="uploadDiv">
        上传文件<input type="file" name="file" id="uploadfile">
        <button id="uploadBtn">上传</button>
    </div>
    <hr>
    <a>文件列表</a>
    <div id="filelist" style="height: 20%;overflow: auto">
        <div id="filelistform">

        </div>
    </div>
    <a>Sheet列表</a>
    <div id="sheetlist"style="height: 20%;overflow: auto">
        <div id="sheetlistform">

        </div>
    </div>
</div>
</body>
</html>

