package pe.com.damnfit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "profile", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
public class ProfileDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Integer user_id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

	@Column(name = "points")
	private Integer points;

	@Column(name = "level")
	private Integer level;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "height")
	private Integer height;

	@Column(name = "age")
	private Integer age;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "createdate")
	private Date createDate;

	@Column(name = "lastupdate")
	private Date lastUpdate;
	
	@Column(name = "gender")
	private Integer gender;
	
	@Column(name = "image_url")
	private String imageUrl;
	@OneToMany(targetEntity = ProfileGoalDTO.class, mappedBy = "profile", cascade = { CascadeType.ALL }, orphanRemoval = true)
//	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProfileGoalDTO> profileGoals = new ArrayList<ProfileGoalDTO>(0);
	
	@Transient
	Message message;
	
	public ProfileDTO() {
	}
	
	public ProfileDTO(Integer user_id) {
		this.user_id = user_id;
	}
	
	public ProfileDTO(Message message) {
	  this.message = message;
	}
	
	public ProfileDTO(Integer user_id, Message message) {
		this.user_id = user_id;
		this.message = message;
	}


	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ProfileGoalDTO> getProfileGoals() {
		return profileGoals;
	}

	public void setProfileGoals(List<ProfileGoalDTO> profileGoal) {
		this.profileGoals = profileGoal;
	}

	@Override
	public String toString() {
		return "ProfileDTO [user_id=" + user_id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", points="
				+ points + ", level=" + level + ", weight=" + weight
				+ ", height=" + height + ", age=" + age + ", birthday="
				+ birthday + ", createDate=" + createDate + ", lastUpdate="
				+ lastUpdate + ", gender=" + gender + ", imageUrl=" + imageUrl
				+ "]";
	}
}
