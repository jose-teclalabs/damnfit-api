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
import pe.com.damnfit.dao.CheckingDao;
import pe.com.damnfit.model.CheckingDTO;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.model.ProfileGoalDTO;
import pe.com.damnfit.pojo.Checking;

@Repository
public class CheckingDaoImpl extends GenericDaoImpl<CheckingDTO> implements CheckingDao {
 
	private static final Log log = LogFactory.getLog(CheckingDaoImpl.class);

	@PersistenceContext
    private EntityManager entityManager;
		
	@Transactional
	@Override
	public Integer persistCheck(CheckingDTO c) {
		Integer idAssinged = null;
		try {
			entityManager.persist(c);
			entityManager.flush();
			idAssinged = c.getCheck_id();
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
		return idAssinged;
	}
	
	@Override
	public List<Checking> getlistById(ProfileDTO profile) {
		StringBuilder sql = new StringBuilder();
	 	sql.append("SELECT c.address as address, Date(c.checkdate) as fechaCheck, c.namegym as namegym FROM checking c ");
		sql.append("INNER JOIN profile_goal pg  ON c.profile_goal_id = pg.profile_goal_id ");
		sql.append("INNER JOIN goal g ON g.goal_id = pg.goal_id where pg.profile_id = :profileId and c.status=1 and pg.active = 1 order by c.checkdate asc ");
		
		List<Checking> profileGoalList = null;
		try {			
			Query query = entityManager.createNativeQuery(sql.toString()); 			
			HibernateQuery hibernateQuery = (HibernateQuery)query;
			SQLQuery sq = (SQLQuery)hibernateQuery.getHibernateQuery();
			sq.setInteger("profileId", profile.getUser_id());
			sq.addScalar("address", StandardBasicTypes.STRING);
			sq.addScalar("fechaCheck", StandardBasicTypes.STRING);
			sq.addScalar("namegym", StandardBasicTypes.STRING);			
			sq.setResultTransformer(Transformers.aliasToBean(Checking.class)); 
			return profileGoalList = (List<Checking>)query.getResultList();
		}catch( NoResultException  e ){
			return null;
		} catch (RuntimeException re) {
			log.error("get filter list by id user failed", re);
			throw re;
		}
	}
	
	@Transactional
	@Override
	public Checking getTimerByProfileId(ProfileGoalDTO profilegoal) throws Exception {
		
		String sql = "SELECT c.checkdate as timer FROM checking c, profile_goal pg WHERE pg.profile_id = :profileId and active = 1 order by c.checkdate desc LIMIT 1 ";
		Checking checkingReturn = null;
		try {				 
			Query squery = entityManager.createNativeQuery(sql.toString()); 
			HibernateQuery hibernateQuery = (HibernateQuery)squery;
			SQLQuery query = (SQLQuery)hibernateQuery.getHibernateQuery();
			query.setInteger("profileId", profilegoal.getProfile().getUser_id());
			query.addScalar("timer", StandardBasicTypes.CALENDAR);
			query.setResultTransformer(Transformers.aliasToBean(Checking.class));
			checkingReturn = ((Checking)squery.getSingleResult());
		} catch( NoResultException  e ){
			return null;
		} catch (RuntimeException re) {
			log.error("save device failed", re);
			throw re;
		}
		return checkingReturn;	
	}
}