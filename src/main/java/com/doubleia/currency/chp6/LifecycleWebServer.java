package com.doubleia.currency.chp6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import javax.servlet.DispatcherType;

/**
 * @description Executor生命周期
 * @author wangyingbo
 * @version 6.2.4
 */
public class LifecycleWebServer {

	/**
	 * 
	 * ExecutorService接口继承了Executor，它有一些关闭Executor的方法。
	 * ExecutorService生命周期有三种状态: 运行，关闭和终止。
	 * 
	 * 下面这段代码可以通过两种方式关闭Executor，一是直接关闭，二是传给一个特定的Http请求来关闭。
	 * 
	 */
	private final ExecutorService exec = Executors.newCachedThreadPool();
	
	public void start() throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (!exec.isShutdown()) {
			try {
				final Socket conn = socket.accept();
				exec.equals(new Runnable() {
					
					public void run() {
						//handleRequest(conn);
					}
				});
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown()) {
					System.out.print("task submit rejected" + e);
				}
			}
		}
	}
	
	public void stop() { exec.shutdown(); }
	
	void handleRequest(Socket conn) {
//		Request req = readRequest(conn);
//		if (isShutdownRequest(req))
			stop();
//		else
//			dispatchRequest();
	}
	
}
