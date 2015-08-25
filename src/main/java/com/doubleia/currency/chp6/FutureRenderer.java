package com.doubleia.currency.chp6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @description 找出可利用的并行性
 * @author wangyingbo
 * @version 6.3.0
 *
 */
public class FutureRenderer {
	
	/**
	 * 
	 * Executor框架使用Runnable作为其基本的任务形式，Runnable是一种有很大局限的抽象，虽然run能写入到日志文件或将结果放入某个共享的数据结构，但它不能返回一个值或抛出一个受检查的异常。
	 * 对于很多任务都是存在延迟的计算，对于这些任务，Callable是一种更好的抽象：它认为入口点(call)会返回一个值，并且可能抛出一个异常。
	 * 在Executor中包含了一些辅助方法能将其他类型的任务封装为一个Callable，如Runnable和java.security.PrivilegedAction。
	 * 
	 * Runnable和Callable描述的都是抽象的计算任务，这些任务通常是有范围的，即都会明确的起点，并且最终都会结束。
	 * Executor执行任务有四个生命周期阶段：创建、提交、开始、完成。并且已提交但尚未开始的任务可以取消，对于已经开始的任务，只有等他们发生中断才能取消。
	 * 
	 * Future表示一个任务的生命周期，并且能够判断一个任务是否已经开始或已取消，还可以获取任务结果或取消任务。
	 * 可以通过很多发放创建一个Future来描述任务，ExecutorService中所有Submit方法都返回一个Future，从而将一个Runnable或Callable交给Executor，并得到一个Future用来获得任务的执行结果或取消任务。
	 * 还可以显示地为某个Runnable和Callable实例话一个FutureTask。(FutureTask实现了Runnable接口)。
	 * 
	 * 将Runnable和Callable提交到Executor的过程中，包含了一个安全发布的过程，从提交线程发布到计算它的线程。类似的，在设置Future结果的时候，也包含一个安全发布，将结果从计算它的线程发布到通过get获得它的线程。
	 *
	 * 下面例子通过Future实现页面渲染器，使用并发将渲染分为两个任务一个渲染所有文本，另一个下载图片。Callable和Future有助于表示这些协同任务之间的交互。
	 * 
	 */
	private final ExecutorService executor = Executors.newFixedThreadPool(2);
	
	void renderPage(CharSequence source) {
		final List<ImageInfo> imageInfo = scanForImageInfo(source);
		
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {

			public List<ImageData> call() throws Exception {
				List<ImageData> result = new ArrayList<ImageData>();
				for (ImageInfo info : imageInfo) {
					result.add(info.downloadImage());
				}
				return result;
			}
			
		};
		
		Future<List<ImageData>> future = executor.submit(task);
		renderText(source);
		
		try {
			List<ImageData> imageData = future.get();
			for (ImageData data : imageData) {
				renderImage(data);
			}
		} catch (InterruptedException e) {
			//重新设置线程的中断状态
			Thread.currentThread().interrupt();
			//由于不需要结果，取消任务
			future.cancel(true);
		} catch (ExecutionException e) {
			throw launcherThrowable(e.getCause());
		}
		
	}
	
	void renderText(CharSequence source) {
		System.out.println("Render text " + source.toString());
	}
	
	void renderImage(ImageData data) {
		System.out.println("Render image " + data.toString());
	}
	
	List<ImageInfo> scanForImageInfo(CharSequence source) {
		List<ImageInfo> imageList = new ArrayList<ImageInfo>();
		imageList.add(new ImageInfo());
		return imageList;
	}
	
	private static RuntimeException launcherThrowable(Throwable cause) {
		if (cause instanceof RuntimeException)
			return (RuntimeException) cause;
		else if (cause instanceof Error)
			throw (Error) cause;
		else
			throw new IllegalArgumentException();
	}
	
	class ImageData {
		
	}
	
	class ImageInfo {
		ImageData downloadImage() {
			//pretend taking a long time
			return new ImageData();
		}
	}
	
	/**
	 * 
	 * 其实还可以做的更好，那就是每下载了一张图片，就直接显示出来，而不必等待每张图片都下载完成。
	 * 
	 * 然而，虽然做了很多工作来并发执行异构任务，以提高并发性，但从中获得的并发性十分有限。只有当大量相互独立且同构的任务并发处理时，才能体现出将程序的工作负载分配到多个任务中带来的真正性能的提升。
	 * 
	 */
	
}
