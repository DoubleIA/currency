package com.doubleia.currency.chp4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 
 * @date 2015.8.5 17:32
 * @author wangyingbo
 *	@description 设计线程安全的类，希望将一些现有的线程安全的组件组合为更大规模的组件或程序。对象的组合，组合模式的学习，使类更容易成为线程安全的，并且维护这些类时不会无意中破坏线程安全性。
 * @version 4.1.0
 * 
 */
public class ThreadSafeClass {
	
	/**
	 * 
	 * 设计线程安全类时，需要考虑三个要素： *
	 * 1.找出构成对象状态的所有变量。
	 * 2.找出约束状态变量的不变性条件。
	 * 3.建立对象状态的并发访问管理策略。
	 * 
	 */
	@ThreadSafe
	public final class Counter {
		@GuardedBy("this") private long value = 0;
		
		public synchronized long getValue() {
			return value;
		}
		
		public synchronized long increment() {
			if (value == Long.MAX_VALUE)
				throw new IllegalStateException();
			return value++;
		}
	
	}
	/**
	 * 
	 * 如果不了解对象的不变性条件与后验条件，那么就不能确保线程安全性。
	 * 要满足在状态变量的有效值或状态转化上的各种约束条件，就需要借助原子性与封装性。
	 * 
	 * 依赖状态的操作：*
	 * 不变性条件与后验条件约束了对象上有哪些状态或状态转化是可变的。
	 * 而某些对象的方法中还包含一些基于状态的先验条件，那么，这些方法的操作称为依赖状态的操作。
	 * 
	 * 等待某个条件为真的各种内置机制都与内置加锁机制紧密关联。
	 * Blocking Queue， Semaphore
	 * 
	 * 状态所有权：*
	 * 所有权与封装性总是相关联的，对象封装它所拥有的状态，反之也成立，即对他封装的状态拥有所有权。
	 * 状态变量的所有者将决定采用何种加锁方式来维持变量状态的完整性。
	 * 如果发布了某个可变对象的引用，那么就不再独占所有权，最多是共享所有权。
	 * 对于从构造方法和函数中传进来的对象，类通常不拥有这些对象，除非特意设计为转移对象的所有权。
	 * 
	 * 容器类通常表现出一种拥有权分离的形式，容器本身拥有自身的状态，而客户代码拥有容器中各个对象的状态。 
	 * ServletContext 使用 getAttribute() 和 setAttribute() 时不需要使用同步，但使用保存在ServletContext中的对象时，则可能使用同步。
	 * 
	 * 为了防止多个线程在并发访问同一个对象时产生的相互干扰，这些对象应该要么是线程安全的对象，要么是事实不可变对象，要么是被锁保护起来的对象。
	 * 
	 */
	
	
}
