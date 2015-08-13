package com.doubleia.currency.chp4;

import java.util.Vector;

import net.jcip.annotations.ThreadSafe;


/**
 * 
 * @description 在现有的线程安全类中添加功能
 * @author wangyingbo
 * @version 4.4.0
 *
 * @param <E>
 */
@SuppressWarnings("serial")
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

	/**
	 * 
	 * Put-If-Absent 确保操作的原子性
	 * 扩展的方法比直接将代码添加到类中更加脆弱，因为现在的同步策略实现被分布到多个单独维护的代码文件中。
	 * 如果底层改变了它的同步策略，那么子类会被破坏，因为同步策略改变后它无法再使用正确的锁来控制，对基类状态的并发访问。
	 * 
	 */
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent) {
			add(x);
		}
		return absent;
	}
	
	
}
