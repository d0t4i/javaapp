package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Quocgia database table.
 * 
 */
@Entity
@NamedQuery(name="Quocgia.findAll", query="SELECT q FROM Quocgia q")
public class Quocgia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int maquocgia;

	private String tenquocgia;

	//bi-directional many-to-one association to Khachhang
	@OneToMany(mappedBy="quocgia")
	private List<Khachhang> khachhangs;

	public Quocgia() {
	}

	public int getMaquocgia() {
		return this.maquocgia;
	}

	public void setMaquocgia(int maquocgia) {
		this.maquocgia = maquocgia;
	}

	public String getTenquocgia() {
		return this.tenquocgia;
	}

	public void setTenquocgia(String tenquocgia) {
		this.tenquocgia = tenquocgia;
	}

	public List<Khachhang> getKhachhangs() {
		return this.khachhangs;
	}

	public void setKhachhangs(List<Khachhang> khachhangs) {
		this.khachhangs = khachhangs;
	}

	public Khachhang addKhachhang(Khachhang khachhang) {
		getKhachhangs().add(khachhang);
		khachhang.setQuocgia(this);

		return khachhang;
	}

	public Khachhang removeKhachhang(Khachhang khachhang) {
		getKhachhangs().remove(khachhang);
		khachhang.setQuocgia(null);

		return khachhang;
	}

}