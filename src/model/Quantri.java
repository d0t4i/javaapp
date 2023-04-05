package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Quantri database table.
 * 
 */
@Entity
@NamedQuery(name="Quantri.findAll", query="SELECT q FROM Quantri q")
public class Quantri implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int maquantri;

	private String matkhau;

	//bi-directional many-to-one association to Khachhang
	@ManyToOne
	@JoinColumn(name="makhachhang")
	private Khachhang khachhang;

	//bi-directional many-to-one association to Vaitro
	@ManyToOne
	@JoinColumn(name="mavaitro")
	private Vaitro vaitro;

	public Quantri() {
	}

	public int getMaquantri() {
		return this.maquantri;
	}

	public void setMaquantri(int maquantri) {
		this.maquantri = maquantri;
	}

	public String getMatkhau() {
		return this.matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public Khachhang getKhachhang() {
		return this.khachhang;
	}

	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}

	public Vaitro getVaitro() {
		return this.vaitro;
	}

	public void setVaitro(Vaitro vaitro) {
		this.vaitro = vaitro;
	}

}