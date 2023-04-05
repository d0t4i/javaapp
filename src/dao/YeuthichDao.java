package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.query.Query;

import model.Sach;
import model.Yeuthich;

public class YeuthichDao extends CDao<Yeuthich>{

	public YeuthichDao() {
		super(Yeuthich.class);
	}
	public void addyeuthich(String idsach, String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		int don =  (int)em.createNativeQuery("select count(*) from yeuthich a where a.makhachhang = '"+idkhach
				+ "' and a.masach = '"+idsach+"' ").getSingleResult();
		System.out.println(don);
		if(don > 0)
		{
			
		}
		else
		{
			Query que = (Query) em.createNativeQuery("insert into yeuthich  values("+idkhach+", "+idsach+",null);");
			que.executeUpdate();
		}
		trans.commit();
		em.close();
	}
	public void removeyeuthich(String idsach, String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		Query que = (Query) em.createNativeQuery("delete  yeuthich  where makhachhang = '"+idkhach+"' and masach = '"+idsach+"' ");
		que.executeUpdate();
		trans.commit();
		em.close();
	}
	public void removeyeuthich( String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		Query que = (Query) em.createNativeQuery("delete  yeuthich  where makhachhang = '"+idkhach+"'");
		que.executeUpdate();
		trans.commit();
		em.close();
	}
	public List<Sach> getKhachhang(String makhachhang)
	{
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Yeuthich> listchay =  em.createNativeQuery("select * from yeuthich where makhachhang = "+makhachhang, Yeuthich.class).getResultList();
		
		List<Sach> list = new ArrayList<Sach>();
		for(int i =0; i < listchay.size(); i++)
		{
			SachDao sd = new SachDao();
			Sach sach = sd.findId(listchay.get(i).getId().getMasach());
			list.add(sach);
		}
		return list;
	}
	
}
