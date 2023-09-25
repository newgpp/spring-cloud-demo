### minio docker 启动

```shell
# 查找镜像
docker search redis
# 下载镜像
docker pull bitnami/redis:latest
# 启动redis
docker run -p 6379:6379 --name redis -e REDIS_PASSWORD=redis123 bitnami/redis:latest &
```