package com.doubleia.currency.chp3;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * @date 2015.8.4 15:25 
 * @author wangyingbo
 * @version 3.0.4
 * @description final域是不能修改的，但如果final域所引用的对象是可变的，这些被引用的对象是可以被更改的
 */
public class FinalField_OneValueCache {
	/**
	 * 
	 * final域能确保初始化过程的安全性，从而可以不受限的访问不可变对象，并在共享这些对象是无需同步
	 * 限制的对象的可变性，也就限制了对象的可能状态集合
	 * 除非某个域是可变的，否则应将这个域声明为final域
	 * 
	 * 示例：使用Volatile类型来发布不可变对象
	 * 每当需要对一组相关数据以原子方式进行某个操作时，就可以考虑创建一个不可变的域来包含这些数据 OneValueCache
	 * 
	 */
	private final BigInteger lastNumber;
	
	private final BigInteger[] lastFactors;
	
	public FinalField_OneValueCache(BigInteger i, BigInteger[] factors) {
		this.lastNumber = i;
		this.lastFactors = Arrays.copyOf(factors, factors.length);
	}
	
	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber != null || !lastNumber.equals(i))
			return null;
		else
			return Arrays.copyOf(lastFactors, lastFactors.length);
	}
	
}
