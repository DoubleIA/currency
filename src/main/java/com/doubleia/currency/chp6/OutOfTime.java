package com.doubleia.currency.chp6;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @description 延迟与周期任务
 * @author wangyingbo
 * @version 6.2.5
 */
public class OutOfTime {

	/**
	 * 使用Timer类负责管理延迟任务以及周期任务会出现一些缺陷，应该使用ScheduledThreadPoolExecutor
	 * Timer类执行任务时会创建一个线程，如果某个任务执行时间过长，会影响其他的TimeTask定时准确性。
	 * 此外，当TimerTask抛出一个未检查的异常时，Timer不会捕获异常，而会直接终止，未执行的TimerTask不会得到执行，新任务也得不到调度。
	 * 
	 * 下面是错误的Timer行为
	 */
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		//SECONDS.sleep(1);
		timer.schedule(new ThrowTask(), 1);
		//SECONDS.sleep(5);
	}
	
	static class ThrowTask extends TimerTask {
		public void run() {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 
	 * 构建自己的调度任务，可以使用DelayQueue, 它实现了BlockingQueue，并为ShceduledThreadPoolExecutor提供调度功能。
	 * 
	 */
}
