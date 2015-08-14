package com.doubleia.currency.chp5;

/**
 * 
 * @description 串行线程封闭, 双端队列，工作密取
 * @author wangyingbo
 * @version 5.3.2
 *
 */
public class DequeAndWorkStealing {
	
	/**
	 * 
	 * 对于可变对象，生产者消费者这种设计与阻塞队列一起，促使了串行线程封闭，从而将对象所有权从生产者交付给消费者。
	 * 线程封闭对象只能由单个线程拥有，但可以通过安全发布该对象来转移所有权。新的线程将封闭该对象，原线程不会再访问它。
	 * 可以使用其他发布机制来发布可变对象，如ConcurrentMap的原子方法remove或者AtomicReference的compareAndSet来完成这项工作。
	 * 
	 * Deque和BlockingDeque，是双端队列，对Queue和BlockingQueue进行了扩展。
	 * 阻塞队列适用于生产者消费者模式，双端队列同样适用于另一种相关模式，即工作密取（Work stealing）。
	 * 在生产者消费者设计中，所有消费者共享一个工作队列，而在工作密取模式中，所有消费者都有各自的双端队列。如果一个消费者完成了自己的工作，那么它可以从其他消费者双端队列末尾秘密的获取工作。
	 * 
	 * 非常适合于即是生产者也是消费者的问题。它可以确保每个线程都保持忙碌状态。
	 * 
	 */

}
