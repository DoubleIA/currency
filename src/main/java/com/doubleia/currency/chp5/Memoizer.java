package com.doubleia.currency.chp5;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author wangyingbo
 * @version 5.6.0
 *
 */
public class Memoizer<A, V> implements Computable<A, V> {

	/**
	 * 
	 * 最终版本，经过Memoizer1、2、3后的完善版本
	 * 当缓存的不是值而是Future时，将导致缓存污染问题，当某个计算过程被取消或失败，需要把Future在缓存中移除出去。如果检测到RuntimeException，那么也会把Future移除出去，这样将来的计算才会成功。
	 * 可以通过子类来实现为每个元素添加逾期时间的功能
	 * 
	 */
	private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> c;
	
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}
	
	public V compute(final A arg) throws InterruptedException {
		while (true) {
			Future<V> f = cache.get(arg);
			if (f ==null) {
				Callable<V> eval = new Callable<V>() {
					public V call() throws InterruptedException {
						return c.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft);
				if (f == null) { f = ft; ft.run(); }
			}
			try {
				return f.get();
			} catch (CancellationException e) {
				cache.remove(arg, f);
			} catch (ExecutionException e) {
				throw launcherThrowable(e.getCause());
			}
		}
	}
	
	//TODO RuntimeException是否将Future移除了呢。
	private static RuntimeException launcherThrowable(Throwable cause) {
		if (cause instanceof RuntimeException)
			return (RuntimeException) cause;
		else if (cause instanceof Error)
			throw (Error) cause;
		else
			throw new IllegalArgumentException();
	}
	
	
	
}
