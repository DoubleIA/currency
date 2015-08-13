package com.doubleia.currency.chp3;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @date 2015.8.4 14:25
 * @author wangyingbo
 * @description 使用不变性满足同步需求
 * @version 3.0.3
 * 
 * Immutable Object：不可变对象。即某个对象被创建后的状态就不可被修改了。不可变对象一定是线程安全的。
 */
public final class Immutable {
	
	/**
	 * 
	 * 不可变对象固有属性之一就是线程安全性，他们的状态由构造函数来控制。
	 * 
	 * 不可变对象的条件：
	 * 1.对象创建以后其状态就不能修改。
	 * 2.对象的所有域都是final型的。
	 * 3.对象的创建时正确的（创建时，没有this溢出）
	 * 
	 */
	private final Set<String> stooges = new HashSet<String>();
	
	public Immutable() {
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}
	
	public boolean isStooge(String name) {
		return stooges.contains(name);
	}
}
