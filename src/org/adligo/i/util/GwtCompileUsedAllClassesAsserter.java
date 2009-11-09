package org.adligo.i.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ClassUsageView;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_UsageHolder;
import org.adligo.i.util.client.UtilEntryPoint;
import org.adligo.tests.ATest;



public class GwtCompileUsedAllClassesAsserter extends ATest implements I_UsageHolder {
	private static final Log log = LogFactory.getLog(GwtCompileUsedAllClassesAsserter.class);
	private Set<Class<?>> usedClasses = new HashSet<Class<?>>();
	
	/**
	   * list Classes inside a given package
	   * 
	   * @return Set<Class<?>> classes inside the root of the given package
	   * @throws ClassNotFoundException if the Package is invalid
	   */
	  public static Set<Class<?>> getClasses(String packageName, Set<String> ignore) throws ClassNotFoundException, IOException {
	    File directory=null;
	    String path = packageName.replace('.', '/');
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	    Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (File dir : dirs) {
            classes.addAll(findClasses(dir, packageName, ignore));
        }
	    Set<Class<?>> toRet = new HashSet<Class<?>>();
	    toRet.addAll(classes);
	    return toRet;
	  } 
	
	    /**
	     * Recursive method used to find all classes in a given directory and subdirs.
	     *
	     * @param directory   The base directory
	     * @param packageName The package name for classes found inside the base directory
	     * @return The classes
	     * @throws ClassNotFoundException
	     */
	    private static List<Class<?>> findClasses(File directory, String packageName, 
	    		Set<String> ignore) throws ClassNotFoundException {
	        List<Class<?>> classes = new ArrayList<Class<?>>();
	        if (!directory.exists()) {
	            return classes;
	        }
	        File[] files = directory.listFiles();
	        for (File file : files) {
	            if (file.isDirectory()) {
	                assert !file.getName().contains(".");
	                classes.addAll(findClasses(file, packageName + "." + file.getName(), ignore));
	            } else if (file.getName().endsWith(".class")) {
	               String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
	               if (ignore.contains(className)) {
	            	   if (log.isDebugEnabled()) {
	            		   log.debug("ignoring class " + className);
	            	   }
	               } else {
		               try  {
		                	classes.add(Class.forName(className));
		                } catch (Exception x) {
		                	if (log.isDebugEnabled()) {
		                		log.debug("didn't find class " + className + "  in  " +
		                				file.getAbsolutePath());
		                	}
		                }
	               }
	            }
	        }
	        return classes;
	    }

	public void testUsedAllI_UtilClasses() throws Exception {
		//ignore classes in this (i_util_tests) project 
		Set<String> ignore = new HashSet<String>();
		String utilEntryPointClassName = ClassUtils.getClassName(UtilEntryPoint.class);
		ignore.add(ClassUtils.getClassName(ClassUsageView.class));
		ignore.add(ClassUtils.getClassName(I_UsageHolder.class));
		ignore.add(utilEntryPointClassName);
		
		for (int i = 0; i <= 12; i++) {
			ignore.add(utilEntryPointClassName + "$" + i);
		}
		if (log.isDebugEnabled()) {
			log.debug("ignoring classes " + ignore);
		}
		
		Set<Class<?>> classes = getClasses("org.adligo.i.util.client", ignore);
		
		UtilEntryPoint entryPoint = new UtilEntryPoint(this);
		entryPoint.onModuleLoad();
		
		assertEquals(classes, usedClasses);
		
	}
	
	/**
	 * tries to be intelligent and tell you which ones are missing
	 * @param expected
	 * @param actual
	 */
	public static void assertEquals(Set<Class<?>> expected, Set<Class<?>> actual) {
		
		Set<Class<?>> a = new HashSet<Class<?>>();
		a.addAll(expected);
		a.removeAll(actual);
		
		if (a.size() > 0) {
			assertTrue("the expected classes " + a  + " were not contained in the actual results", false);
		}
		a = new HashSet<Class<?>>();
		a.addAll(actual);
		a.removeAll(expected);
		if (a.size() > 0) {
			assertTrue("the actual classes contained extra classes " + a , false);
		}
		
		assertEquals(expected.size(), actual.size());
		if (log.isDebugEnabled()) {
			log.debug("actual classes " + actual);
			log.debug("expected classes " + expected);
		}
		
	}
	
	public void addUsed(Object o) {
		Class<?> clazz = o.getClass();
		addUsed(clazz);
	}

	public void addUsed(Class<?> clazz) {
		usedClasses.add(clazz);
	}

	public Set<Class<?>> getUsedClasses() {
		return usedClasses;
	}
}
