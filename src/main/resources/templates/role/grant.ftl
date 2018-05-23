<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>分配资源</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/animate.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/style.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <p>为【${role.name}】分配资源</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>选择资源</h5>
                    </div>
                    <div class="ibox-content">
                        <ul id="tree" class="ztree"></ul>
                    </div>
                </div>
                <div class="col-sm-6 col-sm-offset-6">
                    <button class="btn btn-primary" type="button" id="btnSave">提交</button>
                </div>
            </div>
        </div>

    </div>
    <!-- 全局js -->
    <#include "${ctx}/common.ftl">
    <script src="${ctx}/bjjoy/js/zTree/jquery.ztree.all.min.js"></script>
    <script type = "text/javascript">
        var setting = {
            check : {
                enable : true
            },
            data : {
                simpleData : {
                    enable : true
                }
            }
        };
        setting.check.chkboxType = {
            "Y" : "ps",
            "N" : "s"
        };
        $(document).ready(function() {
            $.ajax({
                type : "GET",
                url : "${ctx}/menu/tree/" + ${role.id},
                dataType : 'json',
                success : function(msg) {
                    $.fn.zTree.init($("#tree"), setting, msg);
                }
            });

            $("#btnSave").click(function (){
                var treeObj = $.fn.zTree.getZTreeObj("tree");
                var nodes = treeObj.getCheckedNodes(true);
                var selectIds="";
                for(var index in nodes){
                    var item=nodes[index];
                    selectIds+=item.id+"$$";
                }
                $.ajax({
                    url : "${ctx}/role/update/grant",
                    type : "post",
                    dataType : "json",
                    data : {"menuIds":selectIds,"id":${role.id}},
                    success : function(msg) {
                        layer.msg(msg.message, {time: 2000},function(){
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);
                        });
                    },
                    error : function(r,s,m) {
                    }
                });

            });
        });

    </script>

</body>

</html>
