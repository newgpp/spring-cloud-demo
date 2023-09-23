### minio docker 启动

```shell
docker run -d -p 9000:9000 -p 50000:50000 --name minio \
-e "MINIO_ROOT_USER=minio" -e "MINIO_ROOT_PASSWORD=minio123" \
-v /home/data:/data -v /home/config:/root/.minio \
minio/minio server --console-address ":50000" /data
```