package com.doubleia.currency.chp4;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 
 * @date 2015.8.6 14:50
 * @author wangyingbo
 * @description 实例封闭
 * @version 4.2.0
 *
 */
@ThreadSafe
public class InstanceConfinement {

	/**
	 * 
	 *封装简化了线程安全类的实现过程，他提供了一种实例封闭（instance confinement）机制，将一个对象封装到另一个对象中时，能够访问被封装对象的所有代码路径都是可知的。
	 *通过将封闭机制与合适的加锁策略结合起来，可以确保以线程安全的方式来使用非线程安全的对象。即，将数据封装在对象内部，可以将数据的访问封装在对象方法上，从而更容易使得线程在访问数据时总能持有正确的锁。 
	 * 
	 * 
	 * 通过封闭机制来保证线程安全:*
	 * InstanceConfinement的状态（mySet）是通过HashSet来管理，虽然HashSet不是线程安全的，但由于mySet是私有的，且没不会逸出，因此HashSet被封闭在InstanceConfiinement中，
	 * 唯一访问mySet的代码的路径是addObject和contiansObject，在执行他们时都需要获得InstanceConfiinement上的锁。InstanceConfiinement的状态完全由它的内置锁所保护，因此
	 * InstanceConfiinement是线程安全的类。
	 * 
	 * 当然，在从InstanceConfiinement类中获得Object对象时，仍然需要使用同步。
	 * 
	 * 实例封装时线程安全类的一个最简单的方式，只要保证同一个状态使用相同的锁来保护即可。 实例封装又不失灵活性，不同的状态可以由不同的锁来保护。
	 * 
	 * 如果一个本该被封闭的对象被发布出去，同样会破坏封闭性。当发布其他对象时，如迭代器或内部类实例，可能会间接发布被封装对象，同样会使被封闭对象逸出。
	 * 
	 * 总结：封闭机制更易于构造线程安全的类，因为当封闭类的状态时，在分析类的线程安全性时，就无需检查整个程序。
	 * 
	 */
	@GuardedBy("this")
	private final Set<Object> mySet = new HashSet<Object>();
	
	public synchronized void addObject(Object object) {
		mySet.add(object);
	}
	
	public synchronized boolean containsObject(Object object) {
		return mySet.contains(object);
	}
	
	/**
	 * 
	 *Java监视器模式：*
	 *遵循 Java监视器模式的对象会把对象的所有可变状态都封装起来，并由自己的内置锁来保护。
	 *
	 * vector以及HashTable都使用了监视器模式
	 * 
	 * 示例：车辆追踪*
	 * 
	 * 见MonitorVehicleTracker类
	 * 
	 */
	
	
	
}
