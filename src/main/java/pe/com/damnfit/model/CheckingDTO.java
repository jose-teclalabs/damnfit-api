package pe.com.damnfit.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "checking", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
public class CheckingDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer check_id;

	private Integer status;
	private String namegym;
	private String address;
	private Date checkdate;
	private String distance;

	@ManyToOne(targetEntity = ProfileGoalDTO.class)
	@JoinColumn(name = "profile_goal_id")
	private ProfileGoalDTO profileGoal;

	@Transient
	Message message;

	@Transient
	Integer goalState;
	
	public CheckingDTO() {
	}

	public CheckingDTO(Integer check_id) {
		this.check_id = check_id;
	}

	public CheckingDTO(Message message) {
		this.message = message;
	}

	public CheckingDTO(Integer check_id, Integer goalState, Message message) {
		this.check_id = check_id;
		this.message = message;
		this.goalState = goalState;
	}

	public CheckingDTO(String namegym, String address, String distance,
			ProfileGoalDTO profileGoal) {
		super();
		this.namegym = namegym;
		this.address = address;
		this.distance = distance;
		this.profileGoal = profileGoal;
	}

	public CheckingDTO(String address2, String distance2, String namegym2) {
		this.address = address2;
		this.distance = distance2;
		this.namegym = namegym2;
	}

	public Integer getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer goalState) {
		this.goalState = goalState;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getCheck_id() {
		return check_id;
	}

	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public Date getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public ProfileGoalDTO getProfileGoal() {
		return profileGoal;
	}

	public void setProfileGoal(ProfileGoalDTO profileGoal) {
		this.profileGoal = profileGoal;
	}

	@Override
	public String toString() {
		return "CheckingDTO [check_id=" + check_id + ", status=" + status
				+ ", namegym=" + namegym + ", address=" + address
				+ ", checkdate=" + checkdate + ", distance=" + distance + "]";
	}	
}
