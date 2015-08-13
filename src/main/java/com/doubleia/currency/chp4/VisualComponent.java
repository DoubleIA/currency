package com.doubleia.currency.chp4;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author wangyingbo
 * @description 独立的状态变量
 * @version 4.3.1
 *
 */
public class VisualComponent {

	/**
	 * 
	 * 我们可以将线程安全性委托给多个独立的状态变量。
	 * 使用CopyOnWriteArrayList来保存各个监听器列表。
	 * 每个列表都是线程安全的，此外由于各个状态都不存在耦合关系，因此，VisualComponent可以将安全性委托给keyListeners和mouseListeners等对象。
	 * 
	 * 总结：如果一个类是由多个独立且线程安全的状态变量组成，并且在操作中都不包含无效条件的转换，那么可以将线程安全性委托给底层的状态变量。
	 * 
	 * 如果一个状态变量是线程安全的，并且没有任何不变性条件来约束它的值，在变量的操作上也不存在任何不允许的状态转换，那么就可以安全的发布这个对象。
	 * 
	 */
	private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
	
	private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();
	
	public void addKeyListener(KeyListener listener) {
		keyListeners.add(listener);
	}
	
	public void addMouseListener(MouseListener listener) {
		mouseListeners.add(listener);
	}
	
	public void removeKeyListener(KeyListener listener) {
		keyListeners.remove(listener);
	}
	
	public void removeMouseListener(MouseListener listener) {
		mouseListeners.remove(listener);
	}
	
}
