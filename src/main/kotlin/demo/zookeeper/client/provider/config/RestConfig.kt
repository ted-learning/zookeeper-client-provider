package demo.zookeeper.client.provider.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfig {

    @LoadBalanced
    @Bean
    fun restWithLoadBalanced(): RestTemplate {
        return RestTemplateBuilder()
            .rootUri("/zookeeper-client-provider")
            .build()
    }

    @Bean
    fun defaultRest(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}