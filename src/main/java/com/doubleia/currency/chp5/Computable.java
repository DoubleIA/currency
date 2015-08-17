package com.doubleia.currency.chp5;

/**
 * 
 * @author wangyingbo
 * @version 5.6.1
 *
 * @param <A>
 * @param <V>
 */
public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}
