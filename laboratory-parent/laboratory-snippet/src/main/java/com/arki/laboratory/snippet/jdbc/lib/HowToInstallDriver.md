1. Oracle driver:  
Download driver from:  
https://www.oracle.com/technetwork/database/application-development/jdbc/downloads/index.html  
假设下载的是ojdbc6的11.2.0.4版本，放在d盘根目录下，执行maven安装命令：
    
       mvn install:install-file -DgroupId=com.oracle.jdbc -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar -Dfile=d:\ojdbc12.jar
    
   之后，在项目中引入：

       <dependency>
           <groupId>com.oracle.jdbc</groupId>
           <artifactId>ojdbc6</artifactId>
           <version>11.2.0.4</version>
       </dependency>
       
2.