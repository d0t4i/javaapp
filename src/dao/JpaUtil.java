package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUtil {
	public static EntityManager getEntityManager()
	{
		return Persistence.createEntityManagerFactory("doan").createEntityManager();
	}
}
