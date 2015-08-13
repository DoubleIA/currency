package com.doubleia.currency.chp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @description 客户端加锁机制
 * @author wangyingbo
 * @version 4.4.1
 *
 */
public class ListHelper<E> {

	/**
	 * 
	 * 对于由Collections.synchronizedList封装的ArrayList，在原始类中添加一个方法或者对类进行扩展都行不通。
	 * 使用第三种策略来扩展类的功能，并不是扩展类本身，而是将扩展代码放入一个“辅助类”中。
	 * 
	 * 客户端加锁是指，对于使用某个对象X的客户端代码，使用X本身用于保护其状态的锁来保护这段客户代码。要使用客户端加锁，必须知道对象X使用的是哪一种锁。
	 * 
	 * 客户端加锁比添加一个原子操作来扩增类更加脆弱，因为它将类C的加锁代码放到与C完全无关的其他类中。
	 * 
	 * 客户端加锁机制与扩展机制有许多共同点，二者都是将派生类的行为与基类实现耦合在一起。正如扩展会破坏实现的封装性，客户端加锁同样会破坏同步策略的封装性。
	 * 
	 */
	public List<E> list = Collections.synchronizedList(new ArrayList<E>());
	
	//...
	
	public boolean putIfAbsent(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent)
				list.add(x);
			return absent;
		}
	}
	
}
