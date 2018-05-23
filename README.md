# service_auth
后台管理系统权限控制，对user、role、resource（通常菜单和按钮）进行增删改查。该系统与mysql交互，定位于服务层，
最好通过接口层利用http请求进行调用。接口层在auth_web项目中。

auth.sql，mysql初始化文件，新建名为auth数据库后，文本编辑器打开直接复制建库语句运行，
或者工具（如Navicat）运行sql文件

auth_service.postman_collection.json文件，postman测试接口文件，用postman->import->import file加载接口信息，
点击运行测试。

This is a web management system to CRUD user, role and resource.
This system is created by java. We used idea as IDE. And we also used springboot, maven, mybatis, mysql and git and so on.
It is only java code for interaction with mysql. We have not edited view.
