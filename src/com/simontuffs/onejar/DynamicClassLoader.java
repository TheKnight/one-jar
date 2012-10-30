/*
 * Copyright (c) 2004-2010, P. Simon Tuffs (simon@simontuffs.com)
 * All rights reserved.
 *
 * See the full license at http://one-jar.sourceforge.net/one-jar-license.html
 * This license is also included in the distributions of this software
 * under doc/one-jar-license.txt
 *
 * @author Christopher Ottley
 *
 */
package com.simontuffs.onejar;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Referenced as an alternative class loader to allow for dynamic class loading
 * for scripting languages like groovy.
 *
 * @author Christopher Ottley
 */
public class DynamicClassLoader {

  public static ArrayList dynamicClassLoaders = new ArrayList();

  public static synchronized void addClassLoader(ClassLoader newClassLoader) {
    dynamicClassLoaders.add(newClassLoader);
  }
  
  public static Class loadClass(String name) throws ClassNotFoundException {
    Iterator it = dynamicClassLoaders.iterator();
   
    while(it.hasNext()) {
      try {
        return ((ClassLoader)it.next()).loadClass(name);
      } catch (ClassNotFoundException cnfx) {
        // Ignore
      }
    }
    
    throw new ClassNotFoundException("Cannot load class " + name + " using Dynamic class loader");
  }
  
}
