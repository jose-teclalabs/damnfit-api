package pe.com.damnfit.ws.response;

import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.util.Constant;

public class RestResponseID {
	private String newID;
	private String goalState;
	public RestResponseID(CheckingDTO checkingReturn) {

		this.newID = (checkingReturn.getCheck_id() == null) ? Constant.IS_EMPTY
				: String.valueOf(checkingReturn.getCheck_id());
		this.goalState = (checkingReturn.getGoalState() == null) ? Constant.IS_EMPTY
				: String.valueOf(checkingReturn.getGoalState());
		
	}

	public String getNewID() {
		return newID;
	}

	public void setNewID(String newID) {
		this.newID = newID;
	}

	public String getGoalState() {
		return goalState;
	}

	public void setGoalState(String goalState) {
		this.goalState = goalState;
	}
}
