package com.doubleia.currency.chp6;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description 串行的执行任务
 * @author wangyingbo
 * @version 6.1.1
 */
public class SingleThreadWebServer {
	
	/**
	 * 围绕“任务执行”设计应用程序结构时，第一步就是要找出清晰的任务边界
	 * 理想情况下任务之间是相互独立的，任务并不依赖于其他任务状态、结果或边界效应。
	 * 自然的任务边界选择方式，以独立的客户端请求为边界。
	 * 
	 * 在单个线程中串行的执行各个任务是最简单的调度任务策略。在这里我们不关心handleRequest如何处理连接，我们感兴趣的是如何表章不同调度策略的同步特性。
	 * 
	 * 显而易见，这种单线程方式效率非常之差。因为任务无法并发执行，当处理过程时间过长(I/O等)，很多任务将被阻塞。
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			Socket connection = socket.accept();
			//handleRequest(connection);
		}
	}
	
	
}
