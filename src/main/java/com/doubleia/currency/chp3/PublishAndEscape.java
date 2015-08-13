package com.doubleia.currency.chp3;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @date 2015.8.4 9:52
 * @author wangyingbo
 * @version 3.0.1
 *
 */
public class PublishAndEscape {
	
	/**
	 * 
	 * 发布一个对象最简单的方法是
	 * 将该对象的引用保存到一个共有的静态变量中
	 * 发布一个对象可能会间接发布其他对象
	 * 将一个Secret对象添加到集合knownSecret中
	 * 也会发布该对象
	 * 
	 * 不安全的发布，会导致可见性问题
	 * 
	 */
	public static Set<Object> knownSecret;
	
	public void initialize() {
		knownSecret = new HashSet<Object>();
	}
	
	/**
	 * 
	 * UnsafeStatus 会导致逸出
	 * 发布了一个本应为私有的状态数组
	 * 逸出，任何对象都可访问该私有数组
	 * 当发布一个对象时，该对象非私有域中引用的所有对象也都会发布
	 * 已发布的对象通过非私有引用或方法可以访问到的对象也都会发布
	 * 
	 */
	private String[] status = new String[] { "AK", "AL" };
	
	public String[] getStatus() { return status; }
	
	/**
	 * 
	 * ThisEscape
	 * 发布内部类实例
	 * 隐含的发布了PublishAndEscape实例本身
	 * 因为EventListener实例隐含对PublishAndEscape实例的隐含引用
	 * 
	 * 隐式发布this
	 * 
	 */
	/*
	public PublishAndEscape(EventSource source) {
		source.registerListener(
			new EventListener() {
				public void onEvent(Event e) {
					doSomething(e);
				}
			}
		);
	}
	*/
	
	/**
	 * 
	 * SafeListener
	 * 如果想在构造方法中注册一个事件监听或启动一个线程
	 * 可以使用私有构造方法和共有工厂方法来正确发布对象
	 * 
	 */
	/*
	private final EventListener listener;
	
	private PublishAndEscape() {
		listener = new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}
		};
	}
	
	public static PublishAndEscape newInstance(EventSource source) {
		PublishAndEscape safe = new PublishAndEscape();
		source.registerListener(safe.listener);
		return safe;
	}
	*/
}
