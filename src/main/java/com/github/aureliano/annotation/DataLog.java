package com.github.aureliano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is intended to annotate a collector class with connection metadata.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataLog {

	String uri();
	String serverHost();
	int serverPort();
	String user();
	String password();
	HttpMethod method();
}