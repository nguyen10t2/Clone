package collect_KOL_infor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Followers extends CollectX {

    public Followers(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getBrowser(String url) {
        this.driver.get(url + "/followers");
    }

    public int getKOLNumberOfFollowers(String url) {
        super.getBrowser(url);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element = driver.findElement(By.xpath("//a[contains(@href, '/verified_followers')]"));
        String result = element.getText();

        int followers = 0;
        int i = 0;
        while (result.charAt(i) != ' ') {
            i++;
        }
        if (Character.isDigit(result.charAt(i - 1))) {
            for (int k = 0; k <= i - 1; k++) {
                if (Character.isDigit(result.charAt(k))) {
                    followers = followers * 10 + (result.charAt(k) - 48);
                }
            }
        } else if (result.charAt(i - 1) == 'K') {
            int position = 0;
            int cnt = 0;
            for (int k = 0; k <= i - 2; k++) {
                if (Character.isDigit(result.charAt(k))) {
                    followers = followers * 10 + (result.charAt(k) - 48);
                    cnt++;
                } else {
                    position = k;
                }
            }
            if (position != 0) {
                followers = followers * (int) (Math.pow(10, 3 - (cnt - position)));
            } else {
                followers = followers * (int) (Math.pow(10, 3));
            }
        } else if (result.charAt(i - 1) == 'M') {
            int position = 0;
            int cnt = 0;
            for (int k = 0; k <= i - 2; k++) {
                if (Character.isDigit(result.charAt(k))) {
                    followers = followers * 10 + (result.charAt(k) - 48);
                    cnt++;
                } else {
                    position = k;
                }
            }
            if (position != 0) {
                followers = followers * (int) (Math.pow(10, 6 - (cnt - position)));
            } else {
                followers = followers * (int) (Math.pow(10, 6));
            }
        } else {
            System.out.println("LỖI LẤY SỐ FOLLOWERS");
        }

        return followers;
    }
}
