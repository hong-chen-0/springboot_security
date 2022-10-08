部署JAVA项目到AWS-EC2

https://docs.aws.amazon.com/zh_cn/AWSEC2/latest/UserGuide/ec2-lamp-amazon-linux-2.html

根据上文教程，安装phpMyAdmin

https://blog.csdn.net/weixin_39971435/article/details/114494641

根据上文教程，给root用户设置密码 sudo passwd root

切换身份 su root

在usr下创建java文件夹 mkdir java

https://www.cnblogs.com/faunjoe88/p/7942431.html

根据上文教程，修改文件夹权限为可写可读可执行 chmod 777 java

http://www.mrsheng.com/way-to-use-filezilla-to-connect-amazon-aws-ec2/

根据上文教程，打开fillezilla->编辑->设置->连接->SFTP->添加你的密钥（把AWS里的ppk文件导入）->确认

打开 “文件”-> “站点管理器” ， 点击左边的 “新站点”按钮， 在右边“常规”标签中 填入主机信息（即AWS Console中的 Public DNS）,

"协议"中选 "SFTP - SSH FileTransfer Protocol" ,

“登录类型” 选 “交互式”

“用户” 中输入用户名(如果你用的是Amazon Linux，这里的用户名输ec2-user，如果是Ubuntu，这里的用户名是ubuntu，如果是SUSE Linux，这里的用户名是root，如果是Centos，这里的用户名就是centos)

连接成功

https://blog.csdn.net/qq_39071667/article/details/81483364

根据上文教程，从 https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html 下载JDK16.0版本

通过FTP传送到EC2的java文件夹

在命令栏中输入 tar –zxvf jdk-16.0.2_linux-x64_bin.tar.gz 解压jdk到本文件夹

在命令栏中输入 vi /etc/profile 配置环境变量

在文件的最下方加入下文

JAVA_HOME=/usr/java/jdk-16.0.2

CLASSPATH=$JAVA_HOME/lib/

PATH=$PATH:$JAVA_HOME/bin

export PATH JAVA_HOME CLASSPATH

ESC wq 保存

在命令栏中输入 source /etc/profile 使得配置重启生效

查看版本 java -version


mysql修改密码

set password =password('你的密码');

连接aws里EC2的mysql数据库方法如下：

url: jdbc:mysql://127.0.0.1:3306/springboot_lock?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull

打包jar放到java文件夹

java -jar lock.jar 启动项目

入站规则----所有流量，自定义，0.0.0.0

https://www.cnblogs.com/yelao/p/12589098.html

在服务器上持久运行jar

nohup java -jar lock.jar > system.log 2>&1 &

https://www.cnblogs.com/createhappy/p/9375874.html

redis安装

https://blog.csdn.net/u012978507/article/details/118392727
