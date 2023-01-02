package com.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.entity.Customer;
import com.entity.Role;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.exceptions.UserAlreadyExists;
import com.exceptions.UserNotFoundException;
import com.repository.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	// method to register or add the user

	@Override
	public User register(User user) {

		User userToBeCreated = null;

		if (user instanceof Customer) {

			if (userRepository.findByEmail(user.getEmail()).isPresent())
				throw new UserAlreadyExists("Email is already registered.Try to login");
			else if (user.getRole() == Role.ADMIN)
				throw new UserNotFoundException("Customer role cannot be admin");

			userToBeCreated = userRepository.save(user);

		}

		else if (user instanceof Admin) {
			if (userRepository.findByEmail(user.getEmail()).isPresent())
				throw new UserAlreadyExists("Email is already registered.Try to login");

			userToBeCreated = userRepository.save(user);

		}

		return userToBeCreated;

	}

	// method for the authentication of the user

	@Override
	public User login(User user) {
		String userName = user.getEmail();
		String password = user.getPassword();

		Optional<User> optionalUser = userRepository.findByEmail(userName);

		if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(password)) {

			throw new UserNotFoundException("Bad Credentials");
		}

		return optionalUser.get();
	}

	// method to reset the password using userId and userEmail

	@Override
	public User resetPasswordById(Integer id, Map<Object, Object> fields) throws UserNotFoundException {
		if (userRepository.findById(id).isEmpty())
			throw new UserNotFoundException("Not customer found by id : " + id);
		else {

			User user = userRepository.findById(id).get();

			fields.forEach((k, v) -> {
				Field field = ReflectionUtils.findRequiredField(User.class, (String) k);

				field.setAccessible(true);

				try {
					if (field.getName().equals("email")) {
						if (!field.get(user).toString().equals(v.toString()))
							throw new UserNotFoundException("Email mismatch for the id :" + id);

					}
				} catch (IllegalArgumentException | IllegalAccessException e) {

				}
				ReflectionUtils.setField(field, user, v);
			});

			return userRepository.save(user);
		}
	}
	
	//method to get the users by role

	@Override
	public List<User> userByRole(Role role) {

		List<User> findAll = userRepository.findAll();
		List<User> collect = null;

		if (!findAll.isEmpty())
			collect = findAll.stream().filter(u -> u.getRole().equals(role)).collect(Collectors.toList());
		else
			throw new ResourceNotFoundException("Not records found for the role:" + role);

		return collect;
	}
	
	//method to delete the user using id

	@Override
	public User removeUser(Integer id) {
		
		
		Optional<User> findById = userRepository.findById(id);
		findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id:" + id));

		userRepository.deleteById(id);

		return findById.get();
	}

	//method to update the user
	@Override
	public User updateUser(Integer id, User user) {
		Optional<User> findById = userRepository.findById(id);
		findById.orElseThrow(() -> new UserNotFoundException("There are no customer having id: " + id));

		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			if (userRepository.findByEmail(user.getEmail()).get().getId() != id)
				throw new UserAlreadyExists("Email is already registered try to login");

		}
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			if (userRepository.findByEmail(user.getEmail()).get().getRole()!= user.getRole())
				throw new UserAlreadyExists("Role cannot be changed");

		}

		return userRepository.save(user);
	}

}