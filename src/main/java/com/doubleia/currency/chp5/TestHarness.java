package com.doubleia.currency.chp5;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @description 闭锁
 * @author wangyingbo
 * @version 5.5.1
 *
 */
public class TestHarness {
	
	/**
	 * 
	 * 闭锁是一种同步工具类，可以延迟线程的进度直到其到达终止状态。闭锁相当于一扇加了锁的门，只有当到达结束状态时，锁才会打开，线程才可以通过这扇门，并且，这扇门将一直处于打开的状态。
	 * 
	 * CountDownLatch就是一种闭锁，它维护一个计数器，初始为一个正数，当期待事件发生时，执行countDown方法。
	 * await方法将一直阻塞，直到计数器值变为0
	 * 
	 */
	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		
		final CountDownLatch startGate = new CountDownLatch(1);
		
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException ignore)  { }
				}
			};
			t.start();
		}
		
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		
		return end - start;
	}

}
