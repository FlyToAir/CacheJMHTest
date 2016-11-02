package com.best.zfb.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import com.best.zfb.DefaultCacheAdapter;

//所有线程共享实例，达到并发效果
@State(Scope.Benchmark)
//计算单位时间内操作数量
@BenchmarkMode(Mode.Throughput)
//时间单位
@OutputTimeUnit(TimeUnit.SECONDS)
//并发线程数
@Threads(2)
@Fork(1)
public class Guava64 {

    private DefaultCacheAdapter<Long, Long> BALANCETYPE_ID_PO_CACHE;


    @Setup
    public void setup() {
        BALANCETYPE_ID_PO_CACHE = new DefaultCacheAdapter<Long, Long>("BALANCETYPE_ID_PO_CACHE", -1,
                1 * 60 * 60 * 1000, 64);
        for (long index = 0; index < 10000; index++) {
            BALANCETYPE_ID_PO_CACHE.put(index, index);
        }
    }

    @Benchmark
    //热身20次
    @Warmup(iterations = 20)
    //正式迭代100次
    @Measurement(iterations = 100)
    public void getBalanceType() throws Exception {
        BALANCETYPE_ID_PO_CACHE.get(1L);
        BALANCETYPE_ID_PO_CACHE.get(5000L);
    }

}