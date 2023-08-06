package shopaholicjava;

public class Review {
	private String RID;
	private String PID;
	private String Author;
	private String ProductName;
	private String ReviewDescription;
	private int Stars;
	
	public void setRID(String rID) {
		RID = rID;
	}
	
	public String getRID() {
		return RID;
	}
	
	public void setPID(String pID) {
		PID = pID;
	}
	
	public String getPID() {
		return PID;
	}
	
	public void setAuthor(String a) {
		Author = a;
	}
	
	public String getAuthor() {
		return Author;
	}
	 
	
	public void setProductName(String productName) {
		ProductName = productName;
	}
	
	public String getProductName() {
		return ProductName;
	}
	
	public void setReviewDescription(String desc) {
		ReviewDescription = desc;
	}
	
	public String getReviewDescription() {
		return ReviewDescription;
	}
	
	public void setStars(int desc) {
		Stars = desc;
	}
	
	public int getStars() {
		return Stars;
	}

	@Override
	public String toString() {
		return "Review [RID=" + RID + ", PID=" + PID + ", Author=" + Author + ", ProductName=" + ProductName
				+ ", ReviewDescription=" + ReviewDescription + ", Stars=" + Stars + "]";
	}
}
