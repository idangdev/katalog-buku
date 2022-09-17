package com.idangdev.catalog.security.util;

public interface TokenExtractor {
	
	public String extract(String payload);
	
}
