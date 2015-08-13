package com.doubleia.currency.chp4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

/**
 * 
 * @author wangyingbo
 * @description 线程安全性委托
 * @version 4.3.1
 *
 */
@ThreadSafe
public class DelegatingVehicleTracker {
	
	/**
	 * 
	 * getLocations()返回的是不可修改却实时的一个车辆位置视图，当另一个线程修改locations时，可以反映到unmodifableMap中。
	 * 这可能会导致不一致的问题。getLocations()和getLocation()坐标不一致，因为getLocations()实时返回点，而getLocation()需要刷新视图获得点。
	 * 如果需要一个不发生变化的车辆视图，可以使用一个浅拷贝。如下：
	 * public Map<String, Point> getLocations() {
	 * 	return Collections.unmodifiableMap(
	 * 		new HashMap<String, Point>(locations)
	 * 		);
	 * }
	 * 
	 */
	
	private final ConcurrentHashMap<String, Point> locations;
	
	private final Map<String, Point> unmodifiableMap;
	
	public DelegatingVehicleTracker(Map<String, Point> points) {
		locations = new ConcurrentHashMap<String, Point>(points);
		unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	public Map<String, Point> getLocations() {
		return unmodifiableMap;
	}
	
	public Point getLocation(String id) {
		return locations.get(id);
	}
	
	public void setLocation(String id, int x, int y) {
		if (locations.replace(id, new Point(x, y)) == null ) {
			throw new IllegalArgumentException("invalid vehicle name " + id);
		}
	}
}
