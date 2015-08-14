package com.doubleia.currency.chp5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * @dscription FutureTask
 * @author wangyingbo
 * @version 5.5.2
 *
 */
public class Preloader {
	
	/**
	 * 
	 * FutureTask也可以用作闭锁，FutureTask实现了Future语义，表示一种抽象的可生成结果的计算。
	 * 
	 * FutureTask表示的计算是通过Callable来实现的，相当于一种可生成结果的Runnable。
	 * 
	 * 它有三种状态： 1.Waiting to run. 2.Running. 3.Completed(表示计算所有可能结束的方式，无论正常与否).
	 * 
	 * 当FutureTask进入完成状态后，它会永远停止在这个状态上。
	 * 
	 * Future.get的行为取决于任务的状态，如果任务已经完成，那么get会立即返回结果，否则get将阻塞直到任务进入完成状态，然后返回结果或抛出异常。
	 * 
	 * FutureTask的规范确保了将结果从执行计算的线程安全发布到获取结果的线程。
	 * 
	 * FUtureTask在Executor框架中表示异步任务，还可以表示一些较长的计算，这些就算可以在使用结果之前启动。从而减少等待结果时需要的时间。
	 * 
	 * 该类就使用FutureTask来执行一个高开销的计算，并且计算结果将稍后使用。
	 * 
	 */
	private final FutureTask<ProduceInfo> future = new FutureTask<ProduceInfo>(
			new Callable<ProduceInfo>() {

				public ProduceInfo call() throws DataLoadException {
					return loadProduceInfo();
				}
	});

	private final Thread  thread = new Thread(future);
	
	public void start() { thread.start(); }
	
	public ProduceInfo get() throws DataLoadException, InterruptedException {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException)
				throw (DataLoadException) cause;
			else
				throw launcherThrowable(cause);
		}
	}

	/**
	 * 
	 * Callable任务可以抛出受检查的或未受检查的异常，并且任何代码都有可能抛出Error，这些异常会被封装到ExecutionException中，在future.get中被重新抛出，这使得调用get代码变得复杂，异常处理并不容易。
	 * 
	 * 抛出异常可能是三种情况之一: Callable抛出受检查的异常，RuntimeException以及Error。我们必须对每种情况单独处理。
	 * 
	 * 上段中代码可知，Preloader会首先检测已知的受检查的异常，并重新抛出他们。剩下的是未受检测的异常，Preloader将调用launcherThrowable并抛出异常。
	 * 
	 * 如果是一个Error，那么launcherThrowable再次抛出它，如果不是RuntimeException，将抛出IllegalArgumentException。
	 * 剩下的是RuntimeException,launcherThrowable将它返回给调用者，调用者通常会重新抛出他们。
	 * 
	 * @param cause
	 * @return
	 */
	private static RuntimeException launcherThrowable(Throwable cause) {
		if (cause instanceof RuntimeException)
			return (RuntimeException) cause;
		else if (cause instanceof Error)
			throw (Error) cause;
		else
			throw new IllegalArgumentException();
	}

	private ProduceInfo loadProduceInfo() {
		return new ProduceInfo();
	}
	
	class DataLoadException extends Exception {

		private static final long serialVersionUID = -2939616593887417242L;

		public DataLoadException() {
			super();
		}

		public DataLoadException(String message, Throwable cause,
				boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public DataLoadException(String message, Throwable cause) {
			super(message, cause);
		}

		public DataLoadException(String message) {
			super(message);
		}

		public DataLoadException(Throwable cause) {
			super(cause);
		}
		
	}
	
	class ProduceInfo {
		
	}
	
}
