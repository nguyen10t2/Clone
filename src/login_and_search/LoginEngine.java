package login_and_search;

import org.openqa.selenium.WebDriver;

public interface LoginEngine {
	public void getWeb();
	public void setUpInfor();
	public void fillGmail();
	public void fillUsername();
	public void fillPassword();
	public void close();
	public void init(String url);
	public WebDriver getWebDriver();
	public void setUrl(String url);
} 
