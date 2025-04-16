package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Item {

	public String getShortDescription() {
		return _shortDescription;
	}

	public String getPrice() {
		return _price;
	}

	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	public void setPrice(String price) {
		_price = price;
	}

	@NotBlank
	@Pattern(regexp = "^[\\w\\s\\-]+$")
	private String _shortDescription;

	@Pattern(regexp = "^\\d+\\.\\d{2}$")
	private String _price;

}