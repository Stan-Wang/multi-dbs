

多数据库连接管理工具
-----------------------------------------
	
	
##概览

>基于Hibernate的多数据库管理工具包.(计划支持其他ORM和原生连接 TODO)
	
	
	
##主要结构

>主库和多个数据分库;
>主库存储分库连接信息和数据索引;
>索引可自定义实现,并通过实现DbFilter接口自定义数据分片规则;
	
	
	
##DEMO

>详见 com.stan.util.multidbs.App 实现
	