package pe.com.damnfit.dao;

import java.util.List;

import pe.com.damnfit.base.GenericDao;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.ProfileGoal;

public interface ProfileGoalDao extends GenericDao<ProfileGoalDTO>{
	
	public ProfileGoal getProfileGoalId(ProfileGoalDTO devicePersist) throws Exception;
	public ProfileGoalDTO updateProgressOfOgal(ProfileGoalDTO profileGoal) throws Exception;
	public Integer updateStatus(ProfileGoalDTO profileGoal) throws Exception;
	public List<ProfileGoal> getProfileGoalByProfileId(ProfileDTO profile) throws Exception;
	public void changeStatusOfRange(ProfileDTO profile) throws Exception;
	public ProfileGoal sumPoints (ProfileDTO profile) throws Exception;
	public ProfileGoalDTO updateActive(ProfileDTO profile) throws Exception;
	
}
