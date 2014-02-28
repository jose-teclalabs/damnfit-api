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
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.pojo.ProfileList;
import pe.com.damnfit.service.ProfileService;
import pe.com.damnfit.util.Constant;
import pe.com.damnfit.ws.request.ProfileRequest;
import pe.com.damnfit.ws.response.RestResponse;
import com.sun.jersey.api.core.InjectParam;

@Path("/profile")
public class ProfileWS {
	
	@InjectParam
	ProfileService profileService;
	

	@POST
	@Path("/changeitems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON }) 
	public RestResponse updateChangeItems(ProfileRequest request){
		try{
			ProfileDTO newProfileReturn = profileService.update3Items(request);
			if( newProfileReturn != null){
					return new RestResponse(newProfileReturn.getMessage().isSuccess(), newProfileReturn.getMessage().getDescription() );
			}else{
				return new RestResponse(false,Constant.SERVICE_ERROR );
			}	
		}catch(Exception e){
			return new RestResponse(false,Constant.SERVICE_ERROR );
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
			List<ProfileList> tools = profileService.listProfile(idProfile);
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

}
