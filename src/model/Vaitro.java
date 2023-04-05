package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vaitro database table.
 * 
 */
@Entity
@Table(name="vaitro")
@NamedQuery(name="Vaitro.findAll", query="SELECT v FROM Vaitro v")
public class Vaitro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int mavaitro;

	private String tenvaitro;

	//bi-directional many-to-one association to Quantri
	@OneToMany(mappedBy="vaitro")
	private List<Quantri> quantris;

	public Vaitro() {
	}

	public int getMavaitro() {
		return this.mavaitro;
	}

	public void setMavaitro(int mavaitro) {
		this.mavaitro = mavaitro;
	}

	public String getTenvaitro() {
		return this.tenvaitro;
	}

	public void setTenvaitro(String tenvaitro) {
		this.tenvaitro = tenvaitro;
	}

	public List<Quantri> getQuantris() {
		return this.quantris;
	}

	public void setQuantris(List<Quantri> quantris) {
		this.quantris = quantris;
	}

	public Quantri addQuantri(Quantri quantri) {
		getQuantris().add(quantri);
		quantri.setVaitro(this);

		return quantri;
	}

	public Quantri removeQuantri(Quantri quantri) {
		getQuantris().remove(quantri);
		quantri.setVaitro(null);

		return quantri;
	}

}