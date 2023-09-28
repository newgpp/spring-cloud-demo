### 参考文章
```html
https://www.51cto.com/article/659208.html
https://limingxie.github.io/basic/stack/
```

### redis docker 启动

```shell
# 查找镜像
docker search redis
# 下载镜像
docker pull bitnami/redis:latest
# 启动redis
docker run -p 6379:6379 --name redis -e REDIS_PASSWORD=redis123 bitnami/redis:latest &
```

### reids 数据结构

- Strings
  https://redis.io/docs/data-types/strings/
```html
127.0.0.1:6379> set name:string felix
OK
127.0.0.1:6379> get name:string
"felix"
127.0.0.1:6379> type name:string
string
```
- Lists
  https://redis.io/docs/data-types/lists/
```shell
127.0.0.1:6379> lpush names:list felix
(integer) 1
127.0.0.1:6379> lpush names:list smith linus celia
(integer) 4
127.0.0.1:6379> llen names:list
(integer) 4
127.0.0.1:6379> lrange names:list 0 0
1) "celia"
127.0.0.1:6379> lrange names:list 0 1
1) "celia"
2) "linus"
127.0.0.1:6379> lrange names:list 0 -1
1) "celia"
2) "linus"
3) "smith"
4) "felix"
127.0.0.1:6379> rpop names:list
"felix"
127.0.0.1:6379> lpop names:list
"celia"
127.0.0.1:6379> lrange names:list 0 -1
1) "linus"
2) "smith"
127.0.0.1:6379> type names:list
list
```
- Sets
  https://redis.io/docs/data-types/sets/
```shell
127.0.0.1:6379> sadd names:set felix 
(integer) 1
127.0.0.1:6379> sadd names:set smith felix
(integer) 1
127.0.0.1:6379> smembers names:set
1) "felix"
2) "smith"
127.0.0.1:6379> sismember names:set smith
(integer) 1
127.0.0.1:6379> sismember names:set celia
(integer) 0
127.0.0.1:6379> sadd names:set:1 smith celia
(integer) 2
127.0.0.1:6379> sdiff names:set names:set:1
1) "felix"
127.0.0.1:6379> sdiff names:set:1 names:set
1) "celia"
127.0.0.1:6379> sinter names:set names:set:1
1) "smith"
127.0.0.1:6379> scard names:set
(integer) 2
127.0.0.1:6379> type names:set
set
```
- Hashes
  https://redis.io/docs/data-types/hashes/
```shell
127.0.0.1:6379> hmset names:hash firstName felix lastname zhao
OK
127.0.0.1:6379> hgetall names:hash
1) "firstName"
2) "felix"
3) "lastname"
4) "zhao"
127.0.0.1:6379> hset names:hash age 18
(integer) 1
127.0.0.1:6379> hget names:hash age
"18"
127.0.0.1:6379> type names:hash
hash
```

- Sorted sets
  https://redis.io/docs/data-types/sorted-sets/
```shell
127.0.0.1:6379> zadd names:zset 1 felix
(integer) 1
127.0.0.1:6379> zadd names:zset 3 smith
(integer) 1
127.0.0.1:6379> zadd names:zset 2 celia
(integer) 1
127.0.0.1:6379> zrange names:zset 0 -1
1) "felix"
2) "celia"
3) "smith"
127.0.0.1:6379> zrevrange names:zset 0 -1
1) "smith"
2) "celia"
3) "felix"
127.0.0.1:6379> type names:zset
zset
```

### stream

```html
https://redis.io/commands/?group=stream

https://redis.io/commands/xadd/
https://redis.io/commands/xgroup-create/
https://redis.io/commands/xreadgroup/
https://redis.io/commands/xack/

https://redis.io/commands/xinfo-stream/
https://redis.io/commands/xinfo-groups/
https://redis.io/commands/xinfo-consumers/
```