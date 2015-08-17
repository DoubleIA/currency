package com.doubleia.currency.chp5;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author wangyingbo
 * @version 5.6.3
 *
 */
public class Memoizer3<A, V> implements Computable<A, V> {

	/**
	 * 
	 * Memoizer3首先判断的是future是否已经存在，即该计算是否已经执行，如果没有执行，则创建一个新的FutureTask，并执行计算过程。
	 * 如果已经执行，则获得结果，该结果可能直接返回(执行完毕)，或者等待一段时间才会得到(仍在计算中)。
	 * Memoizer3几乎是完美的，但是仍然有可能导致两个线程计算出同一个值。引发该问题的原因是if仍然不是原子性的，复合操作若没有则添加是在底层的Map对象上执行的，而这个对象无法通过加锁来确保原子性。
	 * 解决方法是使用ConcurrentMap中的原子方法putIfAbsent。
	 * 
	 */
	private Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
	
	private Computable<A, V> c;
	
	public V compute(final A arg) throws InterruptedException {
		Future<V> f = cache.get(arg);
		if (f ==null) {
			Callable<V> eval = new Callable<V>() {
				public V call() throws InterruptedException {
					return c.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(eval);
			f = ft;
			cache.put(arg, ft);
			ft.run(); //调用c.compute(arg);
		}
		try {
			return f.get();
		} catch (ExecutionException e) {
			throw launcherThrowable(e.getCause());
		}
	}
	
	private static RuntimeException launcherThrowable(Throwable cause) {
		if (cause instanceof RuntimeException)
			return (RuntimeException) cause;
		else if (cause instanceof Error)
			throw (Error) cause;
		else
			throw new IllegalArgumentException();
	}

}
