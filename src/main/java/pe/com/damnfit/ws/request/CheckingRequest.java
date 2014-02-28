package pe.com.damnfit.ws.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class CheckingRequest {
	
	@XmlElement(name = "namegym")
	private String namegym;
	@XmlElement(name = "address")
	private String address;
	@XmlElement(name = "distance")
	private String distance;
	@XmlElement(name = "profileId")
	private String profileId;
	
	
	public String getNamegym() {
		return namegym;
	}
	public void setNamegym(String namegym) {
		this.namegym = namegym;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public CheckingRequest(String namegym, String address, String distance,
			String profileId) {
		super();
		this.namegym = namegym;
		this.address = address;
		this.distance = distance;
		this.profileId = profileId;
	}
	public CheckingRequest() {
		
	}
	
	
}
