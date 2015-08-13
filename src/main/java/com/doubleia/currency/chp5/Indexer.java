package com.doubleia.currency.chp5;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @description 桌面搜索
 * @author wangyingbo
 * @version 5.3.1
 *
 */
public class Indexer implements Runnable {
	private static final File POISON = new File("");
	
	private final BlockingQueue<File> queue;
	
	public Indexer(BlockingQueue<File> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (true) {
				File file = queue.take();
				if (file == POISON )
					break;
				else
					indexFile(file);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
    public void indexFile(File file) {
        /*...*/
    };
}
