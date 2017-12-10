package edu.mum.eventmanagement.controllers;

import java.util.List;
import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.User;
import edu.mum.eventmanagement.repositories.CountryRepository;
import edu.mum.eventmanagement.repositories.UserRepository;

public class UserController {
	UserRepository userRepository = new UserRepository();
	CountryRepository countryRepository = new CountryRepository();
	
	public void createHost(User host) {
		User user = getUserByEmail(host.getEmail());
				
		if(user == null) {
			host.addRole(new edu.mum.eventmanagement.models.Host());
			userRepository.add(host);
		}else {
			user.addRole(new edu.mum.eventmanagement.models.Host());
			userRepository.update(user);
		}
	}
		
	public List<Country> getCountries(){
		return countryRepository.getAll();
	}
	
	public User getUserByEmail(String email) {
		User user = userRepository.getByEmail(email);
		return user;
	}
}
