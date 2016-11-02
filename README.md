# CacheJMHTest
### 目录结构
* benchmark --存放四个benchmark
    * ConcurrentHashbench.java
    * Hashbench.java
    * GuavaDefault.java
    * Guava64.java
* Cache --Q9中Local cache的实现基础代码
* ConcurrentHashMapCache.java --使用ConcurrentHashMap实现的Cache
* DefaultCacheAdapter.java --Guava Cache实现类
* HashMapCache.java--HashMap实现的Cache

### 如何运行
以运行默认并发等级的**Guava Cache**在并发线程数为**16**的情况下的执行步骤
```
mvn clean install
java -jar target/benchmarks.jar GuavaDefault -t 16
```
默认的热身次数为20，如果向指定热身次数，可以在执行指令末尾添加`-w`；默认的执行次数为100，自定义选项为`-i`
