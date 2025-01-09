package com.aamir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aamir.entity.AccountStatus;
import com.aamir.entity.User;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.exception.SuccessException;
import com.aamir.repository.UserRepository;
import com.aamir.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean verifyAccount(Integer userId, String verificationCode) throws Exception {
		
		
		//check jo userid url me h wo sahi hai ki nhi 
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid user"));
		//ager user id same hai then check jo url me verification code h or mere db me same hai ki nhi
		
		//agr koi 2nd time same url pr hit kre to vrificode to null ho chuka hai qki already 1st time kr chuka h to null vericn ko handle ke liye
          if(user.getStatus().getVerificationCode()==null) {
        	  //custom exception bnayenge SuccessException naam ka exception pakage me
        	  throw new SuccessException("Account already verified");
      		//agr koi 2nd time same url pr hit kre to vrificode to null ho chuka hai qki already 1st time kr chuka h to null vericn ko handle ke liye
          }
		
		if(user.getStatus().getVerificationCode().equals(verificationCode))
		{
			AccountStatus status = user.getStatus();
			status.setActive(true);
			//verify hone ke baad verificationcode me null qki verify ho gya
			status.setVerificationCode(null);
			
			userRepository.save(user);
			return true;
		}
		return false;
	}

}
