package demo.zookeeper.client.provider.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@SpringBootTest
internal class HelloWorldControllerTest{
    @Autowired
    lateinit var restWithLoadBalanced: RestTemplate
    @Autowired
    lateinit var defaultRest: RestTemplate
    @Autowired
    lateinit var discoveryClient: DiscoveryClient

    @Disabled
    fun `test hello world`() {
        for (i in 1..100) {
            Thread.sleep(1000)
            try {
                val response = restWithLoadBalanced.getForObject<Map<String, String>>("/hello")
                Assertions.assertThat(response["data"]).isEqualTo("hello world")
                println("$i.succeed")
            } catch (e: Exception) {
                println("$i.failed")
            }
        }
    }

    @Test
    fun serviceUrl() {
        for (i in 1..100) {
            Thread.sleep(1000)
            try {
                val list = discoveryClient.getInstances("zookeeper-client-provider").map {
                    it.uri
                }
                val response = defaultRest.getForObject<Map<String, String>>("${list.random()}/hello")
                Assertions.assertThat(response["data"]).isEqualTo("hello world")
                println("$i.succeed, size: ${list.size}, nodes: $list")
            } catch (e: Exception) {
                println("$i.failed")
            }
        }
    }
}