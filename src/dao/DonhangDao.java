package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.query.Query;

import model.Donhang;

public class DonhangDao extends CDao<Donhang> {

	public DonhangDao() {
		super(Donhang.class);
	}

	public int getmadon(String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		int don =  (int)em.createNativeQuery("select top 1 a.madonhang from Donhang a where a.makhachhang = '"+idkhach+"' order by a.madonhang desc").getSingleResult();
		
		em.close();
		return don;
	}
}
