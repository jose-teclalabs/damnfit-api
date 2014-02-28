package pe.com.damnfit.pojo;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ProfileGoal {

	@XmlElement(name = "profilegoalId")
	private Integer profilegoalId;
	@XmlElement(name = "progress")
	private Double progress;
	@XmlElement(name = "status")
	private Integer status;
	@XmlElement(name = "binDate")
	private Date binDate;
	@XmlElement(name = "closeDate")
	private Date closeDate;
	@XmlElement(name = "goalId")
	private Integer goalId;
	@XmlElement(name = "finalDate")
	private String finalDate;
	@XmlElement(name = "gname")
	private String gname;
	@XmlElement(name = "gamount")
	private String gamount;
	@XmlElement(name = "mdescription")
	private String mdescription;
	@XmlElement(name = "asprogress")
	private String asprogress;
	@XmlElement(name = "active")
	private Integer active;

	@XmlElement(name = "total")
	private Integer total;
	public ProfileGoal() {
	}

	public Integer getGoalId() {
		return goalId;
	}

	public void setGoalId(Integer goalId) {
		this.goalId = goalId;
	}

	public Integer getProfilegoalId() {
		return profilegoalId;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public void setProfilegoalId(Integer profilegoalId) {
		this.profilegoalId = profilegoalId;
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

	public Date getBinDate() {
		return binDate;
	}

	public void setBinDate(Date binDate) {
		this.binDate = binDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGamount() {
		return gamount;
	}

	public void setGamount(String gamount) {
		this.gamount = gamount;
	}

	public String getMdescription() {
		return mdescription;
	}

	public void setMdescription(String mdescription) {
		this.mdescription = mdescription;
	}

	public String getAsprogress() {
		return asprogress;
	}

	public void setAsprogress(String asprogress) {
		this.asprogress = asprogress;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ProfileGoal [profilegoalId=" + profilegoalId + ", progress="
				+ progress + ", status=" + status + ", binDate=" + binDate
				+ ", closeDate=" + closeDate + ", goalId=" + goalId
				+ ", finalDate=" + finalDate + ", gname=" + gname
				+ ", gamount=" + gamount + ", mdescription=" + mdescription
				+ ", asprogress=" + asprogress + ", active=" + active
				+ ", total=" + total + "]";
	}
}
