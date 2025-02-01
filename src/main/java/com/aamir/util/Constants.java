package com.aamir.util;

public class Constants {
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String MOBNO_REGEX = "^[7-9][0-9]{9}$";
    //public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    
    
    //constant 
    public static final String ROLE_ADMIN="hasRole('ADMIN')"; // ab isse call krenge direct import krenge endpointclass me class-name se call nhi krenge
    
    public static final String ROLE_ADMIN_USER="hasAnyRole('USER','ADMIN')";
    
    public static final String ROLE_USER="hasRole('USER')";
    
    //define for pegination static value
    public static final String DEFAULT_PAGE_NO="0";
    public static final String DEFAULT_PAGE_SIZE="10";


}
