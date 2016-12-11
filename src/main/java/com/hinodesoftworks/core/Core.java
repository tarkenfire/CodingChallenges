package com.hinodesoftworks.core;

import java.lang.reflect.Method;

import com.google.common.reflect.ClassPath;
import com.google.common.collect.ImmutableSet;

public class Core {

	public static final String TARGET_PACKAGE ="com.hinodesoftworks.challenges";
	public static final String TARGET_METHOD = "runTest";
	
	
	public static void main(String[] args) {	
				
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try {
			ClassPath path = ClassPath.from(loader);
			
			ImmutableSet<ClassPath.ClassInfo> classes = path.getTopLevelClasses(TARGET_PACKAGE);
			
			for (ClassPath.ClassInfo info : classes){
				
				Class<?> clazz = info.load();
				
				if (Testable.class.isAssignableFrom(clazz)){
					Object instance = clazz.newInstance();
					Method testMethod = clazz.getMethod(TARGET_METHOD, null);
					
					Object returnedValue = testMethod.invoke(instance, null);
					System.out.println(returnedValue.toString());	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}