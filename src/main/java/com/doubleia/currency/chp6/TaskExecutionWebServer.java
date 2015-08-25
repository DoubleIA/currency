package com.doubleia.currency.chp6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description 基于Executor的web服务器
 * @author wangyingbo
 * @version 6.2.1
 */
public class TaskExecutionWebServer {
	
	private static final int NTHREADS = 100;
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);
	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				
				public void run() {
					//handleRequest(connection);
				}
			};
			exec.execute(task);
		}
	}
	
	//通过Executor接口实现SinagelThreadWebServer和ThreadPerTaskWebServer类似的功能。
	class ThreadPerTaskExecutor implements Executor {

		public void execute(Runnable command) {
			new Thread(command).start();
		}
		
	}
	
	class WithinThreadExecutor implements Executor {

		public void execute(Runnable command) {
			command.run();
		}
		
	}
	
}
