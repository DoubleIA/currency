package com.doubleia.currency.chp5;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * @description 阻塞方法与中断方法
 * @author wangyingbo
 * @version 5.4.0
 *
 */
public class TaskRunnable implements Runnable {

	/**
	 * 
	 * 等待I/O操作结束，等待获取一个锁，等待从一个Thread.sleep方法中醒来，或是等待另一个线程的计算结果，都会导致线程阻塞或暂停执行。
	 * 
	 * 当线程阻塞时，它通常被挂起，并处于某种阻塞状态（BLOCKED、WAITING或TIMED_WAITING）。只有当某些事件发生时，线程被置回RUNNABLE状态时，才可以再次被调度执行。
	 * 
	 * Thread的interrupt方法用于中断线程或查询线程是否已经被断。中断是一种协作机制，一个线程不能强制其他线程停止正在执行的操作而去执行其他操作。
	 * 当线程A中断线程B时，A仅仅是要求B在执行到某个可以暂停的地方停止正在执行的操作--前提是B愿意停下来。
	 * 
	 * 最常使用中断的情况就是取消某个操作。方法对中断请求响应度越高，就越容易及时取消那些执行时间很长的操作。
	 * 
	 * 代码抛出InterruptedException时，方法也就变成了一个阻塞方法，有两种方法进行处理：
	 * 
	 * 1.传递InterruptedException：不捕获该异常，直接传递给调用者，或者进行简单的处理，再抛出。
	 * 
	 * 2.恢复中断：当代码时Runnable的一部分时，必须要捕获该异常，可以通过调用当前线程上的interrupt方法恢复中断状态，这样在调用栈更高层代码将看到中断状态。
	 * 
	 * 更复杂的处理方法可见chp7
	 * 
	 */
	
	BlockingQueue<Task> queue;
	
	public void run() {
		try {
			queue.take();
		} catch (InterruptedException e) {
			//恢复被中断的状态
			Thread.currentThread().interrupt();
		}
	}
	
	
	class Task {
		
	}
	
}
