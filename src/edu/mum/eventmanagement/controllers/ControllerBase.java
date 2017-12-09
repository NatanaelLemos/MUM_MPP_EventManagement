package edu.mum.eventmanagement.controllers;

import edu.mum.eventmanagement.repositories.IRepository;

public class ControllerBase<TEntity> {
	private IRepository<TEntity> repository;
	
	ControllerBase(IRepository<TEntity> repository){
		this.repository = repository;
	}
	
	protected IRepository<TEntity> getRepository(){
		return this.repository;
	}
	
	public void create(TEntity entity) {
		repository.add(entity);
	}
	
	public void delete(TEntity entity) {
		repository.delete(entity);
	}
}
