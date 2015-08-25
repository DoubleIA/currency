package com.doubleia.currency.chp6;

/**
 * @description Executor框架
 * @author wangyingbo
 * @version 6.2.0
 */
public interface Executor {
	
	/**
	 * 任务是一组逻辑工作单元，而线程是使任务异步执行的机制
	 */
	void execute(Runnable command);

}
