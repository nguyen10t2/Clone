package collect_KOL_infor;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class CollectX {
    protected int collectLimitation;
    protected HashSet<String> collection;
    protected WebDriver driver;

    public CollectX(WebDriver driver) {
        this.driver = driver;
        this.collection = new HashSet<>();
    }

    public void crawlingInfor() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        
        try {
            int attempts = 0;
            int previousSize = 0;

            while (collection.size() < this.collectLimitation && attempts < this.collectLimitation) {
                // Đợi phần tử xuất hiện
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                List<WebElement> kolProfiles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("button[data-testid='UserCell']")
                ));

                // Thu thập dữ liệu
                for (WebElement profile : kolProfiles) {
                    String url = profile.findElement(By.cssSelector("a")).getAttribute("href");

                    collection.add(url);

                    if (collection.size() >= this.collectLimitation) {
                        break;
                    }
                }

                // Kiểm tra nếu không có dữ liệu mới, dừng lại
                if (collection.size() == previousSize) {
                    attempts++;
                } else {
                    attempts = 0; // Reset nếu có thêm dữ liệu
                }
                previousSize = collection.size();

                // Cuộn xuống cuối trang
                jsExecutor.executeScript("window.scrollBy(0, 1000);");
                Thread.sleep(2000); // Đợi nội dung mới tải
            }

        } catch (Exception e) {
            System.out.println("Error while collecting KOL data: " + e.getMessage());
        }

    }

    public void getBrowser(String url) {
        this.driver.get(url);
    }

    public int getCollectLimitation() {
        return collectLimitation;
    }

    public void setCollectLimitation(int collectLimitation) {
        this.collectLimitation = collectLimitation;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public HashSet<String> getCollection() {
        return collection;
    }

    public void setCollection(HashSet<String> collection) {
        this.collection = collection;
    }

    
    
}
