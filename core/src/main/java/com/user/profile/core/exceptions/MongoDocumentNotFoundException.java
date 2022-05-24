package com.user.profile.core.exceptions;

/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */

public class MongoDocumentNotFoundException extends RuntimeException{

    public MongoDocumentNotFoundException(String message) {
        super(message);
    }

    public MongoDocumentNotFoundException(Throwable throwable, String message) {
        super(message, throwable);
    }
}
