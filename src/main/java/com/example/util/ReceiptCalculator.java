package com.example.util;

import com.example.model.Receipt;
import com.example.model.Item;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReceiptCalculator {

	public static int calculatePoints(Receipt receipt) {
		return calculatePoints(receipt, false);
	}

	public static int calculatePoints(
		Receipt receipt, boolean isLargeLanguageModel) {

		int points = 0;

		Pattern pattern = Pattern.compile("[a-zA-Z0-9]");

		Matcher matcher = pattern.matcher(receipt.getRetailer());

		while (matcher.find()) {
			points++;
		}

		String total = receipt.getTotal();

		if (total.endsWith(".00")) {
			points += 50;
		}

		if (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) {
			points += 25;
		}

		List<Item> items = receipt.getItems();

		points += (items.size() / 2) * 5;

		for (Item item : items) {
			String shortDescription = item.getShortDescription();

			shortDescription = shortDescription.trim();

			if (shortDescription.length() % 3 == 0) {
				points += (int)Math.ceil(
					Double.parseDouble(item.getPrice()) * 0.2);
			}
		}

		if (isLargeLanguageModel) {
			if (Double.parseDouble(receipt.getTotal()) > 10.00) {
				points += 5;
			}
		}

		LocalDate purchaseDate = receipt.getPurchaseDate();

		if (purchaseDate.getDayOfMonth() % 2 == 1) {
			points += 6;
		}

		LocalTime purchaseTime = receipt.getPurchaseTime();

		if (purchaseTime.isAfter(LocalTime.of(14, 0)) &&
			purchaseTime.isBefore(LocalTime.of(16, 0))) {

			points += 10;
		}

		return points;
	}

}