package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.query.Query;

import model.Ctdh;
import model.Donhang;

public class CtdhDao extends CDao<Ctdh> {
	public CtdhDao() {
		super(Ctdh.class);
	}
	public void addct(String idsach, int don, int sl, double gia)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		Query que = (Query) em.createNativeQuery("insert into CTDH values('"+idsach+"','"+don+"', "+sl+", "+gia+")");
		que.executeUpdate();
		trans.commit();
		em.close();
	}
}
