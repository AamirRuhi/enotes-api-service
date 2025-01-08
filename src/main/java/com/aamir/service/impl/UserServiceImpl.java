package com.aamir.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.EmailRequest;
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
	
    @Autowired
    private EmailService emailService;
    
    
	@Override
	public boolean register(UserDto userDto) throws Exception {
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
			//send email to user for confirmation register ,util me class EmailService bna liya and emailRequest in dto, ab yaha emailSend(saveUser) methpd
			emailSend(saveUser);
			return true;
		}
		return false;  // AuthController bnalenge
	}


	private void emailSend(User saveUser) throws Exception {
		String message = "Hi,<b>"+ saveUser.getFirstName()+"</b> "
	           + "<br> Your account register sucessfully.<br>"
				+ "<br> Click the below link verify & Active your account <br>"
				+ "<a href='#'>Click Here</a> <br><br>" 
				+ "Thanks,<br> Welcom in Aamir's project.com";

		EmailRequest emailRequest=EmailRequest.builder()
				.to(saveUser.getEmail())
				.title("your account registered success fully in aamir website")
				.subject("account created success ")
				//message ke liye alag se
				.message(message)
				.build();
		emailService.sendEmail(emailRequest);
	}


	private void setRole(UserDto userDto, User user) {
		List<Integer> reqRoleId = userDto.getRoles().stream().map(r->r.getId()).toList();
		//reqRoleId se all all object get krenge
		List<Role> roles = roleRepository.findAllById(reqRoleId);
		user.setRoles(roles);
	}

}
