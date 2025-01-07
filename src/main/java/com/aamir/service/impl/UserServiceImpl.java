package com.aamir.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.UserDto;
import com.aamir.entity.Role;
import com.aamir.entity.User;
import com.aamir.repository.RoleRepository;
import com.aamir.repository.UserRepository;
import com.aamir.service.UserService;
import com.aamir.util.Validation;

@Service
public class UserServiceImpl implements UserService{
	
	
    @Autowired
	private UserRepository userRepository;
	
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private Validation validation;
    
    @Autowired
	private ModelMapper modelMapper;
	
    
	@Override
	public boolean register(UserDto userDto) {
		//validation krenge id ke liye util pakege ke validation class me
		validation.userValidation(userDto);
		//GlobalExceptionhandler me ek exception handler bnalenge IllegalArgumentException
		//mapper se userdto ko  model class me change krna hai
		User user = modelMapper.map(userDto, User.class);
	// 	"data": "JSON parse error: Cannot deserialize value of type `java.util.ArrayList<com.aamir.dto.UserDto$RoleDto>
		//` from Object value (token `JsonToken.START_OBJECT`)"  without setRole(userDto); method we get this exception register time
		setRole(userDto,user);
		
		User saveUser = userRepository.save(user);
		//check save hua to object milega wrna null hoga
		if(!ObjectUtils.isEmpty(saveUser)) {
			return true;
		}
		return false;  // AuthController bnalenge
	}


	private void setRole(UserDto userDto, User user) {
		List<Integer> reqRoleId = userDto.getRoles().stream().map(r->r.getId()).toList();
		//reqRoleId se all all object get krenge
		List<Role> roles = roleRepository.findAllById(reqRoleId);
		user.setRoles(roles);
	}

}
