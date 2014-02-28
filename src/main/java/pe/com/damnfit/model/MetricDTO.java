package pe.com.damnfit.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;

@Entity
@Table(name = "metric", catalog = Constant.NAME_CATALOG, schema = Constant.NAME_SCHEMA)
@XmlRootElement
public class MetricDTO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name = "metric_id")
	private Integer idMetric;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "metric", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GoalDTO> goals = new HashSet<GoalDTO>(0);
	
	@Transient
	Message message;
	
	public MetricDTO() {
	}
	
	public MetricDTO(Integer idMetric) {
		this.idMetric = idMetric;
	}
	
	public MetricDTO(Message message) {
	  this.message = message;
	}
	
	public MetricDTO(Integer idMetric, Message message) {
		this.idMetric = idMetric;
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getIdMetric() {
		return idMetric;
	}

	public void setIdMetric(Integer idMetric) {
		this.idMetric = idMetric;
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
	
	public Set<GoalDTO> getGoals() {
		return goals;
	}

	public void setGoals(Set<GoalDTO> goals) {
		this.goals = goals;
	}

	@Override
	public String toString() {
		return "MetricDTO [idMetric=" + idMetric + ", name=" + name
				+ ", description=" + description + "]";
	}
}
