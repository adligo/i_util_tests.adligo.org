package org.adligo.i.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.I_UsageHolder;



public class GwtCompileUsedAllClassesAsserter implements I_UsageHolder {
	private static final Log log = LogFactory.getLog(GwtCompileUsedAllClassesAsserter.class);
	private Set<Class<?>> usedClasses = new HashSet<Class<?>>();
	
	/**
	   * list Classes inside a given package
	   * 
	   * @return Set<Class<?>> classes inside the root of the given package
	   * @throws ClassNotFoundException if the Package is invalid
	   */
	  public static Set<Class<?>> getClasses(String packageName, Set<String> ignore) throws ClassNotFoundException, IOException {
	    String path = packageName.replace('.', '/');
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	    Enumeration<URL> resources = classLoader.getResources(path);
        Set<File> dirs = new HashSet<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (log.isDebugEnabled()) {
            	log.debug("url is " + resource + " protocol " + 
            			resource.getProtocol() + " path " + resource.getPath());
            }
            String fileName = resource.getPath();
            if ("jar".equals(resource.getProtocol())) {
            	//file:/
            	String jarName = fileName.substring(6, fileName.indexOf("!"));
            	if (log.isDebugEnabled()) {
                	log.debug("jarName is " + jarName);
                }
            	dirs.add(new File(jarName));
            } else {
            	dirs.add(new File(resource.getFile()));
            }
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (File dir : dirs) {
            classes.addAll(findClasses(dir, packageName, ignore));
        }
	    Set<Class<?>> toRet = new HashSet<Class<?>>();
	    toRet.addAll(classes);
	    if (log.isDebugEnabled()) {
	    	log.debug("returning " + toRet);
	    }
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
	        
	        String dirName = directory.getAbsolutePath();
	        if (log.isDebugEnabled()) {
        		log.debug("checking " + dirName);
        	}
	        
	        if (!directory.exists()) {
	        	if (log.isDebugEnabled()) {
	        		log.debug("directory " + directory + " does not exist!");
	        	}
	            return classes;
	        }
	        
	        if (directory.getName().endsWith(".jar")) {
            	if (log.isDebugEnabled()) {
            		log.debug("checking .jar file packageName " + packageName);
            	}
            	try {
            		String newPackage = packageName.replace('.', '/');
	            	ZipFile zip = new ZipFile(directory);
	            	Enumeration<? extends ZipEntry> entries = zip.entries();
	            	while (entries.hasMoreElements()) {
	            		ZipEntry entry = entries.nextElement();
	            		if (log.isDebugEnabled()) {
	                		log.debug("found entry " + entry.getName());
	                	}
	            		String name = entry.getName();
	            		if (name.contains(newPackage) && name.contains(".class")) {
	            			String clazzName = name.replace('/', '.');
	            			clazzName = clazzName.substring(0, clazzName.length() - 6);
	            			if (ignore.contains(clazzName)) {
	            				if (log.isDebugEnabled()) {
			                		log.debug("ignoring class " + clazzName);
			                	}
	            			} else {
		            			if (log.isDebugEnabled()) {
			                		log.debug("loading class " + clazzName);
			                	}
		            			classes.add(Class.forName(clazzName));
	            			}
	            		}
	            	}
            	} catch (IOException x) {
            		log.error(x.getMessage(), x);
            	}
            } else {
		        File[] files = directory.listFiles();
		        if (log.isDebugEnabled()) {
	        		log.debug("found " + files.length + " files in directory " + directory + ".");
	        	}
		        for (File file : files) {
		            if (file.isDirectory()) {
		                assert !file.getName().contains(".");
		                classes.addAll(findClasses(file, packageName + "." + file.getName(), ignore));
		            } else if (file.getName().endsWith(".class")) {
		            	if (log.isDebugEnabled()) {
		            		log.debug("found class " + file.getName() + ".");
		            	}
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
            }
	        if (log.isDebugEnabled()) {
        		log.debug("returning " + classes + ".");
        	}
	        return classes;
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
