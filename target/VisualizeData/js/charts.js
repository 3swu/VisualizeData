
$(document).ready(function () {
    $("#chartDiv").css( 'width', $("#chartDiv").width() );
    $("#chartDiv").css( 'height', $("#chartDiv").height() );
    myChart = echarts.init(document.getElementById('chartDiv'));
    //test

    //displayChart();

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

    $("#generateChart").click(function () {
        console.log(getDataFromContent(sheetContent, columnList));
        displayChart(myChart);
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
    var content;
    $.ajax({
        url:'/file/getContent/' + fileName + '/' + sheetName,
        type:'GET',
        success:function (data) {
            sheetContent = data;
            content = data;
            loadColumnList(data);
            for(var i in columnList){
                $("#propertylistform").append(
                    '<label><input type="checkbox" class="propertycheckbox" name="propertybox" onclick="propboxchange(this)" id="propbox'+i+'">'+columnList[i]+'</label>'
                )
            }
        }
    })

    //test
    //console.log(getDataFromContent());
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
    //test
    //console.log(dataObj);
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

function getDataFromContent(content, column , proplist){
    var dataArray = new Array();
    parsedContent = JSON.parse(content);

    for(var i in parsedContent){

        var nowDataArray = new Array();
        for(var j in column){

            if(proplist.has(column[j])) {
                nowDataArray.push(parsedContent[i][column[j]]);
            }
        }
        dataArray.push(nowDataArray);
    }
    return dataArray;
}

function displayChart(myChart) {
    var option = {
        backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
            offset: 0,
            color: '#f7f8fa'
        }, {
            offset: 1,
            color: '#cdd0d5'
        }]),
        title:{
            text: 'Demo'
        },
        legend:{
            right: 10,
            data: '总分'
        },
        xAxis: {
            splitLine:{
                lineStyle:{
                    type: 'dashed'
                }
            }
        },
        yAxis:{
            splitLine:{
                lineStyle:{
                    type: 'dashed'
                }
            },
            scale: true
        },
        series:{
            name: '总分',
            data: function () {
                return getDataFromContent(sheetContent, columnList, propboxlist);
            },
            type: 'scatter',
            symbolSize: function (data) {
                return data[columnList.indexOf('总分')];
            },
            label:{
                emphasis:{
                    show:true,
                    formatter: function (param) {
                        return param[columnList.indexOf('资源名称')];
                    },
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(120, 36, 50, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(251, 118, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(204, 46, 72)'
                    }])
                }
            }
        }
    }

// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

