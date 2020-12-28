package com.example.hashtagcount.entities;

public class TweetBean {

	private String trendTagName;
	private Integer tweetCount;

	public String getTrendTagName() {
		return trendTagName;
	}

	public void setTrendTagName(String trendTagName) {
		this.trendTagName = trendTagName;
	}

	public Integer getTweetCount() {
		return tweetCount;
	}

	public void setTweetCount(Integer tweetCount) {
		this.tweetCount = tweetCount;
	}

	@Override
	public String toString() {
		return "TweetBean [trendTagName=" + trendTagName + ", tweetCount=" + tweetCount + "]";
	}

}
