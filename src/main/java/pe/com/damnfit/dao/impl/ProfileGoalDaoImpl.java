package pe.com.damnfit.dao.impl;

import java.util.List;

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
import pe.com.damnfit.dao.ProfileGoalDao;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.ProfileGoal;

@Repository
public class ProfileGoalDaoImpl extends GenericDaoImpl<ProfileGoalDTO>
		implements ProfileGoalDao {

	private static final Log log = LogFactory.getLog(ProfileGoalDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public ProfileGoal getProfileGoalId(ProfileGoalDTO profilegoal)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.profile_goal_id as profilegoalId, p.progress as progress, p.status as status, p.bind_date as binDate, p.close_date as closeDate, p.goal_id as goalId ");
		sql.append("FROM profile_goal p inner join goal g on p.goal_id = g.goal_id WHERE p.profile_id = :profileId and p.status=0 and g.name= 'Gym' and p.active = 1 order by p.bind_date  LIMIT 1 ");
		ProfileGoal idProfileGoalReturn = null;
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setInteger("profileId", profilegoal.getProfile().getUser_id());
			query.addScalar("profilegoalId", StandardBasicTypes.INTEGER);
			query.addScalar("progress", StandardBasicTypes.DOUBLE);
			query.addScalar("status", StandardBasicTypes.INTEGER);
			query.addScalar("binDate", StandardBasicTypes.DATE);
			query.addScalar("closeDate", StandardBasicTypes.DATE);
			query.addScalar("goalId", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers
					.aliasToBean(ProfileGoal.class));
			idProfileGoalReturn = ((ProfileGoal) squery.getSingleResult());
		} catch (NoResultException e) {
			return null;
		} catch (RuntimeException re) {
			log.error("save device failed", re);
			throw re;
		}
		return idProfileGoalReturn;
	}

	@Transactional
	public ProfileGoalDTO updateProgressOfOgal(ProfileGoalDTO profileGoal)
			throws Exception {
		try {
			String sql = "UPDATE profile_goal SET progress = :progress FROM goal g WHERE profile_goal_id = :profilegoalId and g.goal_id = :goalId ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("profilegoalId", profileGoal.getProfileGoalId());
			query.setParameter("progress", profileGoal.getProgress());
			query.setParameter("goalId", profileGoal.getGoal().getGoal_id());
			query.executeUpdate();
			return findById(profileGoal.getProfileGoalId());
		} catch (Exception e) {
			log.error("update progress from profile goal failed, " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public Integer updateStatus(ProfileGoalDTO profileGoal) throws Exception {
		try {
			String sql = "UPDATE profile_goal set status = :status FROM goal g WHERE profile_goal_id = :profilegoalId and g.goal_id = :goalId ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("profilegoalId", profileGoal.getProfileGoalId());
			query.setParameter("status", profileGoal.getStatus());
			query.setParameter("goalId", profileGoal.getGoal().getGoal_id());
			query.executeUpdate();
		} catch (Exception e) {
			log.error("update status from profile goal failed, " + e);
			throw e;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProfileGoal> getProfileGoalByProfileId(
			ProfileDTO profile) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT g.name as gname , g.amount as gamount, m.name as mdescription ,(now() - Date(pg.close_date)) as finalDate,pg.progress as asprogress FROM profile_goal pg ");
		sql.append("INNER JOIN goal g ON pg.goal_id = g.goal_id ");
		sql.append("INNER JOIN metric m ON g.metric_id = m.metric_id  ");
		sql.append("INNER JOIN profile p ON pg.profile_id = p.user_id WHERE pg.profile_id = :profileId and pg.status=0 and pg.active = 1 order by pg.bind_date desc");

		List<ProfileGoal> profileGoalList = null;
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setLong("profileId", profile.getUser_id());
			query.addScalar("gname", StandardBasicTypes.STRING);
			query.addScalar("gamount", StandardBasicTypes.STRING);
			query.addScalar("mdescription", StandardBasicTypes.STRING);
			query.addScalar("finalDate", StandardBasicTypes.STRING);
			query.addScalar("asprogress", StandardBasicTypes.STRING);
			query.setResultTransformer(Transformers.aliasToBean(ProfileGoal.class));
			profileGoalList = (List<ProfileGoal>) squery.getResultList();

		} catch (NoResultException e) {
			return null;
		} catch (RuntimeException re) {
			log.error("get filter list by goal and  user failed", re);
			throw re;
		}
		return profileGoalList;
	}
	
	@Transactional
	@Override
	public void changeStatusOfRange(ProfileDTO profile) throws Exception {

		String sql = "UPDATE profile_goal set active = :active WHERE profile_id = :profileId and now() > close_date";
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setLong("profileId", profile.getUser_id());
			query.setInteger("active", profile.getProfileGoals().get(0).getActive() );
            query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("Error at modify the active in the profile_goal table", re);
			throw re;
		}
	}
	@Transactional
	@Override
	public ProfileGoal sumPoints(ProfileDTO profile) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(g.points) as total ");
		sql.append("FROM profile_goal pg , goal g where pg.profile_id = :profileId and pg.status = 1 and pg.goal_id = g.goal_id and g.name = 'Gym' and pg.active = 1 ");
		ProfileGoal idProfileGoalReturn = null;
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setInteger("profileId", profile.getUser_id());
			query.addScalar("total", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(ProfileGoal.class));
			idProfileGoalReturn = ((ProfileGoal) squery.getSingleResult());
		} catch (NoResultException e) {
			return null;
		} catch (RuntimeException re) {
			log.error("error at get the  total points ", re);
			throw re;
		}
		return idProfileGoalReturn;
	}
	
	@Transactional
	@Override
	public ProfileGoalDTO updateActive(ProfileDTO profile) throws Exception {
		String sql = "UPDATE profile_goal SET active = 0 WHERE profile_id = :idProdifle and status = 1 ";
		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("idProdifle", profile.getUser_id());
			query.executeUpdate();
			return findById(profile.getUser_id());
		} catch (Exception e) {
			log.error("update level failed " + e);
			throw e;
		}
	}
	
	
}
