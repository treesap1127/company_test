package kr.ac.kopo.util;

import org.springframework.validation.Errors;

import kr.ac.kopo.model.One;

public class Validator implements org.springframework.validation.Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return One.class.isAssignableFrom(clazz);
	}

	// user validator
	@Override
	public void validate(Object target, Errors errors) {
		One userDTO = (One)target;
		
		String dtoName = errors.getObjectName();
/*			 비밀번호 확인 validator 예시!
		if(dtoName.equals("joinUserDTO")) {
			if(!userDTO.getLogin_pw().equals(userDTO.getLogin_pw2())) {
				errors.rejectValue("login_pw", "NotEquals");
					//이거 properties로 불러옴!
				errors.rejectValue("login_pw2", "NotEquals");
			}
		}
		if(dtoName.equals("modifyUserDTO")) {
			if(!userDTO.getLogin_pw().equals(userDTO.getLogin_pw2())) {
				errors.rejectValue("login_pw", "NotEquals");
				errors.rejectValue("login_pw2", "NotEquals");
			}
		}
*/		
	}

}
