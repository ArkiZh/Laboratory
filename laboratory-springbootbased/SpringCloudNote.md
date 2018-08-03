
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
