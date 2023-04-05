package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Giohang database table.
 * 
 */
@Entity
@NamedQuery(name="Giohang.findAll", query="SELECT g FROM Giohang g")
public class Giohang implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GiohangPK id;

	private int sl;

	//bi-directional many-to-one association to Khachhang
	@ManyToOne
	@JoinColumn(name="makhachhang",insertable = false,updatable = false)
	private Khachhang khachhang;

	//bi-directional many-to-one association to Sach
	@ManyToOne
	@JoinColumn(name="masach",insertable = false,updatable = false)
	private Sach sach;

	public Giohang() {
	}

	public GiohangPK getId() {
		return this.id;
	}

	public void setId(GiohangPK id) {
		this.id = id;
	}

	public int getSl() {
		return this.sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public Khachhang getKhachhang() {
		return this.khachhang;
	}

	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}

	public Sach getSach() {
		return this.sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

}