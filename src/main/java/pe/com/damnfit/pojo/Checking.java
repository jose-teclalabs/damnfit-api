package pe.com.damnfit.pojo;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Checking {

	@XmlElement(name = "address")
	private String address;
	@XmlElement(name = "fechaCheck")
	private String fechaCheck;
	@XmlElement(name = "namegym")
	private String namegym;
	@XmlElement(name = "timer")
	private Calendar timer;
		
	
	
	public Checking() {
		super();
	}
	public Checking(String address, String fechaCheck, String namegym) {
		super();
		this.address = address;
		this.fechaCheck = fechaCheck;
		this.namegym = namegym;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFechaCheck() {
		return fechaCheck;
	}
	public void setFechaCheck(String fechaCheck) {
		this.fechaCheck = fechaCheck;
	}
	public String getNamegym() {
		return namegym;
	}
	public void setNamegym(String namegym) {
		this.namegym = namegym;
	}
	public Calendar getTimer() {
		return timer;
	}
	public void setTimer(Calendar timer) {
		this.timer = timer;
	}
	
}
