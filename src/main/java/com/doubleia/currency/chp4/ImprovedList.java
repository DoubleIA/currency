package com.doubleia.currency.chp4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @description 组合
 * @author wangyingbo
 * @version 4.4.2
 *
 */
public class ImprovedList<T> implements List<T> {

	/**
	 * 
	 * 当为现有的类添加一个原子操作时，有一种更好的方法：组合（Composition）。
	 * 
	 * 实际上，ImprovedList使用了Java监视器模式来封装，只要类中拥有指向底层List的唯一外部引用，就能确保线程安全性。
	 * 
	 */
	private final List<T> list;
	
	public ImprovedList(List<T> list) {
		this.list = list;
	}
	
	public synchronized boolean putIfAbsent(T x) {
		boolean contains = list.contains(x);
		if (contains)
			list.add(x);
		return !contains;
	}

	public synchronized boolean add(T e) {
		return list.add(e);
	}

	public synchronized void add(int index, T element) {
		list.add(index, element);
	}

	public boolean addAll(Collection<? extends T> c) {
		return list.addAll(c);
	}

	public synchronized boolean addAll(int index, Collection<? extends T> c) {
		return list.addAll(index, c);
	}

	public synchronized void clear() {
		list.clear();
	}

	public synchronized boolean contains(Object o) {
		return list.contains(o);
	}

	public synchronized boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public synchronized T get(int index) {
		return list.get(index);
	}

	public synchronized int indexOf(Object o) {
		return list.indexOf(o);
	}

	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}

	public synchronized Iterator<T> iterator() {
		return list.iterator();
	}

	public synchronized int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public synchronized ListIterator<T> listIterator() {
		return list.listIterator();
	}

	public synchronized ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	public synchronized boolean remove(Object o) {
		return list.remove(o);
	}

	public synchronized T remove(int index) {
		return list.remove(index);
	}

	public synchronized boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public synchronized boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public synchronized T set(int index, T element) {
		return list.set(index, element);
	}

	public synchronized int size() {
		return list.size();
	}

	public synchronized List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public synchronized Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings("hiding")
	public synchronized <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	
	
}
