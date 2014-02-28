package pe.com.damnfit.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "goal", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
public class GoalDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Integer goal_id;
	
	private String name;

	private String description;

	private Integer points;

	private Integer time;

	private Integer active;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "image_url")
	private String imageUrl;

	private Double amount;
	
	@JoinColumn(name = "metric_id", referencedColumnName = "metric_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private MetricDTO metric;
	
	
	@OneToMany(targetEntity = ProfileGoalDTO.class, mappedBy = "goal", cascade = { CascadeType.ALL }, orphanRemoval = true)
//	@OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProfileGoalDTO> profileGoals = new HashSet<ProfileGoalDTO>(0);
	
	@Transient
	Message message;
	
	public GoalDTO() {
	}
	
	public GoalDTO(Integer goal_id) {
		this.goal_id = goal_id;
	}
	
	public GoalDTO(Message message) {
	  this.message = message;
	}
	
	public GoalDTO(Integer goal_id, Message message) {
		this.goal_id = goal_id;
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getGoal_id() {
		return goal_id;
	}

	public void setGoal_id(Integer goal_id) {
		this.goal_id = goal_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "GoalDTO [goal_id=" + goal_id + ", name=" + name
				+ ", description=" + description + ", points=" + points
				+ ", time=" + time + ", active=" + active + ", createDate="
				+ createDate + ", imageUrl=" + imageUrl + ", amount=" + amount
				+ "]";
	}
}
