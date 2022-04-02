package demo.zookeeper.client.provider

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZookeeperClientProviderApplication

fun main(args: Array<String>) {
    runApplication<ZookeeperClientProviderApplication>(*args)
}
