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
import pe.com.damnfit.dao.ProfileDao;
import pe.com.damnfit.model.ProfileDTO;
import pe.com.damnfit.pojo.Profile;
import pe.com.damnfit.pojo.ProfileLevel;
import pe.com.damnfit.pojo.ProfileList;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<ProfileDTO> implements
		ProfileDao {

	private static final Log log = LogFactory.getLog(ProfileGoalDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public ProfileDTO sumaPoints(ProfileDTO profile , Integer sum) throws Exception {
		String sql = "UPDATE profile SET points =  points + :sumTotal  WHERE user_id = :idProdifle ";
		try {
			System.out.println("DAO SUMA POINTS   "+ profile.toString() + "   ++  " + sum.toString());
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("idProdifle", profile.getUser_id());
			query.setParameter("sumTotal", sum);
			query.executeUpdate();
			return findById(profile.getUser_id());
		} catch (Exception e) {
			log.error("update progress from profile goal failed, " + e);
			throw e;
		}
	}
	
	@Transactional
	@Override
	public ProfileDTO updateProfile(ProfileDTO profile) throws Exception {
		String sql = "UPDATE profile SET age =  :age, weight = :weight, gender = :gender WHERE user_id = :idProdifle ";
		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("idProdifle", profile.getUser_id());
			query.setParameter("age", profile.getAge());
			query.setParameter("weight", profile.getWeight());
			query.setParameter("gender", profile.getGender());
			query.executeUpdate();
			return findById(profile.getUser_id());
		} catch (Exception e) {
			log.error("update progress from profile goal failed, " + e);
			throw e;
		}
	}
	
	
	
	@Transactional
	@Override
	public ProfileDTO updateLevel(ProfileDTO profile , Integer level) throws Exception {
		String sql = "UPDATE profile SET level =  :levels  WHERE user_id = :idProdifle ";
		try {
			System.out.println("DAO SUMA POINTS   "+ profile.toString() + "   ++  " + level.toString());
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("idProdifle", profile.getUser_id());
			query.setParameter("levels", level);
			query.executeUpdate();
			return findById(profile.getUser_id());
		} catch (Exception e) {
			log.error("update progress from profile goal failed, " + e);
			throw e;
		}
	}

	@Transactional
	@Override
	public Profile getPointsFromProfile(ProfileDTO profile) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sum(points) as totalPoints ");
		sql.append("FROM  profile where user_id = :profileId  ");
		Profile idProfileReturn = null;
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setInteger("profileId", profile.getUser_id());
			query.addScalar("totalPoints", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(Profile.class));
			idProfileReturn = ((Profile) squery.getSingleResult());
		} catch (NoResultException e) {
			return null;
		} catch (RuntimeException re) {
			log.error("error at get the  total points ", re);
			throw re;
		}
		return idProfileReturn;
	}


	@Override
	public ProfileLevel getLevel(ProfileDTO profile) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT level as levels ");
		sql.append("FROM profile where user_id = :profileId  ");
		ProfileLevel idProfileReturn = null;
		try {
			Query squery = entityManager.createNativeQuery(sql.toString());
			HibernateQuery hibernateQuery = (HibernateQuery) squery;
			SQLQuery query = (SQLQuery) hibernateQuery.getHibernateQuery();
			query.setInteger("profileId", profile.getUser_id());
			query.addScalar("levels", StandardBasicTypes.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(ProfileLevel.class));
			idProfileReturn = ((ProfileLevel) squery.getSingleResult());
		} catch (NoResultException e) {
			return null;
		} catch (RuntimeException re) {
			log.error("error at get level from profile ", re);
			throw re;
		}
		return idProfileReturn;
	}

	@Override
	public List<ProfileList> getprofilelistById(ProfileDTO profile) {
		String sql =  "SELECT user_id as userId, points as points, age as age,weight as weight, gender as sexo FROM profile WHERE user_id = :profileId ";
		
		List<ProfileList> profileList = null;
		try {			
			Query query = entityManager.createNativeQuery(sql.toString()); 			
			HibernateQuery hibernateQuery = (HibernateQuery)query;
			SQLQuery sq = (SQLQuery)hibernateQuery.getHibernateQuery();
			sq.setInteger("profileId", profile.getUser_id());
			sq.addScalar("userId", StandardBasicTypes.INTEGER);
			sq.addScalar("points", StandardBasicTypes.INTEGER);
			sq.addScalar("age", StandardBasicTypes.INTEGER);
			sq.addScalar("weight", StandardBasicTypes.DOUBLE);		
			sq.addScalar("sexo", StandardBasicTypes.INTEGER);		
			sq.setResultTransformer(Transformers.aliasToBean(ProfileList.class)); 
			return profileList = (List<ProfileList>)query.getResultList();
		}catch( NoResultException  e ){
			return null;
		} catch (RuntimeException re) {
			log.error("get filter profiel list by id user failed", re);
			throw re;
		}
	}	
	

}
