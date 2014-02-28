package pe.com.damnfit.service;

import java.util.List;

import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.pojo.ProfileList;
import pe.com.damnfit.ws.request.ProfileRequest;

public interface ProfileService {
	
	public ProfileDTO update3Items(ProfileRequest request);
	public List<ProfileList> listProfile(Integer id);

}
