/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

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

import com.best.zfb.HashMapCache;

//所有线程共享实例，达到并发效果
@State(Scope.Benchmark)
//计算单位时间内操作数量
@BenchmarkMode(Mode.Throughput)
//时间单位
@OutputTimeUnit(TimeUnit.SECONDS)
//并发线程数
@Threads(2)
@Fork(1)
public class Hashbench {

    private HashMapCache<Long, Long> CACHE;

    @Setup
    public void setup(){
        CACHE = new HashMapCache<Long, Long>(
                "BALANCETYPE_ID_PO_CACHE", -1, 1 * 60 * 60 * 1000);
        for (long index = 0; index < 10000; index++) {
            CACHE.put(index, index);
        }
    }

    @Benchmark
    @Warmup(iterations = 20)
    @Measurement(iterations = 100)
    public void testMethod() {
        CACHE.get(1L);
        CACHE.get(5000L);
    }

}
