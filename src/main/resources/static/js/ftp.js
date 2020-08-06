$(document).ready(function(){
    $.ajax({
        url : "../api/ftp.do",
        type : "GET",
        dataType:"JSON",
        success : function(response) {
            $('#ftpFileList').bootstrapTable({
                data:response.resultData,
                dataType:'json',
                toolbar: '#toolbar',//工具按钮用哪个容器
                //分页
                pagination: true,  //是否显示分页（*）
                pageNumber: 1,   //初始化加载第一页，默认第一页
                pageSize: 12, //每页的记录行数（*）
                pageList: [5, 10, 15, 20],
                //检索
                sidePagination: "client",//分页方式：client客户端分页，server服务端分页（*）
                search: true,//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                strictSearch: false,//设置为 true启用 全匹配搜索，否则为模糊搜索
                showRefresh: false,  //是否显示刷新按钮
                clickToSelect: true, //是否启用点击选中行
                //导出数据
                // showExport: true,
                // exportDataType: 'all',
                // maintainSelected: true,             //设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
                // buttonsAlign:"right",  //按钮位置
                // exportTypes:[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'],  //导出文件类型
                columns: [{
                    field: 'ftpFileId',
                    title: 'id'
                }, {
                    field: 'ftpFileName',
                    title: 'FileName',
                    formatter: aFormatter //添加超链接的方法
                },
                ],
            });
        }
    });
});

function aFormatter(value, row, index) {
    var url="../api/download.do?ftpFileName="+value;
    return [
        '<a id="ftpFileName" href='+url+'>' +value+'</a>'
    ].join("");
}
