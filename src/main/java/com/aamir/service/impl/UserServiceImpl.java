package com.aamir.service.impl;

import java.util.List;import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.config.security.CustomUserDetails;
import com.aamir.dto.EmailRequest;
import com.aamir.dto.LoginRequest;
import com.aamir.dto.LoginResponse;
import com.aamir.dto.UserRequest;
import com.aamir.entity.AccountStatus;
import com.aamir.entity.Role;
import com.aamir.entity.User;
import com.aamir.repository.RoleRepository;
import com.aamir.repository.UserRepository;
import com.aamir.service.JwtService;
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
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    
	@Override
	public boolean register(UserRequest userDto, String url) throws Exception {
		//validation krenge id ke liye util pakege ke validation class me
		validation.userValidation(userDto);
		//GlobalExceptionhandler me ek exception handler bnalenge IllegalArgumentException
		//mapper se userdto ko  model class me change krna hai
		User user = modelMapper.map(userDto, User.class);
	// 	"data": "JSON parse error: Cannot deserialize value of type `java.util.ArrayList<com.aamir.dto.UserDto$RoleDto>
		//` from Object value (token `JsonToken.START_OBJECT`)"  without setRole(userDto); method we get this exception register time
		setRole(userDto,user);
		//start acount ferification code save krte time status bhi add ho jayega
		AccountStatus status=AccountStatus.builder()
				.isActive(false)
				//Uuid use kiya random string generate krne ke liye
				.verificationCode(UUID.randomUUID().toString())
				.build();
		user.setStatus(status);
		// end  acount ferification code
		// for passwodencord
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User saveUser = userRepository.save(user);
		//check save hua to object milega wrna null hoga
		if(!ObjectUtils.isEmpty(saveUser)) {
			//send email to user for confirmation register ,util me class EmailService bna liya and emailRequest in dto, ab yaha emailSend(saveUser) methpd
			emailSend(saveUser,url);
			return true;
		}
		return false;  // AuthController bnalenge
	}


	private void emailSend(User saveUser, String url) throws Exception {
		String message = "Hi,<b>[[username]]</b> "
	           + "<br>  Your account register sucessfully.<br>"
				+ "<br> Click the below link verify & Active your account <br>"
				+ "<a href='[[url]]'>Click Here</a> <br><br>" 
				+ "Thanks,<br> Welcom in Aamir's project.com";

		// username and url denge ,dynamic krenge username and url ko replacement concept ke through
		message=message.replace("[[username]]", saveUser.getFirstName());
		  message =message.replace("[[url]]", url+"/api/v1/home/verify?uid="+saveUser.getId()+"&&code="+saveUser.getStatus().getVerificationCode());
		//http://localhost:8081/api/v1/home/auth/  hit krne pr url me id and vrification code aa rha hai ,ab verify pr click krne pe isActive true kr lenge
		//ab homecontroller bnalenge verify krne ke liye
		EmailRequest emailRequest=EmailRequest.builder()
				.to(saveUser.getEmail())
				.title("your account registered success fully in aamir website")
				.subject("account created success ")
				//message ke liye alag se
				.message(message)
				.build();
		emailService.sendEmail(emailRequest);
	}


	private void setRole(UserRequest userDto, User user) {
		List<Integer> reqRoleId = userDto.getRoles().stream().map(r->r.getId()).toList();
		//reqRoleId se all all object get krenge
		List<Role> roles = roleRepository.findAllById(reqRoleId);
		user.setRoles(roles);
	}


	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		//thenticationmanager ke through authenticate kkrne wala hu
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		//isAuthenticated return boolean
		if(authenticate.isAuthenticated())
		{
			//return krna h loginResponse ka object,loginResponse me user ka details chahiye
			CustomUserDetails	customUserDetails= (CustomUserDetails)authenticate.getPrincipal();
			//token generate ke liye 1 jwtService ,jwtserviceimpl bnaunga
			String token= jwtService.generateToken(customUserDetails.getUser());
			LoginResponse loginResponse=LoginResponse.builder()
					//userdto chaiye
					.user(modelMapper.map(customUserDetails.getUser(), UserRequest.class))
					.token(token)
					.build(); 
			return loginResponse;
		}
		return null;
		
	}

}
