package com.example.hashtagcount.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.hashtagcount.entities.TweetBean;

public class Utils {
	public static Pattern hashTagPattern = Pattern.compile(Constants.HASHTAG_REGEX_PATTERN);
	public static Map<String, Integer> hashTagsMap = new HashMap<String, Integer>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	public static boolean updateHashTags(String message) {
		LOGGER.info("Inside updateHashTags method");

		Matcher match = hashTagPattern.matcher(message);
		while (match.find()) {
			String hashTag = match.group(1);
			Integer hashtagCount = hashTagsMap.get(hashTag);
			if (hashtagCount != null && Integer.valueOf(hashtagCount) > 0) {
				hashtagCount += 1;
			} else {
				hashtagCount = 1;
			}
			hashTagsMap.put(hashTag, hashtagCount);
		}
		LOGGER.info(Arrays.asList(hashTagsMap).toString());
		return true;
	}

	public static List<TweetBean> getTopHashTags(Integer count) {
		LOGGER.info("Inside getTopHashTags method");
		if (hashTagsMap == null) {
			return null;
		}

		List<Entry<String, Integer>> values = new LinkedList<Entry<String, Integer>>(hashTagsMap.entrySet());

		Collections.sort(values, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {
				return (object2.getValue()).compareTo(object1.getValue());
			}
		});
		List<Entry<String, Integer>> topTrends = values.stream().limit(count).collect(Collectors.toList());
		return prepareList(topTrends);
	}

	public static List<TweetBean> prepareList(List<Entry<String, Integer>> list) {
		if (list == null)
			return null;

		List<TweetBean> topTrendsList = new ArrayList<>();
		TweetBean bean = null;
		for (Entry<String, Integer> entry : list) {
			bean = new TweetBean();
			bean.setTrendTagName(entry.getKey());
			bean.setTweetCount(entry.getValue());
			topTrendsList.add(bean);
		}
		return topTrendsList;
	}

}
