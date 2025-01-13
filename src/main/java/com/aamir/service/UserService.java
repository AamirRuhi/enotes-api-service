package com.aamir.service;

import com.aamir.dto.PasswordChangeRequest;
import com.aamir.dto.PasswordResetRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	public void changePassword(PasswordChangeRequest passwordChangeRequest);

	public void sendEmailPasswordReset(String email, HttpServletRequest request) throws Exception;

	public void verifypswdResetLing(Integer uid, String code) throws Exception;

	public void resetPassword(PasswordResetRequest passwordResetRequest) throws Exception;
}
