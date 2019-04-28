package com.example.crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final String MESSAGE_TEMPLATE = "Resource with id %s not found.";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8800824244988133119L;
    
    public ResourceNotFoundException(Long id) {
        super(String.format(MESSAGE_TEMPLATE, id));
    }

}
