package com.doubleia.currency.chp6;

/**
 * @description 任务执行
 * @author wangyingbo
 * @version 6.0.0
 */
public class TaskExecution {
	
	/**
	 * 任务通常是一些抽象的且离散的工作单元。通过把应用程序的工作分配到多个任务中，可以减化程序的组织结构，
	 * 提供一种自然的事物边界来优化错误恢复过程，以及提供一种自然的并行工作结构来提升并发性。
	 * 
	 * 执行策略：*
	 * 
	 * 通过将任务的提交与执行解耦出来，可以不费太大困难的为某种类型的任务指定和修改执行策略。
	 * 执行策略中定义了“What, Where, When, How”等方面
	 * 在什么时后执行任务
	 * 任务按什么顺序（FIFO LIFO 优先级）执行
	 * 有多少个任务并发执行
	 * 队列中有多少个任务等待执行
	 * 如过系统由于过载而要拒绝一个任务，拒绝哪一个任务，又如何通知应用程序任务被拒绝
	 * 执行一个任务前后应该有哪些动作。
	 * 
	 * 每当看到new Thread(Runnable).start()时，就应该考虑使用Executor来代替Thread。
	 * 
	 * 线程池：* 
	 * 
	 * newFixedThreadPool 创建固定长度的线程池。
	 * newCachedThreadPool 创建一个可缓存的线程池。
	 * newSigleThreadExecutor 单线程的Executor。
	 * newScheduledThreadPool 创建固定长度额线程池，以延迟或定时的方式来执行任务，类似于Timer。
	 * 
	 * newFixedThreadPool和newCachedThreadPool具体参数见第八章。他们可以直接用来构造专门用途的Executor。
	 * 通过使用Executor可以使用各种调优，管理，监视，记录日志和错误报告等功能。
	 * 
	 */

}
