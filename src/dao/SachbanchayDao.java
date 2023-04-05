package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Sach;
import model.Sachbanchay;
import model.Sachyeuthich;

public class SachbanchayDao {

	public List<Sach> get()
	{
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Sachbanchay> listchay =  em.createNativeQuery("select * from sachbanchay", Sachbanchay.class).getResultList();
		
		List<Sach> list = new ArrayList<Sach>();
		for(int i =0; i < listchay.size(); i++)
		{
			SachDao sd = new SachDao();
			Sach sach = sd.findId(listchay.get(i).getMasach());
			list.add(sach);
		}
		return list;
	}
}
