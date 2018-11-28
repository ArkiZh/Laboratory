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
   访问配置：`http://localhost:8888/CloudConfigClient/default`  
   
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
   + SpringBoot与SpringCloud版本的关系：  
   两者没有直接的关系，SpringCloud的版本号为英文单词，集成了很多相互独立的组件，
   从这些个组件中选出可以共同起作用的版本，组成的一个大的集合。SpringBoot即可以
   理解成其中的一份子。两者的版本匹配可以通过官网的SPRING INITIALIZR生成项目，
   链接：`https://start.spring.io/`  
   + Next.
