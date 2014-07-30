package com.github.aureliano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is intended to annotate a mapping-type class with metadata
 * about its index. Because of that it is possible to create mapping-types from
 * command line app just using a model class.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ESIndex {

	String name(); 
}