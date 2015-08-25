package com.doubleia.currency.chp6;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @description 为任务设置时限
 * @author wangyingbo
 * @version 6.3.2
 *
 */
public class QuoteTask implements Callable<TravelQuote>{
	
	/**
	 * 
	 * 当任务无法在指定时间内完成，那么将不再需要其结果，可以放弃这个任务。
	 * 在有限时间内执行任务的主要困难在于，要确保得到答案的时间不会超过限定时间，或者在限定时间内无法获得答案。
	 * 在支持时间限制的Future.get支持这种需求：当结果可用时，它将立即返回，如果指定时间内没有计算出结果，那么将抛出TimeoutException。
	 * 
	 * 在使用限时任务时需要注意，当这些任务超时候应该立即停止，从而避免为继续计算一个不再使用的结果而浪费计算资源。
	 * 要实现这个功能，可以由任务本身来管理它的限定时间，并在超时后取消任务。此时可再在使用Future，如果一个限定时间的get方法抛出了TimeoutException，那么可以通过Future来取消任务。
	 * 如果编写的任务是可取消的，那么可以提前终止它，以免消耗过多资源。
	 * 
	 * 下面看一段Future.get典型应用：
	 * 
	 * Page renderPageWithAd() throws InterruptedException {
	 * 
	 * 	long endNanos = System.nanoTime() + TIME_BUDGET;
	 *  	Future<Ad> f = exec.submit(new FetchAdTask());
	 *  	//等待广告的同时显示页面
	 *  	Page page = renderPageBody();
	 *  	Ad ad;
	 *  	try {
	 *  		//只等待指定时间长度
	 *  		long timeLeft = endNanos - System.nanoTime();
	 *  		ad = f.get(timeLeft, NANOSECONDS);
	 *  	} catch (ExecutionException e) {
	 *  		ad = DEFAULT_AD;
	 *  	} catch (TimeoutException e) {
	 *  		ad = DEFAULT_AD;
	 *  		f.cancel(true);
	 *  	}
	 * 	Page.setAd(ad);
	 * 	return page;
	 * }
	 * 
	 * 下面的示例为一个旅行预订门户网站
	 * 
	 * 使用invokeAll，将多个任务提交到一个ExecutorService中并获得结果。
	 * invokeAll参数为一组Task并返回一组Future。
	 * invokeAll在时限之后，将返回结果，任务要么执行完毕，没执行完毕的将被取消。
	 */
	
	private final TravelCompany company;
	private final TravelInfo travelInfo;
	
	public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
		this.travelInfo = travelInfo;
		this.company = company;
	}
	
	public TravelQuote call() throws Exception {
		return company.solicitQutoe(travelInfo);
	}
	
	public List<TravelQuote> getRankedTravelQuotes(
			TravelInfo travelInfo, Set<TravelCompany> companies,
			Comparator<TravelQuote> ranking, long time, TimeUnit unit
			) throws InterruptedException {

		return null;
	}
	
	
}
