package org.adligo.i.util;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.util.client.ClassUtils;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.impl.LegacySerializationPolicy;

public class IsGwtRpcSerializable  {
	private static final Log log = LogFactory.getLog(IsGwtRpcSerializable.class);
	private static Set<Character> WHITE_SPACE_CHARACTERS = getWhiteSpaceChars();
	private static Map<String,Class<?>> COMMON_CLASSES = getCommonClasses();
	
	private static Set<Character> getWhiteSpaceChars() {
		Set<Character> toRet = new HashSet<Character>();
		toRet.add(' ');
		toRet.add('\t');
		toRet.add('\n');
		toRet.add('\r');
		return toRet;
	}
	
	private static Map<String,Class<?>>  getCommonClasses() {
		Map<String,Class<?>> toRet = new HashMap<String,Class<?>>();
		toRet.put("String",String.class);
		toRet.put("Serializable", Serializable.class);
		toRet.put("IsSerializable", IsSerializable.class);
		toRet.put("Date",Date.class);
		toRet.put("Short",Short.class);
		toRet.put("Integer",Integer.class);
		toRet.put("Long",Long.class);
		toRet.put("Double",Double.class);
		toRet.put("Float",Float.class);
		toRet.put("Character",Character.class);
		toRet.put("Byte",Byte.class);
		toRet.put("Boolean",Boolean.class);
		return toRet;
	}
	/**
	 * this class should tell you why your class isn't running over GWT's RPC
	 * in a nicer way than a runtime exception
	 * 
	 * your source code must be on the class path for generics
	 * and you must use generics for Collection fields
	 * 
	 * @param clazz
	 */
	public static void isRpcSerializable(Class<?> clazz) throws SerializationException {
		Class<?> superClazz = clazz.getSuperclass();
		
		if (isCollection(clazz)) {
			return;
		}
		Collection<Class<?>> commonClasses = COMMON_CLASSES.values();
		if (commonClasses.contains(clazz)) {
			if (log.isDebugEnabled()) {
				log.debug(clazz + " is a A common class which is ok. ");
			}
			return;
		}
		/**
		 * I noticed after about 2 days of work that 
		 * when the GWT RPC serialization does it's thing 
		 * it only looks at the Fields of the class in question and not it's parents
		 * 
		 * So if you the following Classes
		 *     Foo implements IsSerializable {
		 *     	private String name;
		 *     }
		 *     
		 *     Bar extends Foo implements IsSerializable {
		 *     }
		 *     
		 *     and you pass Bar through RPC after setting the name to 'chris'
		 *     it will show up on the other machine as null, Bug or Feature? 
		 *     
		 *     
		 */
		if (superClazz != Object.class) {
			SerializationException ex = new SerializationException("Your Gwt Rpc Serializable class " +
					clazz + " must directly extend Object");
			ex.fillInStackTrace();
			throw ex;
		}
		
		IsGwtRpcBuilder builder = new IsGwtRpcBuilder();
		builder.setCurrentClass(clazz);
		builder.setRootClass(clazz);
		
		isRpcSerializable(builder);
	}

	private static boolean isCollection(Class<?> clazz) {
		if (Map.class.isAssignableFrom(clazz)) {
			if (log.isDebugEnabled()) {
				log.debug(clazz + " is a A Map class which is ok, however your runtime items may not be. ");
			}
			return true;
		}
		if (Collection.class.isAssignableFrom(clazz)) {
			if (log.isDebugEnabled()) {
				log.debug(clazz + " is a A Collection class which is ok, however your runtime items may not be. ");
			}
			return true;
		}
		return false;
	}
	
	private static void isRpcSerializable( IsGwtRpcBuilder builder) throws SerializationException {
			Class<?> clazz = builder.getCurrentClass();
			
			if (builder.isCheckingGeneric()) {
				if (log.isDebugEnabled()) {
					log.debug(" checking generic ");
				}
				if (isCollection(clazz)) {
					return;
				}
			}
			Collection<Class<?>> commonClasses = COMMON_CLASSES.values();
			if (commonClasses.contains(clazz)) {
				if (log.isDebugEnabled()) {
					log.debug(clazz + " is a A common class which is ok. ");
				}
				return;
			}
			
			List<Class<?>> parents = builder.getCurrentClassParents();
			
			LegacySerializationPolicy plcy = LegacySerializationPolicy.getInstance();
		
			Class<?>[] classArray = clazz.getInterfaces();
			Set<Class<?>> asSet = new HashSet<Class<?>>();
			for (int i = 0; i < classArray.length; i++) {
				asSet.add(classArray[i]);
			}
			
			if (asSet.contains(Serializable.class)) {
				if (log.isDebugEnabled()) {
					log.debug("class " + clazz + " implements " + Serializable.class);
				}
				assertFields(builder);
				assertConstructors(clazz, parents);
			} else if (asSet.contains(IsSerializable.class)) {
				if (log.isDebugEnabled()) {
					log.debug("class " + clazz + " implements " + IsSerializable.class);
				}
				assertFields(builder);
				assertConstructors(clazz, parents);
			} else {
				try {
					if (isCollection(builder)) {
						return;
					}
					plcy.validateSerialize(clazz);
					if (log.isDebugEnabled()) {
							log.debug("class " + clazz + " with parents " + parents +
									" is in the LegacySerializationPolicy which is ok. ");
					}
				} catch (Exception x) {
					SerializationException ex = new SerializationException(
							"class " + clazz + " with parents " + parents +
							" is not serlizeable see log. ");
					ex.initCause(x);
					throw ex;
				}
			}
		
	}

	private static boolean isCollection(IsGwtRpcBuilder builder) {
		
		Class<?> clazz = builder.getCurrentClass();
		String fieldName = builder.getCurrentField();
		List<Class<?>> parents = builder.getCurrentClassParents();
		
		if (Map.class.isAssignableFrom(clazz)) {
			if (log.isDebugEnabled()) {
				log.debug("class " + clazz + " with parent " + parents +
						" is a Map which is ok. Parsing java file looking for generic types " +
						"of field " + fieldName + ". ");
			}
			return parseJavaFileForCollection(builder, parents);
		}
		if (Collection.class.isAssignableFrom(clazz)) {
			if (log.isDebugEnabled()) {
				log.debug("class " + clazz + " with parent " + parents +
						" is a Collection which is ok. Checking .java file. ");
			}
			return parseJavaFileForCollection(builder, parents);
		}
		return false;
	}

	private static boolean parseJavaFileForCollection(IsGwtRpcBuilder builder,
			List<Class<?>> parents) {
		Class<?> parent = parents.get(0);
		String classJavaFileName = ClassUtils.getClassShortName(parent) + ".java";
		
		
		try {
			if (classJavaFileName.equals(builder.getCurrentClassJavaFileName())) {
				if (log.isDebugEnabled()) {
					log.debug("already parsed file " + classJavaFileName );
				}
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Parseing file " + classJavaFileName + " last file was " +
							builder.getCurrentClassJavaFileName());
				}
				String memberContent = removeContent(parent, classJavaFileName);
				builder.setCurrentClassJavaFileName(classJavaFileName);
				builder.setCurrentClassMemberContent(memberContent);
				if (log.isDebugEnabled()) {
					log.debug("memberContent is " +memberContent);
				}
			}
			//keep state which may change as the result of a Serializable class
			// in the content itself
			String currentJavaFile = builder.getCurrentClassJavaFileName();
			String currentMemberContent = builder.getCurrentClassMemberContent();
			
			//check file content for field
			checkFileContentForFieldGenerics(builder, -1);
			//replace state
			builder.setCurrentClassJavaFileName(currentJavaFile);
			builder.setCurrentClassMemberContent(currentMemberContent);
			
		} catch (Exception x) {
			RuntimeException toThrow = new RuntimeException("problem parsing file " + classJavaFileName +
					" you may need to add the source folder to your classpath of your test run.");
			toThrow.initCause(x);
			throw toThrow;
		}
		return true;
	}

	/**
	 * digs through file for a specific field
	 * @param builder
	 * @param lastIndex
	 * @param parentFile
	 * @param parentContent
	 * @throws Exception
	 */
	private static void checkFileContentForFieldGenerics(IsGwtRpcBuilder builder, int lastIndex) 
	throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("checkFileContentForFieldGenerics with file " + 
					builder.getCurrentClassJavaFileName() + " looking for field " +
					builder.getCurrentField());
		}
		String fieldName = builder.getCurrentField();
		String classJavaFileName = builder.getCurrentClassJavaFileName();
		String memberContent = builder.getCurrentClassMemberContent();
		
		int index = memberContent.indexOf(fieldName, lastIndex);
		if (index < 1) {
			SerializationException ex = new SerializationException(
					"Unable to find field " + fieldName + " in " +
					classJavaFileName);
			ex.fillInStackTrace();
			throw ex;
		} else if (index + fieldName.length() + 1 > memberContent.length()){
			SerializationException ex = new SerializationException(
					"Unable to find field " + fieldName + " in " +
					classJavaFileName);
			ex.fillInStackTrace();
			throw ex;
		} else {
			String surroundingField = memberContent.substring(index -1, index + fieldName.length() + 1);
			if (log.isDebugEnabled()) {
				log.debug("surrounding content is '" + surroundingField + "'");
			}
			char s = surroundingField.charAt(0);
			char e = surroundingField.charAt(surroundingField.length() -1);

			if (!WHITE_SPACE_CHARACTERS.contains(s)) {
				if (log.isDebugEnabled()) {
					log.debug("false positive search again first surrounding content character is '" + 
							s + "'");
				}
				checkFileContentForFieldGenerics(builder, index + 1);
			}
			//special semicolon case in end char
			if (!WHITE_SPACE_CHARACTERS.contains(e) && e != ';') {
				if (log.isDebugEnabled()) {
					log.debug("false positive search again last surrounding content character is '" + 
							e + "'");
				}
				checkFileContentForFieldGenerics(builder, index + 1);
			}
			if (log.isDebugEnabled()) {
				log.debug("it's a match! ");
			}
			StringBuilder sb = new StringBuilder();
			boolean inCarrot = false;
			int nestedCarrots = 0;
			
			for (int i = index -2; i >= 0; i--) {
				char c = memberContent.charAt(i);
				if (c == '<') {
					if (nestedCarrots == 0) {
						break;
					} else {
						nestedCarrots--;
						sb.insert(0, ',');
					}
				} else if (c == '>') {
					if (!inCarrot) {
						inCarrot = true;
					} else {
						nestedCarrots++;
					}
				} else {
					if (inCarrot) {
						sb.insert(0, c);
					}
				}
			}
			String genericContent = sb.toString();
			if (log.isDebugEnabled()) {
				log.debug("got generic content " + genericContent);
			}
			
			
			
			sb = new StringBuilder();
			char [] genericChars = genericContent.toCharArray();
			for (int i = 0; i < genericChars.length; i++) {
				char c = genericChars[i];
				if (c == ',') {
					
					builder.setCheckingGeneric(true);
					findAndAssertClass(sb.toString(), builder);
					
					sb = new StringBuilder();
				} else {
					sb.append(c);
				}
			}
			builder.setCheckingGeneric(true);
			findAndAssertClass(sb.toString(), builder);
			builder.setCheckingGeneric(false);
			
		}
		if (log.isDebugEnabled()) {
			log.debug(" exit checkFileContentForFieldGenerics with file " + builder.getCurrentClassJavaFileName() 
					+ " looking for field " +
					builder.getCurrentField());
		}
	}
	
	
	private static void findAndAssertClass(String className, IsGwtRpcBuilder builder) 
		 throws Exception {
		
		className = className.trim();
		if (log.isDebugEnabled()) {
			log.debug("enter with findAndAssertClass " + className);
		}
		
		if (className.indexOf('.') != -1) {
			if (log.isDebugEnabled()) {
				log.debug("class " + className + " is a full name");
			}
			Class<?> clazz = Class.forName(className);
			IsGwtRpcBuilder newBuilder = new IsGwtRpcBuilder(builder);
			newBuilder.getCurrentClassParents().add(0, clazz);
			isRpcSerializable(newBuilder);
		} else {
			Class<?> clazz = COMMON_CLASSES.get(className);
			if (clazz == null) {
				clazz = obtainClassFromImports(className, builder);
			} 
			IsGwtRpcBuilder newBuilder = new IsGwtRpcBuilder(builder);
			newBuilder.getCurrentClassParents().add(0, clazz);
			isRpcSerializable(builder);
		}
	}
	
	private static Class<?> obtainClassFromImports(String classShortName,IsGwtRpcBuilder builder) 
		throws Exception {
		
		
		String content = builder.getCurrentClassMemberContent();
		String packageName = content.substring(0, content.indexOf(";"));
		packageName = packageName.replace("package", "");
		packageName = packageName.trim();
		String imports = content.substring(content.indexOf(";") + 1, content.indexOf("{"));
		if (log.isDebugEnabled()) {
			log.debug("package '" + packageName + "'");
			log.debug("imports are " + imports);
		}
		
		int index = imports.indexOf(";");
		int lastIndex = 0;
		
		while (index != -1) {
			String nextImport = imports.substring(lastIndex, index);
			nextImport = nextImport.substring(nextImport.indexOf("import") + 6, nextImport.length());
			nextImport = nextImport.trim();
			
			
			if (nextImport.contains(classShortName)) {
				if (log.isDebugEnabled()) {
					log.debug("Class.forName " + nextImport + " from import?");
				}
				return Class.forName(nextImport);
			}
			lastIndex = index + 1;
			index = imports.indexOf(";", index + 1);
		}
		String className = packageName + "." + classShortName;
		//in same package?
		if (log.isDebugEnabled()) {
			log.debug("Class.forName '" + className + "' ?");
		}
		return Class.forName(className);
	}
	/**
	 * this removes all content which is 
	 * text curly braces {}
	 * except the first and last curly brace
	 * @param in
	 * @return
	 */
	private static String removeContent(Class<?> parent, String classJavaFileName) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		boolean inCBrace = false;
		int nestedCBrase = 0;
		boolean inBrace = false;
		int nestedBrase = 0;
		
		boolean foundFirstCBrase = false;
		InputStream is = parent.getResourceAsStream(classJavaFileName);
		if (log.isDebugEnabled()) {
			log.debug("got input stream " + is);
		}
		byte [] bytes = new byte[1];
		while (is.read(bytes) != -1) {
			char c = (char) bytes[0];
			if (c == '{') {
				if (!foundFirstCBrase) {
					foundFirstCBrase = true;
					//keep first curly brace for import parsing
					sb.append('{');
				} else {
					if (!inCBrace) {
						inCBrace = true;
						nestedCBrase = 0;
					} else {
						nestedCBrase++;
					}
				}
			} else if (c == '}') {
				if (inCBrace) {
					if (nestedCBrase > 0) {
						nestedCBrase--;
					} else {
						inCBrace = false;
					}
				}
			} else if (c == '(') {
				if (!inBrace) {
					inBrace = true;
					nestedBrase = 0;
				} else {
					nestedBrase++;
				}
			} else if (c == ')') {
				if (inBrace) {
					if (nestedBrase > 0) {
						nestedBrase--;
					} else {
						inBrace = false;
					}
				}
			} 
			else {
				if (!inCBrace && !inBrace) {
					sb.append(c);
				}
			}
		}
		is.close();
		return sb.toString();
	}

	private static void assertConstructors(Class<?> clazz, List<Class<?>> parents) throws SerializationException {
		Constructor<?> [] constructors = clazz.getConstructors();
		if (constructors.length == 0) {
			if (log.isDebugEnabled()) {
				log.debug("class " + clazz + " has no constructors which is ok");
			}
			return;
		}
		try {
			clazz.getConstructor();
		} catch (Exception e) {
			SerializationException ex = new SerializationException(
					"class " + clazz + " with parents " + parents +
					" does not have a zero arg constructor");
			ex.fillInStackTrace();
			throw ex;
		}
		if (log.isDebugEnabled()) {
			log.debug("class " + clazz + " has a zero arg constructor which is ok");
		}
	}

	private static void assertFields(IsGwtRpcBuilder builder) throws SerializationException {
		Class<?> clazz = builder.getCurrentClass();
		Field[] fields = clazz.getDeclaredFields();
		if (log.isDebugEnabled()) {
			log.debug("class " + clazz + " has a " + fields.length + " fields ");
		}
		builder.getCurrentClassParents().add(0, clazz);
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (log.isDebugEnabled()) {
				log.debug("checking field " + field.getName());
			}
			
			if (Modifier.isStatic(Modifier.STATIC)) {
				if (log.isDebugEnabled()) {
					log.debug("skipping static field " + field.getName());
				}
			} else {
				builder.setCurrentClass(field.getType());
				builder.setCurrentField(field.getName());
				isRpcSerializable(builder);
			}
		}
	}
}
