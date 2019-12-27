package repositories;

import data.permissions.Permissions;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class PermissionsRepository extends DataRepository
{
	@Override
	public List<Permissions> doGet(String name)
	{
		TypedQuery<Permissions> query = entityManager.createNamedQuery("getByName()", Permissions.class).setParameter("name", name);
		return query.getResultList();
	}
}
