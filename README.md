# checkupdate
检查更新是任何app都会用到功能，任何一个app都不可能第一个版本就能把所有的需求都能实现，通过不断的挖掘需求迭代才能使app变的越来越好，这就需要软件不停升级。检查更新自动下载安装分以下几个步骤:
- 请求服务器判断是否有最新版本（通过versionCode）
- 如果有最新版本，就把最新的apk文件下载到本地
- 下载完成之后给系统发起一个安装的Intent。

# 效果图
![checkupdate](https://github.com/ansen666/checkupdate/blob/master/jks/checkupdate.gif?raw=true)
