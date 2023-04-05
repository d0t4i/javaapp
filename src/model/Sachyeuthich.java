package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sachyeuthich database table.
 * 
 */
@Entity
@Table(name="sachyeuthich")
@NamedQuery(name="Sachyeuthich.findAll", query="SELECT s FROM Sachyeuthich s")
public class Sachyeuthich implements Serializable {
	private static final long serialVersionUID = 1L;

	private String anh;

	private double gia;

	private int giamgia;

	@Id
	private Long masach;

	private int matacgia;

	private int matheloai;

	private String mota;

	private String pdf;

	private int sl;

	private String tensach;

	public Sachyeuthich() {
	}

	public String getAnh() {
		return this.anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public double getGia() {
		return this.gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getGiamgia() {
		return this.giamgia;
	}

	public void setGiamgia(int giamgia) {
		this.giamgia = giamgia;
	}

	public Long getMasach() {
		return this.masach;
	}

	public void setMasach(Long masach) {
		this.masach = masach;
	}

	public int getMatacgia() {
		return this.matacgia;
	}

	public void setMatacgia(int matacgia) {
		this.matacgia = matacgia;
	}

	public int getMatheloai() {
		return this.matheloai;
	}

	public void setMatheloai(int matheloai) {
		this.matheloai = matheloai;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getPdf() {
		return this.pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public int getSl() {
		return this.sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public String getTensach() {
		return this.tensach;
	}

	public void setTensach(String tensach) {
		this.tensach = tensach;
	}

}