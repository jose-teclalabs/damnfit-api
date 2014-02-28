package pe.com.damnfit.ws.response;


import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.util.Constant;

public class RestResponseIDProfileGoal {
	private String newID;

	public RestResponseIDProfileGoal(ProfileGoalDTO obj) {
		
		this.newID = (obj.getProfileGoalId()== null)?Constant.IS_EMPTY:String.valueOf(obj.getProfileGoalId());
	}

	public String getNewID() {
		return newID;
	}

	public void setNewID(String newID) {
		this.newID = newID;
	}


}
