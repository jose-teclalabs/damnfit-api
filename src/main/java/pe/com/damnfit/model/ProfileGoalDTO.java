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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "profile_goal", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
@NamedQueries({ 
    @NamedQuery(name = "ProfileGoalDTO.findDeviceByIdUser", query = "SELECT d FROM ProfileGoalDTO d WHERE d.profile.id = :idPaUser")
   })
public class ProfileGoalDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "profile_goal_id")
	private Integer profileGoalId;

	private Integer active;

	@Column(name = "bind_date")
	private Date bindDate;

	private Double progress;

	private Integer status;

	@Column(name = "close_date")
	private Date closeDate;

	@ManyToOne(targetEntity=ProfileDTO.class , fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id")
	private ProfileDTO profile;

	
	@ManyToOne(targetEntity=GoalDTO.class)
    @JoinColumn(name="goal_id")
	private GoalDTO goal;

	@OneToMany(targetEntity = CheckingDTO.class, mappedBy = "profileGoal", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<CheckingDTO> checkingList = new HashSet<CheckingDTO>(0);

	@Transient
	Message message;

	public Set<CheckingDTO> getCheckingList() {
		return checkingList;
	}

	public void setCheckingList(Set<CheckingDTO> checkingList) {
		this.checkingList = checkingList;
	}

	public ProfileGoalDTO() {
	}

	public ProfileGoalDTO(Integer profileGoalId) {
		this.profileGoalId = profileGoalId;
	}

	public ProfileGoalDTO(Message message) {
		this.message = message;
	}

	public ProfileGoalDTO(Integer profileGoalId, Message message) {
		this.profileGoalId = profileGoalId;
		this.message = message;
	}

	public ProfileGoalDTO(ProfileDTO profileDTO) {

		this.profile = profileDTO;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getProfileGoalId() {
		return profileGoalId;
	}

	public void setProfileGoalId(Integer profileGoalId) {
		this.profileGoalId = profileGoalId;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}

	public GoalDTO getGoal() {
		return goal;
	}

	public void setGoal(GoalDTO goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "ProfileGoalDTO [profileGoalId=" + profileGoalId + ", active="
				+ active + ", bindDate=" + bindDate + ", progress=" + progress
				+ ", status=" + status + ", closeDate=" + closeDate + "]";
	}
}
