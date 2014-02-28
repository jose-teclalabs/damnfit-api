package pe.com.damnfit.dao;

import java.util.List;

import pe.com.damnfit.base.GenericDao;
import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.Checking;

public interface CheckingDao extends GenericDao<CheckingDTO>{
	public Integer persistCheck(CheckingDTO c);
	public List<Checking> getlistById(ProfileDTO profile);
	public Checking getTimerByProfileId(ProfileGoalDTO profilegoal) throws Exception;
	
}
