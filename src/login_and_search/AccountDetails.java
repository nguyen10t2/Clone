
package login_and_search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class AccountDetails {
	private String gmail;
	private String username;
	private String password;
	private String accountInforPath;

	public void getInfor() {
		try {
			URL url = AccountDetails.class.getProtectionDomain().getCodeSource().getLocation();
			String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());  	
	    	File parentFile = new File(decodedPath);
	    	this.accountInforPath = parentFile.getPath() + "\\resources\\infor.txt";
		} catch(Exception e) {
			System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
		}
		try (BufferedReader br = new BufferedReader(new FileReader(accountInforPath))) {
            setGmail(br.readLine());
            setUsername(br.readLine());
            setPassword(br.readLine());
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
        }
	}
	
	
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	

	
}
