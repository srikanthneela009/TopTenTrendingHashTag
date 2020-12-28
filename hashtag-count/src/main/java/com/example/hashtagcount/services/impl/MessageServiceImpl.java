package com.example.hashtagcount.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hashtagcount.entities.TweetBean;
import com.example.hashtagcount.services.MessageService;
import com.example.hashtagcount.utils.Constants;
import com.example.hashtagcount.utils.Utils;

@Service
public class MessageServiceImpl implements MessageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public List<TweetBean> getAndUpdateTopHashtag(String message) {
		LOGGER.info("Inside getAndUpdateTopHashtag method", this.getClass().getName());
		Utils.updateHashTags(message);
		return Utils.getTopHashTags(Constants.GET_TOP_HASHTAG_COUNT);
	}

}
