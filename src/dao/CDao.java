package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class CDao<T> {
	private Class<T> pclass;
	public CDao(Class<T> cls)
	{
		this.pclass = cls;
	}
	public void insert(T entity)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try{
			trans.begin();
			em.persist(entity);
			trans.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			trans.rollback();
		}finally
		{
			em.close();
		}
	}
	public void update(T entity)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try{
			trans.begin();
			em.merge(entity);
			trans.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			trans.rollback();
		}finally
		{
			em.close();
		}
	}
	public void delete(Object id)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try{
			trans.begin();
			T entity = em.find(pclass, id);
			em.remove(entity);
			trans.commit();
		}
		catch (Exception e){
			e.printStackTrace();
			trans.rollback();
		}finally
		{
			em.close();
		}
	}
	public T findId(Object id)
	{
		EntityManager em = JpaUtil.getEntityManager();
		try {
			
			T entity = em.find(pclass, id);
			return entity;
		} finally {
			em.close();
		}
	}
	public List<T> findall()
	{
		EntityManager em = JpaUtil.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(pclass));
			return em.createQuery(cq).getResultList();
		} finally {
			em.close();
		}
	}
	public List<T> findall(int f, int m)
	{
		EntityManager em = JpaUtil.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(pclass));
			Query q = em.createQuery(cq);
			q.setFirstResult(f);
			q.setMaxResults(m);
			
			return q.getResultList();
		} finally {
			em.close();
		}
	}
	
}
