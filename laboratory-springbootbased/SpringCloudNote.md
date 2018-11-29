###Eureka笔记

客户端的发现核心类：
`org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient`  
实现接口：`org.springframework.cloud.client.discovery.DiscoveryClient`
依赖接口：`com.netflix.discovery.EurekaClient`。其实现类：`com.netflix.discovery.DiscoveryClient`
依赖接口：`com.netflix.appinfo.EurekaInstanceConfig`。其实现类：`org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean`

`com.netflix.discovery.DiscoveryClient`类用于帮助与Eureka Server互相协作。
Eureka Client负责下面的任务：
－向Eureka Server注册服务实例
－向Eureka Server服务租约
－ 当服务关闭期间， 向Eureka Server取消租约
－查询Eureka Server中的服务实例列表
Eureka Client还需要配置一个Eureka Server的URL列表。

`com.netflix.discovery.endpoint.EndpointUtils`负责获取serviceUrl列表。

###Spring Cloud Config 笔记

1. Server 端：  
   1. POM配置：  
   parent:  
   `org.springframework.boot:spring-boot-starter-parent:2.1.0.RELEASE`  
   dependency management:  
   `org.springframework.boot:spring-cloud-dependencies:Finchley.SR2`  
   dependencies:  
   `org.springframework.cloud:spring-cloud-config-server`  
   `org.springframework.boot:spring-boot-starter-test`
   2. 使用：  
   访问配置：  
   应用名为CloudConfigClient，profile为default的配置`http://localhost:8888/CloudConfigClient/default`  
   规律：`http://localhost8888/{application}/{profile}`  
   
2. Client 端:  
   1. POM配置：  
   parent:  
   `org.springframework.boot:spring-boot-starter-parent:2.1.0.RELEASE`  
   dependency management:  
   `org.springframework.boot:spring-cloud-dependencies:Finchley.SR2`  
   dependencies:  
   `org.springframework.cloud:spring-cloud-starter-config`    
   `org.springframework.boot:spring-boot-starter-actuator`  
   `org.springframework.boot:spring-boot-starter-web`  
   `org.springframework.boot:spring-boot-starter-test`
   
   2. 使用：  
   发送POST请求到localhost:8080/actuator/refresh来重新拉取config server的配置（需要设置请求头Content-Type: application/json）  
   `$ curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"`  

3. Note:  
   + 资源文件选择：
   Server端的资源文件命名时有几个变量可以引用                    
     + `{application}`, which maps to `spring.application.name` on the client side.
     + `{profile}`, which maps to `spring.profiles.active` on the client (comma-separated list).
     + `{label}`, which is a server side feature labelling a "versioned" set of config files.

     用法：
     1. 在Client端bootstrap.yml中定义应用名和profile
     
            spring:
              application:
                name: foo
              profiles:
                active: dev,mysql
     2. 在Server端设置资源路径：
             
             spring:
               cloud:
                 config:
                   server:
                     git:
                       uri: https://github.com/myorg/{application}
     3. Client在向Server发拉取配置的请求时候，  
     会将`spring.application.name`(此处为`foo`)和`spring.profiles.active`（此处按文档应为`mysql`,可是实际测试时是`dev,mysql`）发送过去。  
     Server端在处理时会将`{application}`替换为`foo`，选取`https://github.com/myorg/foo`仓库.  
     *但是Server端会报错：`No custom http config found for URL: https://github.com/myorg/foo/...`。是因为需要针对变量{application}单独设置config吗？*  
     Note:Active profiles take precedence over defaults, and, if there are multiple profiles, the last one wins (similar to adding entries to a Map).  
     
   + SpringBoot与SpringCloud版本的关系：  
   两者没有直接的关系，SpringCloud的版本号为英文单词，集成了很多相互独立的组件，
   从这些个组件中选出可以共同起作用的版本，组成的一个大的集合。SpringBoot即可以
   理解成其中的一份子。两者的版本匹配可以通过官网的SPRING INITIALIZR生成项目，
   链接：`https://start.spring.io/`  
   + Next.
