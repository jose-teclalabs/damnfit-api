package pe.com.damnfit.ws;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.ProfileGoal;
import pe.com.damnfit.service.CheckingService;
import pe.com.damnfit.service.ProfileGoalService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.ws.request.CheckingRequest;
import pe.com.damnfit.ws.request.ProfileGoalRequest;
import pe.com.damnfit.ws.response.RestResponse;
import pe.com.damnfit.ws.response.RestResponseID;
import pe.com.damnfit.ws.response.RestResponseIDProfileGoal;

import com.sun.jersey.api.core.InjectParam;

@Path("/profilegoal")
public class ProfileGoalWS {

	@InjectParam
	CheckingService checkingService;

	@InjectParam
	ProfileGoalService profileGoalService;

	@POST
	@Path("/check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public RestResponse registrarCheck(CheckingRequest request) {
		
		try {
			CheckingDTO newCheckReturn = checkingService.addChecking(request);
			if (newCheckReturn != null) {

				RestResponseID returnNewID = new RestResponseID(newCheckReturn);
				if (newCheckReturn.getMessage().isSuccess()) {
					return new RestResponse(newCheckReturn.getMessage().isSuccess(),Collections.singletonList(returnNewID),newCheckReturn.getMessage().getDescription());
				} else {
					return new RestResponse(newCheckReturn.getMessage()
							.isSuccess(), newCheckReturn.getMessage()
							.getDescription());
				}
			} else {
				return new RestResponse(false, Constant.SERVICE_ERROR);
			}
		} catch (Exception e) {
			return new RestResponse(false, Constant.SERVICE_ERROR);
		}
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public RestResponse registrarProfileGoal(ProfileGoalRequest request) {

		try {
			ProfileGoalDTO profileGoalReturn = profileGoalService.saveProfileGoal(request);
			if (profileGoalReturn != null) {
				return new RestResponse(profileGoalReturn.getMessage().isSuccess(), profileGoalReturn.getMessage().getDescription());
			} else {
				return new RestResponse(false, Constant.SERVICE_ERROR);
			}
		} catch (Exception e) {
			return new RestResponse(false, Constant.SERVICE_ERROR);
		}

	}
	
	@GET
	@Path("/getall/{profileId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public RestResponse getTotalListOfProfileGoal(@PathParam("profileId") String profileId_p ){
		Integer idProfile = null;
		try{ 
			idProfile = Integer.parseInt(profileId_p);
		}catch(Exception e){
			return new RestResponse(false, Constant.PARAMETER_IS_NOT_SPECIFIED);
		}	
		
		try{
			List<ProfileGoal> tools = profileGoalService.listProfileGoal(idProfile);
			if(tools.size() > 0){
				return new RestResponse(true,Collections.singletonList(tools),Constant.SATISFACTORY_PROCESS);
				
			}else{
				return new RestResponse(false,Constant.GOALS_NOT_FOUND );
			}			
		}catch(Exception e){
			System.out.println(e);
			return new RestResponse(false,Constant.SERVICE_ERROR );
		}
	}

	@POST
	@Path("/sendPoints")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON }) 
	public RestResponse sendPoints(ProfileGoalRequest request){
		
		
		try {
			ProfileGoalDTO newProfileGoalReturn = profileGoalService.getTotalPoint(request);
			if (newProfileGoalReturn != null) {

				RestResponseIDProfileGoal returnNewID = new RestResponseIDProfileGoal(newProfileGoalReturn);
				if (newProfileGoalReturn.getMessage().isSuccess()) {
					return new RestResponse(newProfileGoalReturn.getMessage().isSuccess(),Collections.singletonList(returnNewID),newProfileGoalReturn.getMessage().getDescription());
				} else {
					return new RestResponse(newProfileGoalReturn.getMessage()
							.isSuccess(), newProfileGoalReturn.getMessage()
							.getDescription());
				}
			} else {
				return new RestResponse(false, Constant.SERVICE_ERROR);
			}
		} catch (Exception e) {
			return new RestResponse(false, Constant.SERVICE_ERROR);
		}
	}
}
