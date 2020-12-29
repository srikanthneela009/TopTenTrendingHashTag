package com.example.hashtagcount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.hashtagcount.entities.MessageBean;
import com.example.hashtagcount.entities.TweetBean;
import com.example.hashtagcount.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@PostMapping
	public @ResponseBody ResponseEntity<List<TweetBean>> sendMessage(@Valid @RequestBody MessageBean message) {
		LOGGER.info("Inside send message method", this.getClass().getName());
		List<TweetBean> topHashTags = null;
		try {

			if (message != null) {
				topHashTags = messageService.getAndUpdateTopHashtag(message.getMessage());
			}
		} catch (Exception ex) {
			LOGGER.error("Error occured in the getAndUpdateTopHashtag method", this.getClass().getName(), ex);
		}
		return ResponseEntity.status((topHashTags == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(topHashTags);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
