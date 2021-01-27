package com.sergio.domain;

import java.util.HashMap;
import java.util.Map;


/**
 * Map of products.
 */
public class PriceList {

	private static final Map<String, Double> PRODUCTS = initMap();

	private PriceList() {
	}

	private static Map<String, Double> initMap() {
		Map<String, Double> products = new HashMap<>();
		products.put("Product1", 30.0);
		products.put("Product2", 10.0);
		products.put("Product3", 40.0);
		products.put("Product4", 60.0);
		products.put("Product5", 340.0);
		return products;
	}

	public static Map<String, Double> getPRODUCTS() {
		return PRODUCTS;
	}
}
