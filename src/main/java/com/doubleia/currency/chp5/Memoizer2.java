package com.doubleia.currency.chp5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author wangyingbo
 * @version 5.6.2
 *
 */
public class Memoizer2<A, V> implements Computable<A, V> {

	/**
	 * 
	 * Memoizer2比Memoizer1具有更好的并发行为，因为用ConcurrentHashap代替了HashMap，多线程可以并发的访问它。
	 * 但使用ConcurrentHashMap时仍然有一些不足，因为某些情况下会使相同的计算重复两次，原因是当某一个线程计算时，而另一个线程也刚好要进行同一个计算，但是它却不知道已有线程在计算结果了。
	 * 这就导致了相同计算进行了多次的情况，对于只提供单次初始化对象缓存来说，这个漏洞就会带来安全风险。
	 * 我们期望的是当要进行已在执行的计算时，不进行重新计算，而是等到已执行的计算完成，再返回结果。可以使用FuterTask。
	 * 
	 */
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	
	private final Computable<A, V> c;
	
	public Memoizer2(Computable<A, V> c) {
		this.c = c;
	}
	
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}

}
