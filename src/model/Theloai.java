package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Theloai database table.
 * 
 */
@Entity
@NamedQuery(name="Theloai.findAll", query="SELECT t FROM Theloai t")
public class Theloai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="matheloai")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long matheloai;

	private String mota;
	private String tentheloai;
	//bi-directional many-to-one association to Sach
	@OneToMany(mappedBy="theloai", fetch = FetchType.EAGER)
	private List<Sach> saches;
	public Theloai() {
	}

	public Long getMatheloai() {
		return this.matheloai;
	}

	public void setMatheloai(Long matheloai) {
		this.matheloai = matheloai;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getTentheloai() {
		return this.tentheloai;
	}

	public void setTentheloai(String tentheloai) {
		this.tentheloai = tentheloai;
	}

	public List<Sach> getSaches() {
		return this.saches;
	}

	public void setSaches(List<Sach> saches) {
		this.saches = saches;
	}

	public Sach addSach(Sach sach) {
		getSaches().add(sach);
		sach.setTheloai(this);

		return sach;
	}

	public Sach removeSach(Sach sach) {
		getSaches().remove(sach);
		sach.setTheloai(null);

		return sach;
	}

}