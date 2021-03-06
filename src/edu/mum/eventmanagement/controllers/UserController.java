package edu.mum.eventmanagement.controllers;

import java.util.List;
import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.User;
import edu.mum.eventmanagement.repositories.CountryRepository;
import edu.mum.eventmanagement.repositories.UserRepository;
import edu.mum.eventmanagement.services.CryptographyService;

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
	
	public void createGuest(User guest) {
		User user = getUserByEmail(guest.getEmail());
		
		if(user == null) {
			guest.addRole(new edu.mum.eventmanagement.models.Guest());
			userRepository.add(guest);
		}else {
			user.addRole(new edu.mum.eventmanagement.models.Guest());
			userRepository.update(user);
		}
	}
	
	public void createApprover(User approver) {
		User user = getUserByEmail(approver.getEmail());

		if(user == null) {
			approver.addRole(new edu.mum.eventmanagement.models.Approver());
			userRepository.add(approver);
		}else {
			user.addRole(new edu.mum.eventmanagement.models.Approver());
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
	
	public void InviteGuest(User newUser) {
		User oldUser = getUserByEmail(newUser.getEmail());
		if(oldUser == null) {
			newUser.addRole(new edu.mum.eventmanagement.models.Guest());
			userRepository.add(newUser);
		}else {
			System.out.println("Existed Email");
		}
	}

	public User getUserByLogin(String email, String password) {
		String cryptPassword = CryptographyService.encrypt(password);
		return userRepository.getByLogin(email, cryptPassword);
	}
}
