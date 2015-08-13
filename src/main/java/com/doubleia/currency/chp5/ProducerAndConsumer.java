package com.doubleia.currency.chp5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @description 阻塞队列和生产者-消费者模式
 * @author wangyingbo
 * @version 5.3.0
 *
 */
public class ProducerAndConsumer {
	
	private static final int BOUND = 100;
	private static final int N_CONSUMERS = 20;
	
	public static void startIndexing(File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
		
		FileFilter filter = new FileFilter() {
			
			public boolean accept(File pathname) {
				return true;
			}
		};
		
		for (File root : roots)
			new Thread(new FileCrawler(queue, filter, root)).start();
		
		for (int i = 0; i < N_CONSUMERS; i++)
			new Thread(new Indexer(queue)).start();
	}
}
