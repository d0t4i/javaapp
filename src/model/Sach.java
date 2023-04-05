package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Sach database table.
 * 
 */
@Entity
@NamedQuery(name="Sach.findAll", query="SELECT s FROM Sach s")
public class Sach implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="masach")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long masach;

	private String anh;

	private double gia;

	private int giamgia;

	private String mota;

	private String pdf;

	private int sl;

	private String tensach;

	//bi-directional many-to-one association to Ctdh
	@OneToMany(mappedBy="sach")
	private List<Ctdh> ctdhs;

	//bi-directional many-to-one association to Giohang
	@OneToMany(mappedBy="sach")
	private List<Giohang> giohangs;

	//bi-directional many-to-one association to Tacgia
	@ManyToOne
	@JoinColumn(name="matacgia")
	private Tacgia tacgia;

	//bi-directional many-to-one association to Theloai
	@ManyToOne
	@JoinColumn(name="matheloai")
	private Theloai theloai;

	//bi-directional many-to-one association to Yeuthich
	@OneToMany(mappedBy="sach")
	private List<Yeuthich> yeuthiches;

	public Sach() {
	}

	public Long getMasach() {
		return this.masach;
	}

	public void setMasach(Long masach) {
		this.masach = masach;
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

	public List<Ctdh> getCtdhs() {
		return this.ctdhs;
	}

	public void setCtdhs(List<Ctdh> ctdhs) {
		this.ctdhs = ctdhs;
	}

	public Ctdh addCtdh(Ctdh ctdh) {
		getCtdhs().add(ctdh);
		ctdh.setSach(this);

		return ctdh;
	}

	public Ctdh removeCtdh(Ctdh ctdh) {
		getCtdhs().remove(ctdh);
		ctdh.setSach(null);

		return ctdh;
	}

	public List<Giohang> getGiohangs() {
		return this.giohangs;
	}

	public void setGiohangs(List<Giohang> giohangs) {
		this.giohangs = giohangs;
	}

	public Giohang addGiohang(Giohang giohang) {
		getGiohangs().add(giohang);
		giohang.setSach(this);

		return giohang;
	}

	public Giohang removeGiohang(Giohang giohang) {
		getGiohangs().remove(giohang);
		giohang.setSach(null);

		return giohang;
	}

	public Tacgia getTacgia() {
		return this.tacgia;
	}

	public void setTacgia(Tacgia tacgia) {
		this.tacgia = tacgia;
	}

	public Theloai getTheloai() {
		return this.theloai;
	}

	public void setTheloai(Theloai theloai) {
		this.theloai = theloai;
	}

	public List<Yeuthich> getYeuthiches() {
		return this.yeuthiches;
	}

	public void setYeuthiches(List<Yeuthich> yeuthiches) {
		this.yeuthiches = yeuthiches;
	}

	public Yeuthich addYeuthich(Yeuthich yeuthich) {
		getYeuthiches().add(yeuthich);
		yeuthich.setSach(this);

		return yeuthich;
	}

	public Yeuthich removeYeuthich(Yeuthich yeuthich) {
		getYeuthiches().remove(yeuthich);
		yeuthich.setSach(null);

		return yeuthich;
	}

}