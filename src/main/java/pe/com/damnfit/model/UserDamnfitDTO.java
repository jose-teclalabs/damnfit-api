package pe.com.damnfit.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "user_damnfit", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
public class UserDamnfitDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Integer id;
	
	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private Integer active = 1;

	@Column(name = "facebookid")
	private String facebookId;

	@Column(name = "facebooktoken")
	private String facebookToken;

	@Column(name = "twitterid")
	private String twitterId;

	@Column(name = "twittertoken")
	private String twitterToken;

	@Column(name = "runkeeperid", nullable = false)
	private String runkeeperId;

	@Column(name = "runkeepertoken", nullable = false)
	private String runkeeperToken;
	
	@Transient
	Message message;
	
	public UserDamnfitDTO() {
	}
	
	public UserDamnfitDTO(Integer id) {
		this.id = id;
	}
	
	public UserDamnfitDTO(Message message) {
	  this.message = message;
	}
	
	public UserDamnfitDTO(Integer id, Message message) {
		this.id = id;
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getTwitterToken() {
		return twitterToken;
	}

	public void setTwitterToken(String twitterToken) {
		this.twitterToken = twitterToken;
	}

	public String getRunkeeperId() {
		return runkeeperId;
	}

	public void setRunkeeperId(String runkeeperId) {
		this.runkeeperId = runkeeperId;
	}

	public String getRunkeeperToken() {
		return runkeeperToken;
	}

	public void setRunkeeperToken(String runkeeperToken) {
		this.runkeeperToken = runkeeperToken;
	}

	@Override
	public String toString() {
		return "UserDamnfitDTO [id=" + id + ", username=" + username
				+ ", password=" + password + ", active=" + active
				+ ", facebookId=" + facebookId + ", facebookToken="
				+ facebookToken + ", twitterId=" + twitterId
				+ ", twitterToken=" + twitterToken + ", runkeeperId="
				+ runkeeperId + ", runkeeperToken=" + runkeeperToken + "]";
	}
}
