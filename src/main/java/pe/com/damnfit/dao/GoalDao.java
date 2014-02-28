package pe.com.damnfit.dao;

import pe.com.damnfit.base.GenericDao;
import pe.com.damnfit.model.GoalDTO;
import pe.com.damnfit.pojo.Goal;

public interface GoalDao extends GenericDao<GoalDTO> {

	public Goal getTimerByIdGoal(GoalDTO goal) throws Exception;
}
