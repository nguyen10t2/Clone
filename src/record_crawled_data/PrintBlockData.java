package record_crawled_data;

import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class PrintBlockData {
    private InforOfKOL infor;
	private String logInfor = "";
	
	public PrintBlockData(WebDriver driver) {
        this.infor = new InforOfKOL(driver);
	}
	
	public void printFile(String url, FileWriter writer) {
        this.infor.getFollowers().setCollectLimitation(70);
        this.infor.getTweets().setCollectLimitation(150);
        this.infor.getFollowers().getBrowser(url);
        this.infor.getFollowers().crawlingInfor();
        this.infor.getTweets().getBrowser(url);
        this.infor.getTweets().crawlingInfor();


        String username = url.substring(url.lastIndexOf("/") + 1);


        this.logInfor += url;
        this.logInfor += ",";
        this.logInfor += username;
        this.logInfor += ",";

        this.logInfor += String.valueOf(infor.getFollowers().getCollection().size());
        this.logInfor += ",";
        for(String auto : infor.getFollowers().getCollection()) {
            this.logInfor += auto;
            this.logInfor += " ";
        }

        this.logInfor += ",";

        this.logInfor += String.valueOf(infor.getTweets().getCollection().size());
        this.logInfor += ",";
        for(String entry : infor.getTweets().getCollection()) {
            this.logInfor += entry;
            this.logInfor += " ";
        }

        try {
            writer.append(logInfor + "\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public InforOfKOL getInfor() {
        return infor;
    }

    public void setInfor(InforOfKOL infor) {
        this.infor = infor;
    }

    public String getLogInfor() {
        return logInfor;
    }

    public void setLogInfor(String logInfor) {
        this.logInfor = logInfor;
    }

    
}
