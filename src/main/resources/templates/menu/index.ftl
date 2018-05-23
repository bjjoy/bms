<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>资源管理</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx}/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/js/html5shiv.js"></script>
		<script src="${ctx}/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
        <#include "${ctx}/head.ftl" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
                <#include "${ctx}/menu.ftl"/>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">控制台</a>
							</li>
							<li><a href="#">系统管理</a></li>
							<li class="active">资源管理</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">资源列表</h3>
                                        <div class="form-group">
                                            资源名：<input id="name" name="name" type="text"/>
                                        </div>
                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
                                        <@shiro.hasPermission name="system:menu:add">
                                            <button class="btn btn-xs btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                        </@shiro.hasPermission>
                                    </div>
								</div>
                                <div class="space-6"></div>
                                <div class="table-responsive">
                                    <table id="menuListTable"></table>
                                </div>

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
							<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
							<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								Inside
								<b>.container</b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<#include "${ctx}/common.ftl"/>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
            $(document).ready(function () {
                //初始化表格,动态从服务器加载数据
                $("#menuListTable").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/menu/getList",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: true,
                    //每页显示的记录数
                    pageSize: 10,
                    //当前第几页
                    pageNumber: 1,
                    //记录数可选列表
                    pageList: [10, 20],
                    //是否启用查询
                    search: false,
                    //是否启用详细信息视图
                    detailView:false,
                    detailFormatter:detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams:getQueryParams,
                    queryParamsType: "",
                    //json数据解析
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.list,
                            "total": res.data.total
                        };
                    },
                    //数据列
                    columns: [{
                        title: "ID",
                        field: "id",
                        sortable: true
                    },{
                        title: "资源名",
                        field: "name"
                    },{
                        title: "资源标识",
                        field: "permission"
                    },{
                        title: "资源url",
                        field: "href"
                    },{
                        title: "排序",
                        field: "sort"
                    },{
                        title: "类型",
                        field: "type",
                        formatter: function (value, row, index) {
                            if (value == 0)
                                return '<span class="label label-info">菜单</span>';
                            if (value == 1)
                            	return '<span class="label label-primary">子菜单</span>';
                            if (value == 2)
                                return '<span class="label label-danger">按钮</span>';
                        }
                    },{
                        title: "操作",
                        field: "empty",
                        formatter: function (value, row, index) {
                            var operateHtml = '<@shiro.hasPermission name="system:menu:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;</@shiro.hasPermission>';
                            operateHtml = operateHtml + '<@shiro.hasPermission name="system:menu:delete"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';
                            return operateHtml;
                        }
                    }]
                });
            });

            function detailFormatter(index, row) {
                var html = [];
                html.push('<p><b>描述:</b> ' + row.description + '</p>');
                return html.join('');
            }

            function getQueryParams(params){
                var params={
                    "pageNumber":params.pageNumber,
                    "pageSize":params.pageSize,
                    "name":$("#name").val()
                }
                return params;
            }
            function search() {
                var params = {"name":$("#name").val()};
                $('#menuListTable').bootstrapTable("refresh");
            }
            function add(){
                layer.open({
                    type: 2,
                    title: '用户资源',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/menu/add',
                    end: function(index){
                        $('#menuListTable').bootstrapTable("refresh");
                    }
                });
            }
            function edit(id){
                layer.open({
                    type: 2,
                    title: '编辑资源',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/menu/edit/'  + id,
                    end: function(index){
                        $('#menuListTable').bootstrapTable("refresh");
                    }
                });
            }
            function del(id){
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx}/menu/delete/" + id,
                        success: function(msg){
                            if (msg.code == 200) {
                                layer.msg(msg.msg, {time: 2000}, function () {
                                    $('#menuListTable').bootstrapTable("refresh");
                                    layer.close(index);
                                });
                            } else {
                                layer.msg(msg.msg, {time: 2000}, function () {
                                    layer.close(index);
                                });
                            }
                        }
                    });
                });
            }
		</script>
</body>
</html>
