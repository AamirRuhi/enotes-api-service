package com.aamir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aamir.dto.PasswordChangeRequest;
import com.aamir.entity.User;
import com.aamir.repository.UserRepository;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void changePassword(PasswordChangeRequest passwordChangeRequest) {
	//get krenge jo user loggedin hoga uski id
		User loggedInUser = CommonUtil.getLoggedInUser();
		//passwordChangeRequest me jo oldPassword  request me  ayathen check krenge match krenge db password se then newpassword ko db me update kr denge 
		//db me password encored form me h wo match method provide krta hai,//match(oldpassword,dbpassword,)
		if(!passwordEncoder.matches( passwordChangeRequest.getOldPassword(),loggedInUser.getPassword()))
		{
			//agr old password wrong hai
			throw new IllegalArgumentException("your old password is incorrect");
		}
			//agre oldpassword thik h to set kr dunga
		String encodePassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
		loggedInUser.setPassword(encodePassword);
		userRepository.save(loggedInUser);
	//go userController
	}

}
