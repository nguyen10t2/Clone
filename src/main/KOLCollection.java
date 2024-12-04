package main;


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

            String chromeDriverPath = getSourcePath("resources/chromedriver.exe");
            String hashtagFilePath = getSourcePath("resources/hashtag.txt");

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            try (BufferedReader br = new BufferedReader(new FileReader(hashtagFilePath))) {
                hashtag = br.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred in KOLCollection.");
            }

            LoginEngine login = new TwitterLogin();
            login.init("https://x.com/login");

            KOLSearch search = new KOLSearch(login.getWebDriver());
            search.init(hashtag);


            FileRecorded fileRecorded = new FileRecorded(login.getWebDriver());
            fileRecorded.getKOLs().setCollectLimitation(200);
            fileRecorded.getKOLs().crawlingInfor();
            System.out.println("Crawling information successfully");

            fileRecorded.getKOLs().setCollection(fileRecorded.getKOLs().getCollection());
            System.out.println("Set collection successfully");

            fileRecorded.settingFile(login.getWebDriver());
            System.out.println("Setting file successfully");

            login.close();

        } catch (Exception e) {
            System.out.println("An error occurred in KOLCollection.");
        }
    }
    public static String getSourcePath(String textName) {
        ClassLoader classLoader = KOLCollection.class.getClassLoader();
        return classLoader.getResource(textName).getPath().replace("\\", "/").substring(1);
    }
}
