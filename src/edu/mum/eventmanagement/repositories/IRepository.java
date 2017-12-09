package edu.mum.eventmanagement.repositories;

import java.util.List;

public interface IRepository<TEntity> {
	public List<TEntity> getAll();
	
	public void add(TEntity entity);

}
