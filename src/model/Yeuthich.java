package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Yeuthich database table.
 * 
 */
@Entity
@NamedQuery(name="Yeuthich.findAll", query="SELECT y FROM Yeuthich y")
public class Yeuthich implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private YeuthichPK id;

	private Timestamp ngay;

	//bi-directional many-to-one association to Khachhang
	@ManyToOne
	@JoinColumn(name="makhachhang",insertable = false,updatable = false)
	private Khachhang khachhang;

	//bi-directional many-to-one association to Sach
	@ManyToOne
	@JoinColumn(name="masach",insertable = false,updatable = false)
	private Sach sach;

	public Yeuthich() {
	}

	public YeuthichPK getId() {
		return this.id;
	}

	public void setId(YeuthichPK id) {
		this.id = id;
	}

	public Timestamp getNgay() {
		return this.ngay;
	}

	public void setNgay(Timestamp ngay) {
		this.ngay = ngay;
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