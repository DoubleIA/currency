package com.doubleia.currency.chp5;

import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

public class Memoizer1<A, V> implements Computable<A, V> {

	/**
	 * 
	 * 使用HashMap和同步机制初始化缓存，Memoizer是一个Computable包装器，帮助记住之前的结果，并将缓存过程封装起来。
	 * 这项技术称为“记忆[Memoization]"。
	 * 当然，Memoizer1并不是我们想要的结果，因为它为了保证线程的安全性，对compute进行了同步，这使得只有一个线程可以进行计算，由于计算时间很长，将导致很多执行compute方法的线程阻塞，并不能提升性能。
	 * 
	 */
	@GuardedBy("this")
	private final Map<A, V> cache = new HashMap<A, V>();
	private final Computable<A, V> c;
	
	public Memoizer1(Computable<A, V> c) {
		this.c = c;
	}
	
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}

}
