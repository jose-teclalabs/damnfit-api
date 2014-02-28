package pe.com.damnfit.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.damnfit.dao.ProfileDao;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.pojo.ProfileList;
import pe.com.damnfit.service.ProfileService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.util.Message;
import pe.com.damnfit.ws.request.ProfileRequest;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	ProfileDao profileDao;
	
	private static final Log log = LogFactory.getLog(ProfileServiceImpl.class);
	
	@Override
	public ProfileDTO update3Items(ProfileRequest request) {
		
		try{			
			Integer prodileVerify = null;
			Integer ageVerify = null;
			Double weightVerify = null;
			Integer genderVerify= null;
			try {
				prodileVerify = Integer.parseInt(request.getProfileId());
				ageVerify = Integer.parseInt(request.getAge());
				weightVerify = Double.parseDouble(request.getWeight());
				genderVerify = Integer.parseInt(request.getSexo());		
				
			} catch (Exception e) {
				log.error(e);
				return new ProfileDTO(new Message(false,Constant.PARAMETER_IS_NOT_PROPERLY_FORMATTED));
			}			
			if (!request.getProfileId().equals(Constant.IS_EMPTY)) {
				
				ProfileDTO profileReturn = profileDao.findById(prodileVerify);
				if (profileReturn == null) {
					return new ProfileDTO(new Message(false,
							Constant.MISSING_VALUES_PARAMETERS));
				}	
			
				ProfileDTO profileSet = new ProfileDTO();
				profileSet.setUser_id(prodileVerify);
				profileSet.setAge(ageVerify);
				profileSet.setWeight(weightVerify);
				profileSet.setGender(genderVerify);				
				profileDao.updateProfile(profileSet);
				return new ProfileDTO(new Message(true, Constant.SATISFACTORY_PROCESS));
			}else{
				return new ProfileDTO(new Message(false, Constant.PARAMETER_IS_NOT_SPECIFIED));
			}			
		}catch(Exception e){
			return new ProfileDTO(new Message(false, Constant.DATABASE_ERROR));
			
		}
	}

	@Override
	public List<ProfileList> listProfile(Integer id) {
	
		try{
			return (List<ProfileList>) profileDao.getprofilelistById(new ProfileDTO(id));
		}catch(Exception e){
			return null;
		}
		
	}
	

}
