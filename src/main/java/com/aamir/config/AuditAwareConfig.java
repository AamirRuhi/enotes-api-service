package com.aamir.config;

import java.util.Optional;
//createdby,updatedby yaha se krenge AuditorAware me integer qki createdby integer type ka hai
import org.springframework.data.domain.AuditorAware;

public class AuditAwareConfig implements AuditorAware<Integer>{

	@Override
	public Optional<Integer> getCurrentAuditor() {
		// yaha user get kr skte h security ke help se,projectconfig me ek bean bnayenge
		
		return Optional.of(1);//jo login h uski id deni hai ,abhi hard code kr diya
	}

}
