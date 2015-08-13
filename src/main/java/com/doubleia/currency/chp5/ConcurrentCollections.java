package com.doubleia.currency.chp5;

/**
 * 
 * @description 并发容器
 * @author wangyingbo
 * @version 5.2.0
 *
 */
public class ConcurrentCollections {

	/**
	 * 
	 * 并发容器改进了同步容器的性能。同步容器将所有对容器状态的访问都串行化，以实现他们的线程安全性，这种方法的代价时严重降低并发性，当多个线程竞争容器的锁时，吞吐量将严重降低。
	 * 
	 * 通过并发容器类来代替同步容器类，将极大提高伸缩性并降低风险。
	 * 
	 * ConcurrentHashMap用于代替同步且基于散列的Map
	 * ConcurrentSkipListMap和ConcurrentSkipListSet分别作为同步的SortedMap和SortedSet的并发替代品（例如被SynchronizedMap包装的SortedMap和SortedSet
	 * CopyOnWriteArrayList用于在遍历操作为主要操作的情况下代替同步的List
	 * ConcurrentMap接口添加了一些对复合操作的接口，如若没有则添加，替换以及有条件则删除等
	 * 
	 * Queue，BlockingQueue
	 * 
	 * Queue用于临时保存待处理的一组元素，它有几个实现：
	 * ConcurrentLinkedQueue,是一个传统的先进先出队列
	 * PriorityQueue，非并发的优先级队列
	 * 
	 *  可以用List模拟Queue的行为，实际上，正是通过LinkedList来实现Queue的，但还需要一个Queue的类，可以去掉List的随机访问需求，以实现更高的并发性。
	 * BlockingQueue扩展了Queue，增加了可阻塞的插入和获取等操作。
	 *  
	 * 
	 */
	
}
