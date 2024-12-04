package record_crawled_data;

import collect_KOL_infor.Followers;
import collect_KOL_infor.Tweets;

import org.openqa.selenium.WebDriver;

public class InforOfKOL {
	private Followers followers;
	private Tweets tweets;
	
	public InforOfKOL(WebDriver driver) {
		this.followers = new Followers(driver);
		this.tweets = new Tweets(driver);
	}

	public Followers getFollowers() {
		return followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public Tweets getTweets() {
		return tweets;
	}

	public void setTweets(Tweets tweets) {
		this.tweets = tweets;
	} 
}
