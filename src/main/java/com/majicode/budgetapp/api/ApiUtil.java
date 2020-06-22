package com.majicode.budgetapp.api;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

import com.majicode.budgetapp.model.Error;

public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Determine if a provided String is in the proper UUID format
     * 
     * @param uuidTest the String to test
     * @return true if the String is in the proper format
     */
    public static boolean isValidUUID(final String uuidTest) {
    	try {
    		UUID.fromString(uuidTest);
    		
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
    
    /**
     * Generate an error with a unique UUID representing the error.
     * The code is generated from a random UUID for easier log processing.
     * 
     * @param message general message of what the error is
     * @param description additional details about the error
     * @return the Error
     */
    public static Error generateError(final String message, final String description) {
    	final Error error = new Error();
    	final UUID errorCode = UUID.randomUUID();
    	
    	error.setCode(errorCode.toString());
    	error.setDescription(description);
    	error.setMessage(message);
    	
    	return error;
    }
}
