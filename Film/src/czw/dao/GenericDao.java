package czw.dao;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {
	public void persist(T entity);
		
	public void saveOrUpdate(T entity);
		
	public void flush();
	
	public void close();
}