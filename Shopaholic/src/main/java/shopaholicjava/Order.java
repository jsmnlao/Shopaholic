package shopaholicjava;

public class Order {

	private String OID;
	private String CID;
	private String ProductName;
	
	public String getOID() {
		return OID;
	}
	public void setOID(String oID) {
		OID = oID;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cid) {
		CID = cid;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}

	@Override
	public String toString() {
		return "Order [OID=" + OID + ", CID=" + CID + ", ProductName=" + ProductName + "]";
	}

}
