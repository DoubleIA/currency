package com.doubleia.currency.chp6;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description 显示的为任务创建线程。
 * @author wangyingbo
 * @version 6.1.2
 */
public class ThreadPerTaskWebServer {
	
	/**
	 * 通过为每个请求创建一个新的线程来提供服务，从而实现更高的响应性。
	 * 
	 * 下面这段代码使得处理过程从主线程分离出来，任务可以并行处理。但是需要保证任务处理代码是线程安全的。
	 * 
	 * 当请求的到达速率不超出服务器的请求处理能力，那么这种方法可以同时带来更快的响应性和更高的吞吐率。
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket();
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				
				public void run() {
					//handleRequest(connection);
				}
			};
			new Thread(task).start();
		}
	}
	/**
	 * 无限创建线程有很多不足的地方，尤其是要创建大量线程的时侯。
	 * 1. 线程生命周期的开销非常高，线程的创建与销毁也需要付出一定的代价，当请求到达率非常高且处理过程是轻量级的，那么将消耗大量的计算机资源。
	 * 2. 消耗资源，活跃的线程会消耗资源，尤其是线程。
	 * 3. 稳定性，可创建线程数量上有一定的限制。超过一定阈值，会导致性能下降甚至服务器崩溃。
	 */
	
}
