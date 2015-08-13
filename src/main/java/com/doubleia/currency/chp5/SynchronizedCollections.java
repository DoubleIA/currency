package com.doubleia.currency.chp5;

import java.util.Vector;

/**
 * 
 * @description 同步容器类
 * @author wangyingbo
 * @version 5.1.0
 *
 */
public class SynchronizedCollections {

	/**
	 * 
	 * 同步容器类都是线程安全的，但是某些情况下需要额外的加锁客户端加锁来保护复合操作。
	 * 
	 * 同步容器类主要有vector和Hashtable，还有一些Collections.synchronizedXxx等工厂方法创建的
	 * 
	 * Vector容器中的两个方法getLast和deleteLast为复合操作，当多线程执行这两个方法时，可能会抛出ArrayIndexOutOfBoundsException异常，使用for(int i = 0; i < vector.size(); i++)循环迭代时，也有可能会导致抛出该异常。
	 * 使用客户端加锁的Vector上的复合操作
	 * 
	 */
	public static Object getLast(Vector list) {
		synchronized (list) {
			int lastIndex = list.size()-1;
			return list.get(lastIndex);
		}
	}
	
	public static void deleteLast(Vector list) {
		synchronized (list) {
			int lastIndex = list.size() - 1;
			list.remove(lastIndex);
		}
	}
	
	/**
	 * 
	 * 使用迭代器与ConcurrentModificationException:
	 * 
	 * 在设计同步容器类的迭代器时，没有考虑到并发修改容器类的问题，当进行迭代时，若发现容器在迭代过程中被修改，则会抛出ConcurrentModificationException异常
	 * 
	 * 虽然加锁可以防止异常发生（但可能导致死锁或带来性能问题），但是需要在所有使用共享容器迭代器的地方都是用加锁机制，然而，实际情况中迭代器可能隐藏起来。
	 * 
	 * toString hashCode equals containsAll removeAll retainAll等方法，以及把容器作为参数的构造函数，都会隐式调用迭代器。
	 * 
	 * 如果状态与它的同步代码之间距离越远，那么开发人员就容易忘记在状态时使用正确的同步。
	 * 正如封装对象的状态有助于维持不变性条件一样，封装对象的同步机制同样有助于确保实施同步策略。
	 * 
	 */
	
}
