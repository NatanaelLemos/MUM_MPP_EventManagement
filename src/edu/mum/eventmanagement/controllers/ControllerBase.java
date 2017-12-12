package edu.mum.eventmanagement.controllers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import edu.mum.eventmanagement.repositories.IRepository;

public class ControllerBase<TEntity> {
	private IRepository<TEntity> repository;
	
	ControllerBase(){
		this.repository = getNewRepository();
	}
	
	protected IRepository<TEntity> getRepository(){
		return this.repository;
	}
	
	public void create(TEntity entity) {
		repository.add(entity);
	}
	
	public void update(TEntity entity) {
		repository.update(entity);
	}
	
	public void delete(TEntity entity) {
		repository.delete(entity);
	}
	
	private IRepository<TEntity> getNewRepository(){
    	Type mySuperclass = getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
        String className = tType.getTypeName();
        return IRepository.getRepository(className);
    }
}
