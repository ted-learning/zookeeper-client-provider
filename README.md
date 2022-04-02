# zookeeper-client-provider

A demo for spring cloud zookeeper

Spring Gradle Setup


Docker compose
version: '3.1'


services:
  zoo1:
    image: zookeeper
    hostname: zoo1
    ports:
      - 2181:2181
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181


  zoo2:
    image: zookeeper
    hostname: zoo2
    ports:
      - 2182:2181
    environment:
      ZOO_MY_ID: 2
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181


  zoo3:
    image: zookeeper
    hostname: zoo3
    ports:
      - 2183:2181
    environment:
      ZOO_MY_ID: 3
      ZOO_SERVERS: server.1=zoo1:2888:3888;2181 server.2=zoo2:2888:3888;2181 server.3=zoo3:2888:3888;2181

Note: 需要配置本机hosts
#/etc/hosts 
127.0.0.1 zoo1 zoo2 zoo3

服务注册
Spring application.yml
spring:
  application:
    name: zookeeper-client-provider
  cloud:
    zookeeper:
      connect-string: "zoo1:2181,zoo2:2182,zoo3:2183"


server:
  port: 7650

服务发现 Only DiscoveryClient（需要手动实现Load balance）
