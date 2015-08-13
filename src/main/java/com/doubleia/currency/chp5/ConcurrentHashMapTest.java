package com.doubleia.currency.chp5;

/**
 * 
 * @description ConcurrentHashMapTest
 * @author wangyingbo
 * @version 5.2.1
 *
 */
public class ConcurrentHashMapTest {

	/**
	 * 
	 * 同步容器类在执行每个操作时都会持有一把锁。
	 * 
	 * ConcurrentHashMap使用了一种粒度更细的加锁机制来实现更大程度的共享，从而提供了更高的并发行和伸缩性。
	 * 这种机制称为分段锁（Lock Striping）。任意数量的读取线程可以并发访问Map，执行读取操作的线程和执行写入操作的线程可以并发的访问Map，并且一定数量的写入线程可以并发的修改Map。*
	 * ConcurrentHashMap使得在并发访问环境下实现更高的吞吐量，而在单线程环境中只损失非常小的性能。
	 * 
	 * ConcurrentHashMap并发容器类不会抛出ConcurrentModificationException异常，因此不需要在迭代过程中对容器加锁。
	 * ConcurrentHashMap并发容器返回的迭代器具有弱一致性（Weakly Consistent），而并发“及时失败”的。
	 * 弱一致性的迭代器，可以容忍并发的修改，当创建迭代器时会遍历已有的元素，并可以（不保证）在迭代器被构造后将修改操作返回给容器。
	 * 
	 * 对于一些可以权衡的因素，如size和isEmpty，这些方法的语意被弱化了，返回的结果只是一个近似的值，原因在于并发环境中这些操作的用处不大，因为size总是处于不断变化当中。
	 * 这些操作被弱化了，以换取对其他更重要的操作的性能优化，包括get put containsKey和remove
	 * 
	 * ConcurrentHashMap并没有实现对Map加锁以提供独占的访问，因此，仅仅在应用程序需要对Map加锁独占访问时，才应该放弃使用ConcurrentHashMap。
	 * 
	 * 
	 * CopyOnWriteArrayList用来代替同步List（CopyOnWriteArraySet用于代替同步的Set）
	 * 某些情况下提供更好的并发性能，使用迭代器时也不需要加锁或复制。
	 * 
	 * 写入时复制（Copy on write）容器的安全性在于，只要正确发布一个事实不可变对象，那么在访问该对象时就不再需要进一步同步。在每次修改时都会创建并重新发布一个新的容器副本，从而实现可变性。
	 * 
	 * 创建迭代器时，会保存一个指向底层基础数组的引用，线程间不会相互干扰，不会抛出ConcurrentModificationException异常，并且返回元素与迭代器创建时元素完全一致，而不用考虑之后的修改操作带来的影响。
	 * 
	 * 当迭代操作远远多与修改操作时，才考虑使用CopyOnWriteArrayList容器。
	 * 
	 */
	
}
