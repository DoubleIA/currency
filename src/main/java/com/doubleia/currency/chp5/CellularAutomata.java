package com.doubleia.currency.chp5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * @description Barrier 栅栏
 * @author wangyingbo
 * @version 5.5.4
 *
 */
public class CellularAutomata {
	
	/**
	 * 
	 * 栅栏
	 * 类似于闭锁，主要区别在于，线程必须同时到达栅栏位置，才能继续执行。闭锁用于等待事件，而栅栏用于等待其他线程。
	 * 
	 * CyclicBarrier可以使一定数量的参与方反复在栅栏位置汇聚。他在并行迭代算法中非常有用。
	 * 
	 */
	private final Board mainBoard;
	
	private final CyclicBarrier barrier;
	
	private final Worker[] workers;
	
	public CellularAutomata(Board board) {
		this.mainBoard = board;
		
		int count = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(count,
				new Runnable() {
					
					public void run() {
						mainBoard.commitNewValues();
					}
				});
		
		this.workers = new Worker[count];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker(mainBoard.getSubBoard(count, i));
		}
	}
	
	private class Board {

		public void commitNewValues() {
			System.out.println("Commit new values...");
		}

		public Board getSubBoard(int count, int i) {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean hasConverged() {
			// TODO Auto-generated method stub
			return false;
		}

		public int getMaxX() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getMaxY() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void setNewValue(int x, int y, Object computeValue) {
			// TODO Auto-generated method stub
			
		}

		public void waitForConvergence() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Worker implements Runnable {
		private final Board board;
		
		public Worker(Board board) {
			this.board = board;
		}

		public void run() {
			while (!board.hasConverged()) {
				for (int x = 0; x < board.getMaxX(); x++)
					for (int y = 0; y < board.getMaxY(); y++)
						board.setNewValue(x, y, computeValue(x, y));
				
				try {
					barrier.await();
				} catch (InterruptedException e) {
					return;
				} catch (BrokenBarrierException e) {
					return;
				}
			}
		}

		private Object computeValue(int x, int y) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public void start() {
		for (int i = 0; i < workers.length; i++) {
			new Thread(workers[i]).start();
		}
		mainBoard.waitForConvergence();
	}

}
