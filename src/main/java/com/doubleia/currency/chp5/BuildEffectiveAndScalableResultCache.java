package com.doubleia.currency.chp5;

/**
 * 
 * @description 构建高效且可伸缩的结果缓存
 * @author wangyingbo
 * @version 5.6.0
 *
 */
public class BuildEffectiveAndScalableResultCache {
	
	/**
	 * 
	 * 几乎所有的服务器应用程序都会使用某种形式的缓存，用以复用之前的计算结果，降低延迟，提高吞吐量，但却需要消耗更多的内存。
	 * 缓存看起来十分简单，然而，简单的缓存可能会将性能瓶颈变为可伸缩性的瓶颈，即是缓存是用于提高但线程的性能。
	 * 接下来开发一个高效且可伸缩的缓存，用于改进一个高计算开销的函数。
	 * 我们从简单的HashMap开始，然后分析它的并发缺陷性，并讨论如何修复他们。
	 * 接口为Computable<A, V>, 计算函数ExpensiveFunction， 缓存类系列Memoizer<A, V>
	 * 
	 */

}
