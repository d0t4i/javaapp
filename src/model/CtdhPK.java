package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CTDH database table.
 * 
 */
@Embeddable
public class CtdhPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Long masach;

	@Column(insertable=false, updatable=false)
	private int madonhang;

	public CtdhPK() {
	}
	public Long getMasach() {
		return this.masach;
	}
	public void setMasach(Long masach) {
		this.masach = masach;
	}
	public int getMadonhang() {
		return this.madonhang;
	}
	public void setMadonhang(int madonhang) {
		this.madonhang = madonhang;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CtdhPK)) {
			return false;
		}
		CtdhPK castOther = (CtdhPK)other;
		return 
			(this.masach == castOther.masach)
			&& (this.madonhang == castOther.madonhang);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = (int) (hash * prime + this.masach);
		hash = hash * prime + this.madonhang;
		
		return hash;
	}
}