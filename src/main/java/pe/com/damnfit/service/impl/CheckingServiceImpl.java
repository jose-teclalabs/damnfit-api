package pe.com.damnfit.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.damnfit.dao.CheckingDao;
import pe.com.damnfit.dao.GoalDao;
import pe.com.damnfit.dao.ProfileDao;
import pe.com.damnfit.dao.ProfileGoalDao;
import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.model.GoalDTO;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.Checking;
import pe.com.damnfit.pojo.ProfileGoal;
import pe.com.damnfit.service.CheckingService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;
import pe.com.damnfit.ws.request.CheckingRequest;

@Service
public class CheckingServiceImpl implements CheckingService {

	@Autowired
	ProfileGoalDao profilegoalDao;

	@Autowired
	ProfileDao profileDao;

	@Autowired
	CheckingDao checkingDao;

	@Autowired
	GoalDao goalDao;

	private static final Log log = LogFactory.getLog(CheckingServiceImpl.class);

	@Override
	public CheckingDTO addChecking(CheckingRequest request) {
		Integer profileId = null;
		try {
			profileId = Integer.parseInt(request.getProfileId());
		} catch (Exception e) {
			log.error(e);
			return new CheckingDTO(new Message(false,Constant.PARAMETER_IS_NOT_PROPERLY_FORMATTED));
		}
		try {		
			if (profileId != null) {
				ProfileDTO profileVerify = profileDao.findById( profileId );	
				if(profileVerify == null ){
					return new CheckingDTO(new Message(false,Constant.PROFILE_NOT_FOUND));
				}
				
				ProfileGoal profilePOJO = profilegoalDao.getProfileGoalId(new ProfileGoalDTO(new ProfileDTO((Integer) profileId)));				
				if (profilePOJO == null) {
					return new CheckingDTO(new Message(false,Constant.YOU_NEED_MORE_GOALS));
				}
				ProfileGoalDTO profileGoalVerify = new ProfileGoalDTO();
				profileGoalVerify.setProfileGoalId(profilePOJO.getProfilegoalId());
				profileGoalVerify.setProgress( profilePOJO.getProgress() );
				profileGoalVerify.setBindDate( profilePOJO.getBinDate());
				profileGoalVerify.setCloseDate( profilePOJO.getCloseDate());
				profileGoalVerify.setStatus(profilePOJO.getStatus());			
				profileGoalVerify.setGoal( new GoalDTO( profilePOJO.getGoalId() ) );
				CheckingDTO checkSave =  new CheckingDTO(request.getAddress(),request.getDistance(),request.getNamegym());
				checkSave.setProfileGoal(profileGoalVerify);
				checkSave.setStatus(1);
				checkSave.setCheckdate(new Date());
				
				Checking checkingTimerReturn = checkingDao.getTimerByProfileId(new ProfileGoalDTO(profileVerify));

				Calendar cal = checkingTimerReturn.getTimer();
				Calendar finalCal = Calendar.getInstance();
				finalCal.add(Calendar.HOUR, -4);
				System.out.println("fecha del ultimo checking"+ cal);
				System.out.println("fecha del proximo checking"+ finalCal);
				if( finalCal.before(cal) ){			
					 return new CheckingDTO( new Message(false ,Constant.WAIT_FOUR_HOURS));
				}				
				Integer newID = checkingDao.persistCheck(checkSave);
				if(newID != null){
				  profileGoalVerify.setProgress( profileGoalVerify.getProgress()+ 1.0 );
				  ProfileGoalDTO profileGoalVerify1 = profilegoalDao.updateProgressOfOgal(profileGoalVerify);
				  if(profileGoalVerify1 != null){  
					  GoalDTO goalVerify = goalDao.findById( profileGoalVerify.getGoal().getGoal_id() );
					  Double primerobj = profileGoalVerify.getProgress();
					  Double segunbj = goalVerify.getAmount();
					  int retval = Double.compare(primerobj, segunbj);									  
					  if( retval == 0 ){						  
						  profileGoalVerify.setStatus(Constant.COMPLETE_PROGRESS);
						  profilegoalDao.updateStatus(profileGoalVerify);
						  return new CheckingDTO(newID, Constant.GOAL_COMPLETE, new Message(true ,Constant.YOUR_GOAL_COMPLETED));
					  }		 	  
				     }
				   }
				return new CheckingDTO(newID, Constant.GOAL_IN_PROGRESS, new Message(true,Constant.SUCCESS));
			}else{
				return new CheckingDTO(new Message(false,Constant.MISSING_VALUES_PARAMETERS));
			}	 
		} catch (Exception e1) {
			log.error(e1);
			return new CheckingDTO(new Message(false, Constant.DATABASE_ERROR));
		}
	}	

	@Override
	public List<Checking> getCheckingByProfile(Integer profileId) {
		try{
			return (List<Checking>) checkingDao.getlistById(new ProfileDTO(profileId));
		}catch(Exception e){
			return null;
		}
	}
}
