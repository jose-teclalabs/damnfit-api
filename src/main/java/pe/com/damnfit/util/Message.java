package pe.com.damnfit.util;

public class Message {

	private boolean success;
	private String description;

	public Message(boolean success, String description) {
	    this.success = success;
		this.setDescription(description);
	} 
	
	public Message() {
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("");
		sb.append("[");
		sb.append(this.success);
		sb.append("]-");
		sb.append(this.description);
		sb.append(".");
		return sb.toString();
	}
}
