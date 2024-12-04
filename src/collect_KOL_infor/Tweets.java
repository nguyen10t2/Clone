package collect_KOL_infor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tweets extends CollectX {

    public Tweets(WebDriver driver) {
        super(driver);
    }

    @Override
    public void crawlingInfor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollCount = 0;

        try {
            while (this.collection.size() < collectLimitation && scrollCount < collectLimitation) {
                List<WebElement> tweets = driver.findElements(By.cssSelector("article"));
                boolean checkBreak = true;
                for (WebElement tweet : tweets) {
                    try {
                        // Tìm phần tử chứa URL bài tweet
                        WebElement ownerElement = tweet.findElement(By.xpath(".//a[contains(@href, '/status/')]"));
                        String tweetUrl = ownerElement.getDomAttribute("href");

                        WebElement userElement = tweet.findElement(By.xpath(".//div[@data-testid='User-Name']//a"));
                        String ownerName = userElement.getDomAttribute("href");
                        
                        String entry = tweetUrl + " "  + ownerName;
                        this.collection.add(entry);

                        if (this.collection.size() >= collectLimitation) {
                            checkBreak = false;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing a tweet: " + e.getMessage());
                    }
                }

                // Cuộn trang xuống để tải thêm nội dung
                js.executeScript("window.scrollBy(0, 1000);");
                Thread.sleep(3000); // Chờ nội dung tải thêm
                scrollCount++;

                if(checkBreak == false) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred in Tweets.");
        } 
    }
}
