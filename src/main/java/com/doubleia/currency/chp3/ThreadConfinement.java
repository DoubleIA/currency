package com.doubleia.currency.chp3;


/**
 * 
 * @date 2015.8.4 10:41
 * @author wangyingbo
 * @description 线程封闭， 共享可变数据，通常需要使用同步，一种不使用同步的方式就是不共享数据，在但线程内访问数据，就不需要同步，即线程封闭
 * @version 3.0.2
 *
 */
public class ThreadConfinement {

	/**
	 * 
	 * 栈封闭中，只能通过局部变量才能访问对象
	 * 封装能使代码更容易维护不变性条件，同步变量也能使对象更易于封装在线程中
	 * 局部变量固有属性之一就是封闭在执行线程中，它位于执行线程栈中，其他线程无法访问
	 * 不同于ThreadLocal，比Ad-hoc更加易于维护，更加健壮
	 * 基本类型的局部变量无论如何不会破坏线程封闭性，因为无法获得该引用，如numPairs
	 * 
	 * 基本类型的局部变量与引用变量的线程封闭性
	 * 
	 */
	/*
	public int loadTheArk(Collection<Animal> candidates) {
		SortedSet<Animal> animals;
		int numPairs = 0;
		Animal candidate = null;
		
		//animals被封装在方法中，不要使它逸出
		animals = new TreeSet<Animal>(new SpecialGenderComparator());
		animals.addAll(candidates);
		
		for(Animal a : animals) {
			if(candidate == null || !candidate.isPotentialMate(a))
				candidate = a;
			else {
				ark.load(new AnimalPair(candidate, a));
				++numPairs;
				candidate = null;
			}
		}
		
		return numPairs;
	}
	*/
	
	/**
	 * 
	 * ThreadLocal 维持线程封闭性更规范的方法
	 * 这个类使线程中的某个值与保存值的对象关联起来
	 * 提供get()与set()方法，这些方法为每一个使用该变量的线程都存有一份独立的副本
	 * 即get()总是返回由当前执行线程在调用set()时设置的最新值
	 * ThreadLocal通常用于防止对可变的单实例变量(Singleton)或全局变量进行共享
	 * 
	 * 使用ThreadLocal维持线程封闭性
	 * 
	 * 当某个频繁执行的操作需要一个临时对象，例如缓冲区，而同时希望避免在每次执行时都重新分配该对象，就可以使用这种技术
	 * 
	 * ThreadLocal<T> 看作 Map<Thread, T>对象，其中保存了特定线程的值，事实并非如此，值保存在Thread对象中，线程终止，值回收
	 * 
	 * Page37～38 引入隐含的耦合性，使用时要格外小心
	 * 
	 */
	/*
	 private static ThreadLocal<Connection> connectionHolder = 
	 				new ThreadLocal<Connection>() {
	 					public Connection initialValue() {
	 						return DriverManager.getConnection(DB_URL);
	 					}
	 				};
	 
	 public static Connection getConnection() {
	 	//get()方法会使initialValue()方法调用
	 	return connectionHolder.get();
	 }
	  */
	
	
}
