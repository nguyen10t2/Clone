package record_crawled_data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.openqa.selenium.WebDriver;

import Enum.GetSource;
import collect_KOL_infor.KOL;

public class FileRecorded {

    private String filepath = GetSource.pathfOutput;
    private KOL KOLs;
    private int countBlocks = 0;

    public FileRecorded(WebDriver driver) {
        this.KOLs = new KOL(driver);
    }

    public void settingFile(WebDriver driver) {
        try {
            File file = new File(this.filepath);
            file.getParentFile().mkdirs();

            try (FileWriter fw = new FileWriter(this.filepath)) {
                fw.append("Link,Username,CountFollowers,Followers,CountTweets,Tweets\n");
                System.out.println("Setting file successfully in FileRecorded.");
                HashSet<String> KOLs = this.KOLs.getCollection();
                for (String entry : KOLs) {
                    PrintBlockData node = new PrintBlockData(driver);

                    if (node.getInfor().getFollowers().getKOLNumberOfFollowers(entry) >= GetSource.maxOfFolowers) {
                        node.printFile(entry, fw);
                        this.countBlocks++;
                        System.out.println("Save node data successfully");
                    }

                    if (this.countBlocks >= GetSource.MAX_BLOCKS) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred in FileRecorded.");
        }
    }

    public KOL getKOLs() {
        return KOLs;
    }

    public void setKOLs(KOL kOLs) {
        KOLs = kOLs;
    }
}
