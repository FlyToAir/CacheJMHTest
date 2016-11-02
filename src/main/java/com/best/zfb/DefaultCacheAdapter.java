package com.best.zfb;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class DefaultCacheAdapter<K, V>  {

    private Cache<K, V> cache;
    private long MAX_SIZE = 1024 * 1024;

    /**
     * 使用Guava LocalCache替代DefaultCache，提高并发读写性能
     *
     * @param name        缓存名称
     * @param maxSize     最大缓存数量（非容量）
     * @param maxLifetime 缓存过期时间（msec）
     */
    public DefaultCacheAdapter(String name, long maxSize, long maxLifetime, int concurrentLevel) {
        if(maxSize < 0){
            maxSize = MAX_SIZE;
        }
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(maxLifetime, TimeUnit.MILLISECONDS)
                .concurrencyLevel(concurrentLevel)
                .build();
    }


    public V put(K key, V value) {
        cache.put(key, value);
        return value;
    }

    public V get(Object key) {
        return cache.getIfPresent(key);
    }

}
