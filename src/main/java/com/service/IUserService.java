package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entity.Role;
import com.entity.User;
import com.exceptions.UserNotFoundException;


@Service
public interface IUserService {
	
	
	    User register(User user);
	
	    User login(User user);
	    
	    User updateUser(Integer id,User customer) throws UserNotFoundException;	
	    
	    User resetPasswordById(Integer id, Map<Object, Object> fields) throws UserNotFoundException;
	    
	    List<User> userByRole(Role role);
	    
	    User removeUser(Integer id);

}