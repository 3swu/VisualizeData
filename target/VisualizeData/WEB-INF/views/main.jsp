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
            loadFileList();//页面加载完毕后加载文件列表

            /**
             * 文件上传
             */
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

            $.ajax({
                url:'http://localhost:8080/account/nowuser',
                type:'GET',
                success:function (data) {
                    var dataObj = JSON.parse(data);
                    $("#userDisplay").append(dataObj['username']);
                }
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

        var filelistobj;//文件列表
        var fileSheetList;//文件的Sheet列表
        var fileName;//当前选定的文件名
        var propboxlist = new Set();//属性数组
        var columnList;
        var sheetContent;
        /**
         * 从服务器读取用户文件列表并且加载到页面
         */
        function loadFileList(){
            $("#filelistform").empty();
            $.ajax({
                url:'http://localhost:8080/file/getfilelist',
                type:'GET',
                success:function(data){
                    filelistobj = JSON.parse(data);
                    for(var i in filelistobj){
                        $("#filelistform").append(
                            '<label><input type="radio" class="fileradio" name="radio" onclick="radioChange(this)" id="file'+i+'">'+filelistobj[i]['fileName']+'</input></label><br>'
                        )
                    }
                }
            })
        }



        /**
         * 文件列表中的单选框选定之后，读取当前选定文件的Sheet列表并加载到页面
         * @param radio
         */
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
                            '<label><input type="radio" class="sheetradio" name="sheetradio" onclick="sheetRadioChange(this)" id="sheet'+i+'">'+fileSheetList[i]+'</input></label>'
                        )
                    }
                }
            })

        }

        /**
         * Sheet列表的单选框选定之后，读取页面的属性列表并加载到页面
         * @param radio
         */
        function sheetRadioChange(radio) {
            $("#propertylistform").empty();
            var sheetName = fileSheetList[radio.id.slice(5,radio.id.length)];
            $.ajax({
                url:'/file/getContent/' + fileName + '/' + sheetName,
                type:'GET',
                success:function (data) {
                    sheetContent = data;
                    loadColumnList(data);
                    for(var i in columnList){
                        $("#propertylistform").append(
                            '<label><input type="checkbox" class="propertycheckbox" name="propertybox" onclick="propboxchange(this)" id="propbox'+i+'">'+columnList[i]+'</label>'
                        )
                    }
                }
            })
        }

        /**
         * 维持选中属性数组
         */
        function propboxchange(box) {
            var columnName = columnList[box.id.slice(7,box.id.length)];

            if(propboxlist.has(columnName)){
                propboxlist.delete(columnName);
            }else{
                propboxlist.add(columnName);
            }
            propboxlist.forEach(function (t) {
                console.log(t);
            })
        }

        /**
         * 加载Sheet页面的列名（属性）
         * @param data
         */

        function loadColumnList(data) {
            dataObj = JSON.parse(data);
            columnList = new Array();
            for(var key in dataObj[0]){
                columnList.push(key);
            }
        }

        /**
         * 退出登录
         * 页面重定向到登录页面
         */
        function logout(){
            $.ajax({
                url:'http://localhost:8080/account/logout',
                type:'GET',
                success:function () {
                    window.location.href = 'http://localhost:8080/showlogin';
                }
            })
        }
    </script>
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
</div>
</body>
</html>

