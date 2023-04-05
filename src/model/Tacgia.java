package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Tacgia database table.
 * 
 */
@Entity
@NamedQuery(name="Tacgia.findAll", query="SELECT t FROM Tacgia t")
public class Tacgia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="matacgia")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long matacgia;

	private String anh;

	private String mota;

	private String tentacgia;

	//bi-directional many-to-one association to Sach
	@OneToMany(mappedBy="tacgia", fetch = FetchType.EAGER)
	private List<Sach> saches;

	public Tacgia() {
	}

	public Long getMatacgia() {
		return this.matacgia;
	}

	public void setMatacgia(Long matacgia) {
		this.matacgia = matacgia;
	}

	public String getAnh() {
		return this.anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getTentacgia() {
		return this.tentacgia;
	}

	public void setTentacgia(String tentacgia) {
		this.tentacgia = tentacgia;
	}

	public List<Sach> getSaches() {
		return this.saches;
	}

	public void setSaches(List<Sach> saches) {
		this.saches = saches;
	}

	public Sach addSach(Sach sach) {
		getSaches().add(sach);
		sach.setTacgia(this);

		return sach;
	}

	public Sach removeSach(Sach sach) {
		getSaches().remove(sach);
		sach.setTacgia(null);

		return sach;
	}

}