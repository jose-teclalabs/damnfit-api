package pe.com.damnfit.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.ejb.HibernateQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.damnfit.base.GenericDaoImpl;
import pe.com.damnfit.dao.GoalDao;
import pe.com.damnfit.model.GoalDTO;
import pe.com.damnfit.pojo.Goal;

@Repository
public class GoalDaoImpl extends GenericDaoImpl<GoalDTO> implements GoalDao {

	private static final Log log = LogFactory.getLog(ProfileGoalDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Goal getTimerByIdGoal(GoalDTO goal) throws Exception {
		
		String sql = "SELECT time as timer FROM goal WHERE goal_id = :goalId and active = 1 LIMIT 1 ";
		Goal goalReturn = null;
		try {				 
			Query squery = entityManager.createNativeQuery(sql.toString()); 
			HibernateQuery hibernateQuery = (HibernateQuery)squery;
			SQLQuery query = (SQLQuery)hibernateQuery.getHibernateQuery();
			query.setInteger("goalId", goal.getGoal_id());
			query.addScalar("timer", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(Goal.class));
			goalReturn = ((Goal)squery.getSingleResult());
		} catch( NoResultException  e ){
			return null;
		} catch (RuntimeException re) {
			log.error("save device failed", re);
			throw re;
		}
		return goalReturn;	
	}
}