package edu.mum.eventmanagement.repositories;

import java.util.List;

import edu.mum.eventmanagement.models.Event;

public interface IRepository<TEntity> {
	public List<TEntity> getAll();
	
	public void add(TEntity entity);

	public void delete(TEntity entity);
	
	public TEntity get(int id);
	
	public TEntity update(TEntity entity);
	
	@SuppressWarnings("unchecked")
	public static <TEntity> IRepository<TEntity> getRepository(String className){
		IRepository<TEntity> repository = null;
		
		if(className.endsWith("Activity")) {
			repository = (IRepository<TEntity>) new ActivityRepository();
		}else if(className.endsWith("Advertisement")) {
			repository = (IRepository<TEntity>) new AdvertisementRepository();
		}else if(className.endsWith("Country")) {
			repository = (IRepository<TEntity>) new CountryRepository();
		}else if(className.endsWith("Event")) {
			repository = (IRepository<TEntity>) new EventRepository();
		}else if(className.endsWith("Gift")) {
			repository = (IRepository<TEntity>) new GiftRepository();
		}else if(className.endsWith("Location")) {
			repository = (IRepository<TEntity>) new LocationRepository();
		}else if(className.endsWith("Schedule")) {
			repository = (IRepository<TEntity>) new ScheduleRepository();
		}else if(className.endsWith("User")) {
			repository = (IRepository<TEntity>) new UserRepository();
		}
		
		return repository;
	}
}
