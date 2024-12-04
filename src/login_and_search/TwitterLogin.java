package login_and_search;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;
public class TwitterLogin implements LoginEngine{
	private WebDriver driver;
	private AccountDetails account;
	private String url;

	
	
	public TwitterLogin () {
		this.driver = new ChromeDriver();
		this.account = new AccountDetails();
	}

	@Override
	public void setUpInfor() {
		account.getInfor();
	}

	@Override
	public void getWeb() {
		driver.get(url);
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void fillGmail() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String gmail = account.getGmail();
		try {
            WebElement gmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("text")));
            gmailField.sendKeys(gmail + Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Không cần nhập gmail, chuyển sang nhập username.");
        }
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("An error occurred in TwitterLogin.");
        }
	}
	
	@Override
	public void fillUsername() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String username = account.getUsername();
		try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("text")));
            usernameField.sendKeys(username + Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Không cần nhập username, chuyển sang nhập mật khẩu.");
        }
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void fillPassword() {
		String password = account.getPassword();
		WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password + Keys.ENTER);
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void close() {
		this.driver.close();
	}

	@Override
	public void init(String url) {
		setUrl(url);
		getWeb();
		setUpInfor();
		fillGmail();
		fillUsername();
		fillPassword();
	}
	
	@Override
	public WebDriver getWebDriver() {
		return this.driver;
	}

	@Override
	public void setUrl(String url){
		this.url = url;
	}

	
}
