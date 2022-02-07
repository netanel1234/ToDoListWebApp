package model;

/**
 * This class represents for each user
 * @author netan
 *
 */
public class User {
	
	private int userid;
	private String username;
	private String password;
	
	public User() 
	{	
		
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userid=" + userid + "]";
	}
}
