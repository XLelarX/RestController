package repositories;

import data.Data;
import exceptions.DataBaseException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class DataRepository
{
	//	@PersistenceContext
	private EntityManager entityManager = Persistence.createEntityManagerFactory("repositories.DataRepository").createEntityManager();

	public Data doGet(Data data) throws DataBaseException
	{
		Data type = entityManager.find(data.getClass(), data.getId());
		if (type == null)
			throw new DataBaseException("Такого объекта нет");

		return type;
	}

	public void doInsert(Data data)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(data);
		entityManager.getTransaction().commit();
	}

	public List doGetAll(Data data)
	{
		Class dataClass = data.getClass();
		TypedQuery query = entityManager.createNamedQuery(dataClass.getSimpleName().toUpperCase() + ".getAll()", dataClass);
		return query.getResultList();
	}

	public void doRemove(Data data)
	{
		entityManager.getTransaction().begin();
		entityManager.remove(data);
		entityManager.getTransaction().commit();
	}

	public void doUpdate(Data data)
	{
		entityManager.getTransaction().begin();
		entityManager.merge(data);
		entityManager.getTransaction().commit();
	}
}
