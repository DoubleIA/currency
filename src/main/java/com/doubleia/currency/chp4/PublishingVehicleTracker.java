package com.doubleia.currency.chp4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @description 发布状态的车辆追踪器
 * @author wangyingbo
 * @version 4.3.2
 *
 */
public class PublishingVehicleTracker {

	/**
	 * 
	 * 该版本将发布底层的可变状态，使用线程安全的SafePoint类。
	 * PublishVehicleTracker将其线程安全性委托给底层的ConcurrentHashMap，Map中的元素是线程安全的且可变的Point。
	 * getLocations()方法返回底层Map对象的一个不可变副本，虽然不能添加或删除车辆，但却可以通过改变Map中SafePoint来改变车辆位置。
	 * 该类是线程安全的，但是如果在车辆位置的有效值上施加任何约束，那么就不再是线程安全的了。
	 * 如果想对车辆位置变化进行判断或当位置变化时执行一些操作，那么PublishingVehicleTracker并不适用。
	 * 
	 */
	private final Map<String, SafePoint> locations;
	
	private final Map<String, SafePoint> unmodifiableMap;
	
	public PublishingVehicleTracker(Map<String, SafePoint> locations) {
		this.locations = new ConcurrentHashMap<String, SafePoint>(locations);
		this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}
	
	public Map<String, SafePoint> getLocations() {
		return unmodifiableMap;
	}
	
	public SafePoint getLocation(String id) {
		return locations.get(id);
	}
	
	public void setLocations(String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException("Invalid vehicle name: " + id);
		}
		locations.get(id).set(x, y);
	}
	
}
