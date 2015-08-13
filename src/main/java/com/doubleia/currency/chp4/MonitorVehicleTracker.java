package com.doubleia.currency.chp4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * 
 * @author wangyingbo
 * @description Java监视器
 * @version 4.3.0
 * 
 */
@ThreadSafe
public class MonitorVehicleTracker {
	/**
	 * 
	 * 虽然MutablePoint不是线程安全的，但它所包含的Map对象和可变的Point对象都未发布
	 * 
	 * 数据较大时，性能不好
	 * 
	 */
	@GuardedBy("this")
	private final Map<String, MutablePoint> locations;
	
	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}
	
	public synchronized Map<String, MutablePoint> getLocations() {
		return deepCopy(locations);
	}
	
	public synchronized MutablePoint getLocation(String id) {
		MutablePoint loc = locations.get(id);
		return loc == null ? null : new MutablePoint(loc);
	}

	public synchronized void setLocation(String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		if (loc == null) {
			throw new IllegalArgumentException();
		}
		loc.x = x;
		loc.y = y;
	}
	
	//注意，不能直接使用unmodifiableMap来封装m或者直接将m拷贝给result，这两种方式仅仅是将Map对象的引用m封装为不可变Map，仅仅是引用不可变，可引用指向对象
	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
		Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
		for (String id : m.keySet())
			result.put(id, new MutablePoint(m.get(id)));
		return Collections.unmodifiableMap(result);
	}
	
}
