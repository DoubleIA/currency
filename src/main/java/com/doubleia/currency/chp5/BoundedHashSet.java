package com.doubleia.currency.chp5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 
 * @description Semaphore
 * @author wangyingbo
 * @version 5.5.3
 *
 */
public class BoundedHashSet<T> {
	
	/**
	 * 
	 * 计数信号量用于同时访问某个特定资源的操作数量，或者用来控制同时执行某个指定操作的数量。可用来实现资源池，或者对容器施加边界。
	 * 
	 * Semaphore中管理一组许可（permit），acquire，release方法用于获取释放许可。
	 * 
	 * 二值信号量（permit数量为1）可用作互斥体（mutex），并具备不可重入的加锁语意，谁拥有这唯一的许可，谁就拥有了互斥锁。
	 * 
	 * 资源池：数据库连接池。有界容器：可以使用Semaphore将任意一种容器变为有界容器。
	 * 
	 */
	private final Set<T> set;
	
	private final Semaphore sem;
	
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sem = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean wasAdded = false;
		
		try {
			wasAdded = set.add(o);
			return wasAdded;
		} finally {
			if (!wasAdded)
				sem.release();
		}
		
	}
	
	public boolean remove(T o) {
		boolean wasRemoved = set.remove(o);
		
		if (wasRemoved)
			sem.release();
		return wasRemoved;
	}
	
	
}
