package com.hinodesoftworks.core;


import java.lang.reflect.Method;

import com.google.common.reflect.ClassPath;
import com.google.common.collect.ImmutableSet;

public class Core {
	
	public static final String CHALLENGE_PACKAGE = "com.hinodesoftworks.tests";
	public static final String TEST_METHOD_NAME = "runTest";
	

	public static void main(String[] args) {	
				
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try {
			ClassPath path = ClassPath.from(loader);
			
			ImmutableSet<ClassPath.ClassInfo> classes = path.getTopLevelClasses(CHALLENGE_PACKAGE);
			
			//empty arrays to pass to reflection methods to avoid compiler warnings
			//outside of the loop to save memory
			Class<?>[] eClass = null;
			Object[] eObject = null;
			
			for (ClassPath.ClassInfo info : classes){
				
				Class<?> clazz = info.load();
				
				if (Testable.class.isAssignableFrom(clazz)){
					System.out.println("------------");
					System.out.println(clazz.getName());
					
					Object instance = clazz.newInstance();
					Method testMethod = clazz.getMethod(TEST_METHOD_NAME, eClass);
					
					Object returnedValue = testMethod.invoke(instance, eObject);
					System.out.println(returnedValue.toString());	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}