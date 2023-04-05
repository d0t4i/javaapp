package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Khachhang database table.
 * 
 */
@Entity
@NamedQuery(name="Khachhang.findAll", query="SELECT k FROM Khachhang k")
public class Khachhang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="makhachhang")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  makhachhang;

	private String anhdaidien;

	private String diachi;

	private Integer gioitinh;

	private String hokhachhang;

	private String matkhau;

	private String ngaydangky;

	private String ngaysinh;

	private String sdt;

	private String tenkhachhang;

	private String tennguoidung;

	private Integer tuoi;

	private String web;

	//bi-directional many-to-one association to Donhang
	@OneToMany(mappedBy="khachhang", fetch = FetchType.EAGER)
	private List<Donhang> donhangs;

	//bi-directional many-to-one association to Giohang
	@OneToMany(mappedBy="khachhang")
	private List<Giohang> giohangs;

	//bi-directional many-to-one association to Quocgia
	@ManyToOne
	@JoinColumn(name="maquocgia")
	private Quocgia quocgia;

	//bi-directional many-to-one association to Quantri
	@OneToMany(mappedBy="khachhang")
	private List<Quantri> quantris;

	//bi-directional many-to-one association to Yeuthich
	@OneToMany(mappedBy="khachhang")
	private List<Yeuthich> yeuthiches;

	public Khachhang() {
	}

	public Long getMakhachhang() {
		return this.makhachhang;
	}

	public void setMakhachhang(Long makhachhang) {
		this.makhachhang = makhachhang;
	}

	public String getAnhdaidien() {
		return this.anhdaidien;
	}

	public void setAnhdaidien(String anhdaidien) {
		this.anhdaidien = anhdaidien;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public int getGioitinh() {
		return this.gioitinh;
	}

	public void setGioitinh(Integer gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getHokhachhang() {
		return this.hokhachhang;
	}

	public void setHokhachhang(String hokhachhang) {
		this.hokhachhang = hokhachhang;
	}

	public String getMatkhau() {
		return this.matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getNgaydangky() {
		return this.ngaydangky;
	}

	public void setNgaydangky(String ngaydangky) {
		this.ngaydangky = ngaydangky;
	}

	public String getNgaysinh() {
		return this.ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTenkhachhang() {
		return this.tenkhachhang;
	}

	public void setTenkhachhang(String tenkhachhang) {
		this.tenkhachhang = tenkhachhang;
	}

	public String getTennguoidung() {
		return this.tennguoidung;
	}

	public void setTennguoidung(String tennguoidung) {
		this.tennguoidung = tennguoidung;
	}

	public int getTuoi() {
		return this.tuoi;
	}

	public void setTuoi(Integer tuoi) {
		this.tuoi = tuoi;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<Donhang> getDonhangs() {
		return this.donhangs;
	}

	public void setDonhangs(List<Donhang> donhangs) {
		this.donhangs = donhangs;
	}

	public Donhang addDonhang(Donhang donhang) {
		getDonhangs().add(donhang);
		donhang.setKhachhang(this);

		return donhang;
	}

	public Donhang removeDonhang(Donhang donhang) {
		getDonhangs().remove(donhang);
		donhang.setKhachhang(null);

		return donhang;
	}

	public List<Giohang> getGiohangs() {
		return this.giohangs;
	}

	public void setGiohangs(List<Giohang> giohangs) {
		this.giohangs = giohangs;
	}

	public Giohang addGiohang(Giohang giohang) {
		getGiohangs().add(giohang);
		giohang.setKhachhang(this);

		return giohang;
	}

	public Giohang removeGiohang(Giohang giohang) {
		getGiohangs().remove(giohang);
		giohang.setKhachhang(null);

		return giohang;
	}

	public Quocgia getQuocgia() {
		return this.quocgia;
	}

	public void setQuocgia(Quocgia quocgia) {
		this.quocgia = quocgia;
	}

	public List<Quantri> getQuantris() {
		return this.quantris;
	}

	public void setQuantris(List<Quantri> quantris) {
		this.quantris = quantris;
	}

	public Quantri addQuantri(Quantri quantri) {
		getQuantris().add(quantri);
		quantri.setKhachhang(this);

		return quantri;
	}

	public Quantri removeQuantri(Quantri quantri) {
		getQuantris().remove(quantri);
		quantri.setKhachhang(null);

		return quantri;
	}

	public List<Yeuthich> getYeuthiches() {
		return this.yeuthiches;
	}

	public void setYeuthiches(List<Yeuthich> yeuthiches) {
		this.yeuthiches = yeuthiches;
	}

	public Yeuthich addYeuthich(Yeuthich yeuthich) {
		getYeuthiches().add(yeuthich);
		yeuthich.setKhachhang(this);

		return yeuthich;
	}

	public Yeuthich removeYeuthich(Yeuthich yeuthich) {
		getYeuthiches().remove(yeuthich);
		yeuthich.setKhachhang(null);

		return yeuthich;
	}

}