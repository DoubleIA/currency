package com.doubleia.currency.chp5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @description 桌面搜索
 * @author wangyingbo
 * @version 5.3.1
 *
 */
public class FileCrawler implements Runnable {

	/**
	 * 
	 * 
	 * 
	 */
	private final BlockingQueue<File> fileQueue;
	
	private final FileFilter fileFilter;
	
	private final File root;

	public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	}
	
	public void run() {
		
	}
	
	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory())
					crawl(entry);
				else if (!alreadyIndexed(entry))
					fileQueue.put(entry);
					
			}
		}
	}
	
	
	
}
