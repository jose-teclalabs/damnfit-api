package pe.com.damnfit.ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.core.InjectParam;
import pe.com.damnfit.pojo.Checking;
import pe.com.damnfit.service.CheckingService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.ws.response.RestResponse;

@Path("/check")
public class CheckingWS {
	
	@InjectParam
	CheckingService checkingService;

	
	@GET
	@Path("/getallcheck/{profileId}")
	@Produces({ MediaType.APPLICATION_JSON }) 
	public RestResponse getStoreItemByPaUser( @PathParam("profileId") String profileId_P  ){
		
		Integer profileId = null;
		try{ 
			profileId = Integer.parseInt(profileId_P);
		}catch(Exception e){
			return new RestResponse(false, Constant.PARAMETER_IS_NOT_SPECIFIED);
		}
		
		try { 
			List<Checking> checkingist = checkingService.getCheckingByProfile(profileId);
			if(checkingist.size() > 0){
				return new RestResponse(true, checkingist, Constant.SATISFACTORY_PROCESS);
			}
			else{
				return new RestResponse(false, Constant.GOAL_NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new RestResponse(false, Constant.SERVICE_ERROR);
		}
	}	
}
