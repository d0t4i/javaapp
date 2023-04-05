package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CTDH database table.
 * 
 */
@Entity
@Table(name="CTDH")
@NamedQuery(name="Ctdh.findAll", query="SELECT c FROM Ctdh c")
public class Ctdh implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CtdhPK id;

	private double gia;

	private int sl;

	//bi-directional many-to-one association to Donhang
	@ManyToOne
	@JoinColumn(name="madonhang",insertable = false,updatable = false)
	private Donhang donhang;

	//bi-directional many-to-one association to Sach
	@ManyToOne
	@JoinColumn(name="masach",insertable = false,updatable = false)
	private Sach sach;

	public Ctdh() {
	}

	public CtdhPK getId() {
		return this.id;
	}

	public void setId(CtdhPK id) {
		this.id = id;
	}

	public double getGia() {
		return this.gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getSl() {
		return this.sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public Donhang getDonhang() {
		return this.donhang;
	}

	public void setDonhang(Donhang donhang) {
		this.donhang = donhang;
	}

	public Sach getSach() {
		return this.sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

}