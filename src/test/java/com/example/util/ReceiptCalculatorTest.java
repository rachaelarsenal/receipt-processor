package com.example.util;

import com.example.model.Item;
import com.example.model.Receipt;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptCalculatorTest {

	@Test
	public void testCalculatePoints_basicScenario() {
		Receipt receipt = new Receipt();

		receipt.setRetailer("M&M Corner Market");
		receipt.setPurchaseDate(LocalDate.of(2022, 1, 1));
		receipt.setPurchaseTime(LocalTime.of(13, 1));

		Item item = new Item();

		item.setShortDescription("Mountain Dew 12PK");
		item.setPrice("6.49");

		receipt.setItems(List.of(item));
		receipt.setTotal("6.49");

		int points = ReceiptCalculator.calculatePoints(receipt);

		assertTrue(points > 0);
	}

	@Test
	public void testCalculatePoints_roundDollar() {
		Receipt receipt = new Receipt();

		receipt.setRetailer("StoreX");
		receipt.setPurchaseDate(LocalDate.of(2022, 1, 3));
		receipt.setPurchaseTime(LocalTime.of(15, 0));

		Item item = new Item();

		item.setShortDescription("Item Three");
		item.setPrice("10.00");

		receipt.setItems(List.of(item));
		receipt.setTotal("10.00");

		int points = ReceiptCalculator.calculatePoints(receipt);

		assertTrue(points >= 50);
	}

	@Test
	public void testCalculatePoints_descriptionMultipleOf3() {
		Receipt receipt = new Receipt();

		receipt.setRetailer("Retailer");
		receipt.setPurchaseDate(LocalDate.of(2022, 1, 5));
		receipt.setPurchaseTime(LocalTime.of(14, 30));

		Item item = new Item();

		item.setShortDescription("AAA");
		item.setPrice("5.00");

		receipt.setItems(List.of(item));
		receipt.setTotal("5.00");

		int points = ReceiptCalculator.calculatePoints(receipt);

		assertTrue(points >= 25);
	}
}