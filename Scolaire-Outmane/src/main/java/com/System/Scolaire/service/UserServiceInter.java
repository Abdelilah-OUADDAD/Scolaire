package com.System.Scolaire.service;

import java.util.List;

import com.System.Scolaire.model.Dto.UserDto;
import com.System.Scolaire.model.entity.User;

public interface UserServiceInter {

	UserDto GetUser(Integer Id);
	UserDto SaveUser(UserDto user);
	void DeleteUser(Integer id);
	UserDto UpdateUser(UserDto user);
	List<User> getAllUser();
}
