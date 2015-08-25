package com.doubleia.currency.chp6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 
 * @description CompletionService: Executor和BlockingQueue
 * @author wangyingbo
 * @version 6.3.1
 *
 */
public class Renderer {

	/**
	 * 
	 * 如果向Executor提交一组计算任务，并且希望在计算完成后获得结果，那么可以保留与每个任务关联的Future，然后反复使用get方法，同时将timeout指定为0，从而通过轮询来判断任务是否完成。
	 * 上面的方法虽然可行，但是还有一种更好的方法：完成任务(CompletionService)
	 * 
	 * CompletionService将Executor和BlockingQueue功能融合在一起，可以将Callable任务交给他，然后通过类似于队列操作的take和poll等方法获得已完成的结果，这些结果在完成时会被封装为Future。
	 * ExecutorCompletionService实现了CompletionService，并将计算部分委托给Executor。ExecutorCompletionService实现非常简单，
	 * 在构造函数中创建一个BlockingQueue来保存完成的结果。当计算完成时，调荣FutureTask的done方法。当提交某个任务时，该任务将首先包装为一个QueueingFuture，这是FutureTask的一个子类，
	 * 然后改写子类的done方法，并将结果放入BlockingQueue中，如下面这段代码所示。take和poll方法委托给了BlockingQueue，这些方法会在得到结果之前阻塞。
	 * 
	 * /**
	 *  * private class QueueingFuture<V> extends FutureTask<V> {
	 *  *		QueueingFuture(Callable<V> c) { super(c); }
	 *  *		QueueingFuture(Runnable t, V r) { super(t, r); }
	 *  *		
	 *  *		protected void done() {
	 *  *			comletionQueue.add(this);
	 *  * 		}
	 *  * }
	 *  *\/
	 *
	 * 下面我们将使用CompletionService来实现界面渲染器，并且从缩短响应时间以及提高响应性这两个方面提高页面渲染性能。
	 * 为每一幅图像的下载都创建一个任务，并在线程池中执行他们。此外，通过从CompletionService中取得结果以及每张图片在下载完成后都显示出来，能使用户获得一个更加动态和更高响应性的用户界面。
	 */
	
	private final ExecutorService executor;
	
	Renderer(ExecutorService executor) {
		this.executor = executor;
	}
	
	void renderPage(CharSequence source) {
		List<ImageInfo> info = scanForImageInfo(source);
		CompletionService<ImageData> completionService = new ExecutorCompletionService<Renderer.ImageData>(executor);
		
		for (final ImageInfo imageInfo : info) {
			completionService.submit(new Callable<Renderer.ImageData>() {
				
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		
		renderText(source);
		
		try {
			for (int t = 0, n = info.size(); t < n; t++) {
				Future<ImageData> f = completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
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
	 * 多个ExecutorCompletionService可以共享一个Executor，因此可以创建一个对于特定计算私有，又能共享一个公共的Executor的ExecutorCompletionService。
	 * 因此，CompletionService的作用就相当于一组计算机句柄，这与Future作为单个计算机句柄非常类似。
	 * 
	 */
}
