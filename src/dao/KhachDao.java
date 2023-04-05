package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.Khachhang;
import model.Sach;
import model.Sachyeuthich;

public class KhachDao extends CDao<Khachhang> {

	public KhachDao() {
		super(Khachhang.class);
	}
	public Khachhang dangnhap(String ten, String mk)
	{
		EntityManager em = JpaUtil.getEntityManager();
		
		Khachhang kh =  (Khachhang) em.createNativeQuery("select * from Khachhang a where a.tennguoidung = '"+ten+"' and a.matkhau = '"+mk+"' ", Khachhang.class).getSingleResult();
		if(kh == null) return null;
		return kh;
	}
	
	public Khachhang dangnhapquantri(String ten, String mk)
	{
		EntityManager em = JpaUtil.getEntityManager();
		
		Khachhang kh =  (Khachhang) em.createNativeQuery("select a.* from Khachhang a, Quantri b where a.tennguoidung = '"+ten+"' and b.matkhau = '"+mk+"' ", Khachhang.class).getSingleResult();
		if(kh == null) return null;
		return kh;
	}
	
}
