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
		products.put("Book", 30.0);
		products.put("TV", 500.0);
		products.put("Car", 40000.0);
		products.put("Mobile phone", 1000.0);
		products.put("Shoes", 100.0);
		return products;
	}

	public static Map<String, Double> getPRODUCTS() {
		return PRODUCTS;
	}
}
