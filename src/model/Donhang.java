package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Donhang database table.
 * 
 */
@Entity
@NamedQuery(name="Donhang.findAll", query="SELECT d FROM Donhang d")
public class Donhang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="madonhang")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long madonhang;

	private String ngaydat;

	private double tongtien;

	//bi-directional many-to-one association to Ctdh
	@OneToMany(mappedBy="donhang", fetch = FetchType.EAGER)
	private List<Ctdh> ctdhs;

	//bi-directional many-to-one association to Khachhang
	@ManyToOne
	@JoinColumn(name="makhachhang")
	private Khachhang khachhang;

	public Donhang() {
	}

	public Long getMadonhang() {
		return this.madonhang;
	}

	public void setMadonhang(Long madonhang) {
		this.madonhang = madonhang;
	}

	public String getNgaydat() {
		return this.ngaydat;
	}

	public void setNgaydat(String ngaydat) {
		this.ngaydat = ngaydat;
	}

	public double getTongtien() {
		return this.tongtien;
	}

	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}

	public List<Ctdh> getCtdhs() {
		return this.ctdhs;
	}

	public void setCtdhs(List<Ctdh> ctdhs) {
		this.ctdhs = ctdhs;
	}

	public Ctdh addCtdh(Ctdh ctdh) {
		getCtdhs().add(ctdh);
		ctdh.setDonhang(this);

		return ctdh;
	}

	public Ctdh removeCtdh(Ctdh ctdh) {
		getCtdhs().remove(ctdh);
		ctdh.setDonhang(null);

		return ctdh;
	}

	public Khachhang getKhachhang() {
		return this.khachhang;
	}

	public void setKhachhang(Khachhang khachhang) {
		this.khachhang = khachhang;
	}

}