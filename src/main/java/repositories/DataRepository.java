package repositories;

import data.Data;
import data.permissions.Permissions;
import exceptions.FindException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class DataRepository<T extends Data>
{
	private Class dataClass;
	private T type;

	public DataRepository(Class dataClass)
	{
		this.dataClass = dataClass;
	}

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("repositories.DataRepository");
	@PersistenceContext
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public T doGet(Long id) throws FindException
	{
		type = (T) entityManager.find(dataClass, id);
		if (type == null)
			throw new FindException("Такого объекта нет");

		return type;
	}

	public void doInsert(T data)
	{
//		T permissions = (T) data;

		entityManager.getTransaction().begin();
		entityManager.persist(data);
		entityManager.getTransaction().commit();
	}

	public List<T> doGetAll()
	{
		TypedQuery<T> query = entityManager.createNamedQuery(dataClass.getSimpleName().toUpperCase() + ".getAll()", dataClass);
		return query.getResultList();
	}

	public void doDelete(T data)
	{
		entityManager.getTransaction().begin();
		entityManager.remove(data);
		entityManager.getTransaction().commit();
	}

	public void doUpdate(T data)
	{
		entityManager.getTransaction().begin();
		entityManager.merge(data);
		entityManager.getTransaction().commit();
	}
}
