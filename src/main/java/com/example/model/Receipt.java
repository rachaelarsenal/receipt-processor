package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Receipt {

	public String getRetailer() {
		return _retailer;
	}

	public LocalDate getPurchaseDate() {
		return _purchaseDate;
	}

	public LocalTime getPurchaseTime() {
		return _purchaseTime;
	}

	public List<Item> getItems() {
		return _items;
	}

	public String getTotal() {
		return _total;
	}

	public void setRetailer(String retailer) {
		_retailer = retailer;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		_purchaseDate = purchaseDate;
	}

	public void setPurchaseTime(LocalTime purchaseTime) {
		_purchaseTime = purchaseTime;
	}

	public void setItems(List<Item> items) {
		_items = items;
	}

	public void setTotal(String total) {
		_total = total;
	}

	@NotBlank
	@Pattern(regexp = "^[\\w\\s\\-&]+$")
	private String _retailer;

	@NotNull
	private LocalDate _purchaseDate;

	@NotNull
	private LocalTime _purchaseTime;

	@NotNull
	@Size(min = 1)
	private List<Item> _items;

	@Pattern(regexp = "^\\d+\\.\\d{2}$")
	private String _total;

}