package shopaholicjava;

public class Admin {

	private String AID;
	private String FirstName;
	private String LastName; 
	private String UserName;
	private String UserPassword;
	
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "User [UID=" + AID + ", UserName=" + UserName + ", UserPassword=" + UserPassword + "]";
	}
	
}
