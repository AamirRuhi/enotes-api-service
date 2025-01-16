package com.aamir.config;

import java.util.Optional;
//createdby,updatedby yaha se krenge AuditorAware me integer qki createdby integer type ka hai
import org.springframework.data.domain.AuditorAware;

import com.aamir.entity.User;
import com.aamir.util.CommonUtil;

public class AuditAwareConfig implements AuditorAware<Integer>{

	@Override
	public Optional<Integer> getCurrentAuditor() {
		// yaha user get kr skte h security ke help se,projectconfig me ek bean bnayenge
		//jo AuditAwareConfig me static user ko diya hai wo dynamic krna hai 
		User loggedInUser = CommonUtil.getLoggedInUser();
		return Optional.of(loggedInUser.getId());//optional dena hai qki null bhi ho skta hai
	}

}
