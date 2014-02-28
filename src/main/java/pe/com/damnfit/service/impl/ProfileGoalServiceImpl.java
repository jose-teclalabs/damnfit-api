package pe.com.damnfit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.damnfit.dao.GoalDao;
import pe.com.damnfit.dao.ProfileDao;
import pe.com.damnfit.dao.ProfileGoalDao;
import pe.com.damnfit.model.GoalDTO;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.Goal;
import pe.com.damnfit.pojo.Profile;
import pe.com.damnfit.pojo.ProfileGoal;
import pe.com.damnfit.pojo.ProfileLevel;
import pe.com.damnfit.service.ProfileGoalService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Levels;
import pe.com.damnfit.util.Message;
import pe.com.damnfit.ws.request.ProfileGoalRequest;

@Service
public class ProfileGoalServiceImpl implements ProfileGoalService {

	@Autowired
	ProfileGoalDao profilegoalDao;

	@Autowired
	ProfileDao profileDao;

	@Autowired
	GoalDao goalDao;
	
	private static final Log log = LogFactory.getLog(ProfileGoalServiceImpl.class);

	@Override
	public void persist(ProfileGoalDTO e) {

	}

	@Override
	public void merge(ProfileGoalDTO e) {

	}

	@Override
	public void remove(Object id) {

	}

	@Override
	public ProfileGoalDTO findById(Object id) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ProfileGoalDTO saveProfileGoal(ProfileGoalRequest request) {

		try {
			Integer iProfileLogged = null;
			Integer iGoal = null;
			try {
				iProfileLogged = Integer.parseInt(request.getProfileId());
				iGoal = Integer.parseInt(request.getGoalId());

			} catch (Exception e) {
				return new ProfileGoalDTO(new Message(false,
						Constant.PARAMETER_IS_NOT_PROPERLY_FORMATTED));
			}
			if (!request.getProfileId().equals(Constant.IS_EMPTY)
					&& !request.getGoalId().equals(Constant.IS_EMPTY)) {

				ProfileDTO profileReturn = profileDao.findById(iProfileLogged);
				if (profileReturn == null) {
					return new ProfileGoalDTO(new Message(false,
							Constant.MISSING_VALUES_PARAMETERS));
				}

				GoalDTO goalReturn = goalDao.findById(iGoal);
				if (goalReturn == null) {
					return new ProfileGoalDTO(new Message(false,
							Constant.MISSING_VALUES_PARAMETERS));
				}

				Goal goalTimerReturn = goalDao.getTimerByIdGoal(new GoalDTO(
						(Integer) iGoal));
				Date startDate = new Date();
				Date finalDate = new Date();
				finalDate.setHours(goalTimerReturn.getTimer());

				ProfileGoalDTO profilegoal = new ProfileGoalDTO();
				profilegoal.setProfile(profileReturn);
				profilegoal.setGoal(goalReturn);
				profilegoal.setBindDate(startDate);
				profilegoal.setProgress(0.0);
				profilegoal.setActive(1);
				profilegoal.setStatus(0);
				profilegoal.setCloseDate(finalDate);
				profilegoalDao.persist(profilegoal);
				return new ProfileGoalDTO(new Message(true, Constant.SUCCESS));
			} else {
				return new ProfileGoalDTO(new Message(false,
						Constant.MISSING_VALUES_PARAMETERS));
			}
		} catch (Exception e) {
			log.error("Error at save profile goal" );
			return new ProfileGoalDTO(new Message(false,
					Constant.DATABASE_ERROR));
		}

	}

	@Override
	public List<ProfileGoal> listProfileGoal(Integer id) {
		try{
			List<ProfileGoal> listprofileGoal = (List<ProfileGoal>) profilegoalDao.getProfileGoalByProfileId(new ProfileDTO(id));
			
			if(listprofileGoal.size() > 0){

					ProfileDTO profile = new ProfileDTO();
					profile.setUser_id(id);	
					
					List<ProfileGoalDTO> profileGoalList = new ArrayList<ProfileGoalDTO>();			
					  ProfileGoalDTO profileGoal = new ProfileGoalDTO();
					  profileGoal.setActive(Constant.GOAL_LOSE);		
					profileGoalList.add(profileGoal);
					profile.setProfileGoals( profileGoalList );
					
					profilegoalDao.changeStatusOfRange(profile);

			}
			return listprofileGoal;
		}catch(Exception e){
			log.error("Error at get profile goal list.");
			return null;
		}
	}

	@Override
	public ProfileGoalDTO getTotalPoint(ProfileGoalRequest request) {
		try{
			
			Integer iProfileLogged = null;
			try {
				iProfileLogged = Integer.parseInt(request.getProfileId());

			} catch (Exception e) {
				return new ProfileGoalDTO(new Message(false,
						Constant.PARAMETER_IS_NOT_PROPERLY_FORMATTED));
			}
				
			ProfileDTO profileReturn = profileDao.findById(iProfileLogged);
			if (profileReturn == null) {
				return new ProfileGoalDTO(new Message(false,
						Constant.PROFILE_NOT_FOUND));
			}							
			
			ProfileGoal profileGoal = profilegoalDao.sumPoints(profileReturn);
			if(profileGoal != null){
			Integer newSum = profileGoal.getTotal();
			if(newSum == null){
				return new ProfileGoalDTO(new Message(false,Constant.NOT_POINTS));
			}
			else{
				//actualizar el active luego de sumar
				profilegoalDao.updateActive(profileReturn);
			}
			profileDao.sumaPoints(profileReturn, newSum);
			
			//obteniendo los puntos del profile
			Profile profile = profileDao.getPointsFromProfile(profileReturn);
			Integer newPoints =profile.getTotalPoints();
			//obteniendo el nivel del profile
			ProfileLevel profilereturnLevel =  profileDao.getLevel(profileReturn);
			Integer actualLevel = profilereturnLevel.getLevels();

			while ((actualLevel < 99)
					&& (profile.getTotalPoints() >= Levels.levelPoints[actualLevel + 1])) {
				actualLevel++;
			}
			profile.setLevels(actualLevel);
			Integer levelReturn = profile.getLevels();
			//actualizando el nivel
			profileDao.updateLevel(profileReturn, levelReturn);					
			return new ProfileGoalDTO(newPoints, new Message(true ,Constant.SUCCESS));
			}
			return new ProfileGoalDTO(new Message(true,Constant.SUCCESS));
			
			
		}catch(Exception e){
			log.error("Error at save profile goal" );
			return new ProfileGoalDTO(new Message(false,
					Constant.DATABASE_ERROR));
		}
	}
}
