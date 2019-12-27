package repositories;

import data.Data;
import data.permissions.Permissions;

import javax.persistence.*;
import java.util.List;

public abstract class DataRepository
{
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("repositories.DataRepository");
	@PersistenceContext
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public abstract List<Permissions> doGet(String name);

	public void doInsert(Data data)
	{
		Permissions permissions = (Permissions) data;

		entityManager.getTransaction().begin();
		entityManager.persist(permissions);
		entityManager.getTransaction().commit();
	}

	public List<Permissions> doGetAll()
	{
		TypedQuery<Permissions> query = entityManager.createNamedQuery("getAll()", Permissions.class);
		return query.getResultList();
	}

	public void doDelete(Data data)
	{
		Permissions permissions = (Permissions) data;

		entityManager.getTransaction().begin();
		entityManager.remove(permissions);
		entityManager.getTransaction().commit();
	}
}
