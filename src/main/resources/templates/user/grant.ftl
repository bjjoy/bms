<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加角色</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/animate.css" rel="stylesheet">
    <link href="${ct}/bjjoy/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <p>为【${user.loginName}】分配角色</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>选择角色</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal" id="frm" method="post" action="${ctx}/user/update">
                        	<input type="hidden" id="id" name="id" value="${user.id}">
                        	<div class="form-group">
                        		<#list roles as role>
                                <div class="col-sm-12">
                                    <div class="checkbox i-checks">
                                        <label>
                                        	<#if roleIds?seq_contains(role.id)>
                                            	<input type="checkbox" value="${role.id}" name="roleIdList" checked="checked"> <i></i> ${role.name}
                                            <#else>
                                            	<input type="checkbox" value="${role.id}" name="roleIdList"> <i></i> ${role.name}
                                            </#if>
                                        </label>
                                    </div>
                                </div>
                                </#list>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- 全局js -->
    <#include "${ctx}/common.ftl">
    <script type="text/javascript">
    $(document).ready(function () {
	    $("#frm").validate({
    	    rules: {},
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/user/update" ,
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.msg, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index);
	   					});
   	    		   }
   	    		});
            }
    	});
    });
    </script>

</body>

</html>
