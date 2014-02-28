package pe.com.damnfit.service;

import java.util.List;

import pe.com.damnfit.base.GenericService;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.ProfileGoal;
import pe.com.damnfit.ws.request.ProfileGoalRequest;

public interface ProfileGoalService extends GenericService<ProfileGoalDTO> {
	
	public ProfileGoalDTO saveProfileGoal(ProfileGoalRequest request); 
	public List<ProfileGoal> listProfileGoal (Integer id);
	public ProfileGoalDTO getTotalPoint(ProfileGoalRequest request);
}
