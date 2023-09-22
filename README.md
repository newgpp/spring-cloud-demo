# spring-cloud-demo

### 版本信息

| 组件           | 版本             | 备注 |
|--------------|----------------| --- |
| spring cloud | Hoxton.SR12    |  |
| spring boot  | 2.3.12.RELEASE |  |
| xxl job      | 2.4.1          |  |

### 启动顺序

- micro-eureka
- micro-config-server

### micro-govern

- eureka
```html
https://cloud.spring.io/spring-cloud-netflix/reference/html/
```

- micro-config-server
```html
https://docs.spring.io/spring-cloud-config/docs/current/reference/html
```

```bash
# 拉取配置文件
curl http://127.0.0.1:8762/common/template/master
```

### micro-trace

```bash
# feign & traceId
curl 'http://127.0.0.1:8763/order/get?orderId=11111'

# get traceId
curl http://localhost:8763/order/traceId
```

- Spring Cloud OpenFeign
```html
https://cloud.spring.io/spring-cloud-openfeign/reference/html/

https://www.baeldung.com/spring-cloud-openfeign
```
- Spring Cloud Sleuth
```html
https://docs.spring.io/spring-cloud-sleuth/docs/2.2.8.RELEASE/reference/html/

https://www.baeldung.com/spring-cloud-sleuth-single-application
https://www.baeldung.com/spring-cloud-sleuth-get-trace-id
```

### micro-schedule

- micro-xxljob-server
```html
# manage url
http://127.0.0.1:8765/xxl-job-admin

# forked from
https://github.com/xuxueli/xxl-job
```
