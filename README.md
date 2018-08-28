# 个人博客&云笔记（markdown）
* [www.yaochow.com 可点击查看](http://www.yaochow.com)
* 测试用户名/密码：william/Abcd1234
## 系统
服务器：阿里云ecs CentOS7.4 64位 2核 4G 2Mbps

前端：Nginx:1.14.0  Vue2.5.2

后端：SpringCloud:Finchley.SR1  MongoDB

### 模块
前端由Nginx作为容器运行Vue页面，后端因阿里云的端口限制也是由Nginx作为反向代理服务器。

前端页面分为登陆&注册、笔记列表、笔记内容、新增笔记几个模块。

后端服务通过SpringCloud实现，由eureka作为注册中心，service-zuul作为路由，service-user用户模块，service-core博客&云笔记模块，service-data数据层（MongoDB），service-config配置中心）。

模块之间的Session一致通过Spring集成Redis实现，共享uid。模块之间的调用通过Feign。

### 功能
前端通过控制div的隐藏、显示来实现页面的转换。

登陆页面用户输入用户名、密码惊醒登陆，后端校验成功后完成登陆展现笔记列表模块，在页面缓存10条笔记信息。

笔记列表模块中，点击每一项显示对应的笔记内容，内容可编辑保存，后端更新成功后更新页面当前笔记的缓存信息。

笔记列表最下面可点击新增笔记，展现新增笔记模块，后端成功保存后更新页面缓存数据，返回到笔记列表模块；也可以点击下方取消新增，返回笔记列表模块。

后端通过zuul路由到指定的业务模块：分为user、core（目前只实现了云笔记相关功能、后续会增加博客）进行数据操作。

通过data模块入库MongoDB。

### 版本迭代
* 第一版本：SpringMVC + Jsp + Redis（完成了日记的展现，通过本地工具类上传日记）
* 第二版本：Nodejs + MongoDB （完成了写日记、展现日记）
* 第三版本：Vue + SpringCloud + MongoDB （目前完成了用户注册登陆，写、改、查功能）
### 待完成
日记分类 ect.
