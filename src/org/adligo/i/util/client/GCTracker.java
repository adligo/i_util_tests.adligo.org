package org.adligo.i.util.client;

import java.lang.management.ManagementFactory;
import java.lang.management.GarbageCollectorMXBean;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
/**
 * this class is designed to track memory of a section of a system
 * upon instance creation this object will contain the total amount of 
 * memory used (total memory reserved by java - memory reserved but not used)
 * 
 * The at any point your application can use
 * the assertUse method to assert that memory used since the creation 
 * of the GCTracker object is less than a certain threshold
 * 
 * @author scott
 *
 */
public class GCTracker {
	private static long gcs = 0;
	private static Long inital_gcs;
	private static boolean log = true;

	private Long start;
	private Long end;
	private String name = "";
	
	
	public GCTracker(Class clazz, String p_test_name) {
		name = clazz.getName() + ";" + p_test_name;
		if (log) {
			System.out.println("start " + name);
		}
		start = getMemoryUse();
		
		// @todo waiting on a bug from sun to fix this issue
		//TestCase.assertTrue("Total memory used sould always be above 0 ", start >= 0);
	}

	/**
	 * this method can be used to assert that the NEW memory used
	 * since this object was created is below a certain point
	 * 
	 * @param threshold
	 */
	public void assertUse(long threshold) {
		end = getMemoryUse();
		long used = end - start;
		if (log) {
	    	System.out.println("used memory is " + used + " (end-start) end " + end + 
	    			", start " + start + "  in " + name);
	    }
		
		// @todo waiting on a bug from sun to fix this issue
		//TestCase.assertTrue("Total memory used sould always be above 0 ", used >= 0);
		
		TestCase.assertTrue("The " + name + " should take up less than " +
				threshold + " memeory and it took " + used + 
				" in "+ name, threshold > used);
				
		System.out.println("Memory assertion is not on! " +
				"The " + name + " should take up less than " +
			threshold + " memeory and it took " + used + 
			" in "+ name);	
	}
	
	public long getMemoryUse(){
		forceGc();
		
		long totalMemory = Runtime.getRuntime().totalMemory();
	    
	    long freeMemory = Runtime.getRuntime().freeMemory();
	    long toRet = (totalMemory - freeMemory);
	    if (log) {
	    	System.out.println("total memory is " + totalMemory + " and free " 
	    			+ freeMemory + " returning " + toRet + " in " + name);
	    }
	    return toRet;
 }


	/**
	 * this calls the garbage collector in a synchronus way so that
	 * it waits to return until the garbage collector has actully completed
	 * 
	 * this assumes your using regular jvm settings
	 * what ever those are
	 * 
	 * what i mean to say is no special garbage collection arguments
	 * 
	 */
	public static synchronized void forceGc() {
		/**
		long total_gcs = getTotalGcs();
		if (inital_gcs == null) {
			inital_gcs = total_gcs + 1;
			if (log) {
				System.out.println("Set inital gcs to " + inital_gcs);
			}
		}
		if (gcs <= inital_gcs) {
			while (gcs <= inital_gcs) {
				callGc();
			}
			total_gcs = getTotalGcs();
			while (gcs < total_gcs) {
				try {
					Thread.sleep(20); 
					System.out.println("waiting for garbage collector to run for memory assertions " +
							total_gcs);
				} catch (Exception x) {
					x.printStackTrace();
				}
				total_gcs = getTotalGcs();
			}
		} else {
			callGc();
			total_gcs = getTotalGcs();
			
			while (gcs < total_gcs) {
				try {
					Thread.sleep(20); 
					System.out.println("waiting for garbage collector to run for memory assertions " +
							total_gcs);
				} catch (Exception x) {
					x.printStackTrace();
				}
				total_gcs = getTotalGcs();
			}
		}
		*/
		GarbageTracker gt = new GarbageTracker();
		try {
			gt.verifyCollection(0);
		} catch (Exception x) {
			x.printStackTrace();
		}
	}


	private static void callGc() {
		if (log) {
			System.out.println("calling gc suggestion");
		}
		Runtime.getRuntime().gc();
		Runtime.getRuntime().runFinalization();
		gcs++;
	}


	public static long getTotalGcs() {
		List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
		Iterator<GarbageCollectorMXBean> it = gcs.iterator();
		long maxCollections = 0;
		
		while (it.hasNext()) {
			GarbageCollectorMXBean bean = it.next();
			if (log) {
				System.out.println("mxbean " + bean.getCollectionCount() + 
						" " + bean.getName() + " " + bean.getCollectionTime() + " " +
						bean.isValid());
			}
			long current = bean.getCollectionCount();
			if (maxCollections < current) {
				maxCollections = current;
			}
		}
		return maxCollections;
	}

	public static boolean isLog() {
		return log;
	}


	public static void setLog(boolean log) {
		GCTracker.log = log;
	}

	
}
