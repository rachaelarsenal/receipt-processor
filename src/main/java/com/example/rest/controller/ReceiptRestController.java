package com.example.rest.controller;

import jakarta.validation.Valid;
import com.example.model.Receipt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.util.ReceiptCalculator;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/receipts")
public class ReceiptRestController {

	@PostMapping("/process")
	public ResponseEntity<Map<String, String>> processReceipt(
		@Valid @RequestBody Receipt receipt) {

		String id = UUID.randomUUID().toString();

		_receipts.put(
			id,
			ReceiptCalculator.calculatePoints(receipt, _isLargeLanguageModel));

		return ResponseEntity.ok(Collections.singletonMap("id", id));
	}

	@GetMapping("/{id}/points")
	public ResponseEntity<Map<String, ?>> getPoints(
		@PathVariable String id) {

		if (!_receipts.containsKey(id)) {
			return ResponseEntity.status(
				HttpStatus.NOT_FOUND
			).body(
				Collections.singletonMap(
					"error", "No receipt found for that ID.")
			);
		}

		return ResponseEntity.ok(
			Collections.singletonMap("points", _receipts.get(id)));
	}

	private final Map<String, Integer> _receipts = new ConcurrentHashMap<>();

	@Value("${large.language.model:false}")
	private boolean _isLargeLanguageModel;

}