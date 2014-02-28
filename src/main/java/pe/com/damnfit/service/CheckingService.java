package pe.com.damnfit.service;

import java.util.List;

import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.pojo.Checking;
import pe.com.damnfit.ws.request.CheckingRequest;

public interface CheckingService {
	
	public CheckingDTO addChecking(CheckingRequest request);
	public List<Checking> getCheckingByProfile(Integer profileId);
}
