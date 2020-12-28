package com.example.hashtagcount.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hashtagcount.entities.TweetBean;

@Service
public interface MessageService {
	public List<TweetBean> getAndUpdateTopHashtag(String message);
}
