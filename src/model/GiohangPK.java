package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Giohang database table.
 * 
 */
@Embeddable
public class GiohangPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int makhachhang;

	@Column(insertable=false, updatable=false)
	private Long masach;

	public GiohangPK() {
	}
	public int getMakhachhang() {
		return this.makhachhang;
	}
	public void setMakhachhang(int makhachhang) {
		this.makhachhang = makhachhang;
	}
	public Long getMasach() {
		return this.masach;
	}
	public void setMasach(Long masach) {
		this.masach = masach;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GiohangPK)) {
			return false;
		}
		GiohangPK castOther = (GiohangPK)other;
		return 
			(this.makhachhang == castOther.makhachhang)
			&& (this.masach == castOther.masach);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.makhachhang;
		hash = (int) (hash * prime + this.masach);
		
		return hash;
	}
}