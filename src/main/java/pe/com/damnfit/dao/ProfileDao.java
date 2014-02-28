package pe.com.damnfit.dao;

import java.util.List;
import pe.com.damnfit.base.GenericDao;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.pojo.Profile;
import pe.com.damnfit.pojo.ProfileLevel;
import pe.com.damnfit.pojo.ProfileList;

public interface ProfileDao extends GenericDao<ProfileDTO> {
	
	public ProfileDTO sumaPoints(ProfileDTO profile , Integer sum) throws Exception;
	public Profile getPointsFromProfile(ProfileDTO profile) throws Exception;
	public ProfileLevel getLevel(ProfileDTO profile) throws Exception;
	public ProfileDTO updateLevel(ProfileDTO profile , Integer level) throws Exception;
	public ProfileDTO updateProfile(ProfileDTO profile) throws Exception ;
	public List<ProfileList> getprofilelistById(ProfileDTO profile); 

	

}
