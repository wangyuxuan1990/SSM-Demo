# SSM-Demo

webSocket需要导入jar包到本地Maven库：

在ueditor/jsp/lib目录下打开maven命令窗口

输入以下命令

mvn install:install-file -Dfile=ueditor-1.1.2.jar -Dpackaging=jar -DgroupId=com.baidu.ueditor -DartifactId=ueditor -Dversion=1.1.2
