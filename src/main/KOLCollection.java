package main;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import login_and_search.KOLSearch;
import login_and_search.LoginEngine;
import login_and_search.TwitterLogin;
import record_crawled_data.FileRecorded;



public class KOLCollection {
    public static void runKOLCollection() {
        try {
            String hashtag = "";
            URL url = KOLCollection.class.getProtectionDomain().getCodeSource().getLocation();
            
            String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());
            
            File parentFile = new File(decodedPath);
            String chromeDriverPath = parentFile.getPath() + "\\resources\\chromedriver.exe";
            String hashtagFilePath = parentFile.getPath() + "\\resources\\hashtag.txt";

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            try (BufferedReader br = new BufferedReader(new FileReader(hashtagFilePath))) {
                hashtag = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            LoginEngine login = new TwitterLogin();
            login.init("https://x.com/login");

            KOLSearch search = new KOLSearch(login.getWebDriver());
            search.init(hashtag);


            FileRecorded fileRecorded = new FileRecorded(login.getWebDriver());
            fileRecorded.getKOLs().setCollectLimitation(200);
            fileRecorded.getKOLs().crawlingInfor();

            fileRecorded.getKOLs().setCollection(fileRecorded.getKOLs().getCollection());
            fileRecorded.settingFile(login.getWebDriver());


            login.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
