springboot整合ElasticSearch分为两种：
一种直接使用官方的集成工具（引用spring-boot-starter-data-elasticsearch依赖），然后通过hibernate JPA规范，dao层继承ElasticsearchRepository，
controller依赖注入这个dao接口，就相当于对索引库操作了。
另一种是自己写Client连接。如本知识点。
另外还有一个比较厉害的功能，Elasticsearch的聚合,聚合主要实现的是对数据的统计、分析

参考案例：
https://www.jianshu.com/p/1fbfde2aefa5
https://www.cnblogs.com/lifengdi/archive/2019/09/20/11554923.html


1.使用kibana 查看索引里的数据
教程： https://www.cnblogs.com/c1024/p/11012040.html
搭建集群：
https://www.jianshu.com/p/57173b453a01

kibana:  192.168.238.200:5601

1.下载 logstash
https://www.elastic.co/cn/downloads/past-releases/logstash-6-5-1