package com.doubleia.currency.chp5;

import java.math.BigInteger;

/**
 * 
 * @author wangyingbo
 * @version 5.6.1
 *
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {

	public BigInteger compute(String arg) throws InterruptedException {
		//经过长时间的计算
		return new BigInteger(arg);
	}

}
