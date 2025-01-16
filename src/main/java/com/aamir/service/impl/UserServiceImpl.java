package com.aamir.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aamir.dto.EmailRequest;
import com.aamir.dto.PasswordChangeRequest;
import com.aamir.dto.PasswordResetRequest;
import com.aamir.entity.User;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.repository.UserRepository;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	
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


	@Override
	public void sendEmailPasswordReset(String email , HttpServletRequest request) throws Exception {
		//request me email jo mil rha hai usse check krebge db wala mail se wrna invalid email
		User user = userRepository.findByEmail(email);
		if(ObjectUtils.isEmpty(user))
		{
			throw new ResourceNotFoundException("invalid email or user not availble with this email");

		}
		//agr email sahi hai to email send krnna h bnake rhe h emailservice me,EmailRequest bnake rkhe hai
		
		//Generate password reset token/code
		String passwordResetToken = UUID.randomUUID().toString();
		user.getStatus().setPasswordResetToken(passwordResetToken);
		//save krenge db me
		User updatedUser = userRepository.save(user);
		//ek method bnayenge sendemail ke liye, url get renge jo bnaya h already
		String url = CommonUtil.getUrl(request);
		sendEmailRequest(updatedUser,url);
		
	}


	private void sendEmailRequest(User user ,String url) throws Exception {
		String message = "Hi,<b>[[username]]</b> "
		           + "<br> <p> you have requested to reset your password .</p>"
					+ "<p> Click the below link to change your password </p>"
					+ "<p><a href='[[url]]'>Change my password</a> </p>" 
					+ "<p>Ignore this email if you do remember your password "
					+"or you have not made the request.</p><br>"
					+ "Thanks,<br> Welcome in Aamir's project.com";

			message=message.replace("[[username]]", user.getFirstName());
			  message =message.replace("[[url]]", url+"/api/v1/home/verify-password-link?uid="+user.getId()+"&&code="+user.getStatus().getPasswordResetToken());
			EmailRequest emailRequest=EmailRequest.builder()
					.to(user.getEmail())
					.title("Password Reset")
					.subject("Password Reset link")
					.message(message)
					.build();
			
			//send password reset email to user
			emailService.sendEmail(emailRequest);
		
		
	}


	@Override
	public void verifypswdResetLing(Integer uid, String code) throws Exception {
	//uid check krenge ,and agr koi link change kiya hai
		User user = userRepository.findById(uid).orElseThrow(()->new ResourceNotFoundException(" invalid user"));
		// method likhenge code ko verify krenge ki db wala code hai ki nhi 
		verifyPasswordResetToken(user.getStatus().getPasswordResetToken(),code);
		
		
	}

//parameter ka name change kr liya
	private void verifyPasswordResetToken(String existToken, String reqToken) {
		//request token not null
		if(StringUtils.hasText(reqToken))
		{
			//password reset hone ke baad bhi link pr click kre to ye
			if(!StringUtils.hasText(existToken))
			{
				throw new IllegalArgumentException("already password reset");

			}
			//db and requsted code same na ho to
			if(!existToken.equals(reqToken))
			{
				throw new IllegalArgumentException("invalid url ");

			}
		}else {
			//agr user code ko change kr diya
			throw new IllegalArgumentException("invalid token or code");

		}
		
		
	}


	@Override
	public void resetPassword(PasswordResetRequest passwordResetRequest) throws Exception {
		//check sahi user or not
		User user = userRepository.findById(passwordResetRequest.getUid()).orElseThrow(()->new ResourceNotFoundException(" invalid user"));
        String encodePassword = passwordEncoder.encode(passwordResetRequest.getNewPassword());
		
        user.setPassword(encodePassword);
        //verify hone ke baad null hoga agr user fir url pe click krega to exception throw kr denge
        user.getStatus().setPasswordResetToken(null);
        userRepository.save(user);
        //{"status":"succes","message":"email verified success"} 
        //hone ke baad jab tak password reset nhi krenge tabtak passwod-rest-token me null store nhi hoga(db)
	}



	
}
