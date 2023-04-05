package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.hibernate.query.Query;

import model.Giohang;
import model.Sach;
import model.Sachbanchay;
import model.Yeuthich;
@Transactional
public class GiohangDao extends CDao<Giohang> {

	public GiohangDao() {
		super(Giohang.class);
	}
	@Transactional
	public void addgiohang(String idsach, String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		int don;
		try {
			don = (int)em.createNativeQuery("select a.sl from Giohang a where a.makhachhang = '"+idkhach
					+ "' and a.masach = '"+idsach+"' ").getSingleResult();
		} catch (Exception e) {
			don =0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(don > 0)
		{
			don++;
			Query que = (Query) em.createNativeQuery("update  Giohang   set sl  = "+don+" where makhachhang = '"+idkhach+"' and masach = '"+idsach+"' ");
			que.executeUpdate();
		}
		else
		{
			Query que = (Query) em.createNativeQuery("insert into Giohang (makhachhang, masach, sl) values("+idkhach+", "+idsach+", 1 );");
			que.executeUpdate();
		}
		trans.commit();
		em.close();
	}
	public void removegiohang(String idsach, String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		Query que = (Query) em.createNativeQuery("delete  Giohang  where makhachhang = '"+idkhach+"' and masach = '"+idsach+"' ");
		que.executeUpdate();
		trans.commit();
		em.close();
	}
	public void removegiohang( String idkhach)
	{
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		Query que = (Query) em.createNativeQuery("delete  Giohang  where makhachhang = '"+idkhach+"'");
		que.executeUpdate();
		trans.commit();
		em.close();
	}
	public List<Giohang> getKhachhang(String makhachhang)
	{
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Giohang> listchay =  em.createNativeQuery("select * from giohang where makhachhang = "+makhachhang, Giohang.class).getResultList();
		for(int i =0; i < listchay.size(); i++)
		{
			SachDao sd = new SachDao();
			Sach sach = sd.findId(listchay.get(i).getId().getMasach() );
			listchay.get(i).setSach(sach);
		}
		return listchay;
	}

}
