package com.aamir.service;

import com.aamir.dto.PasswordChangeRequest;

public interface UserService {

	public void changePassword(PasswordChangeRequest passwordChangeRequest);
}
