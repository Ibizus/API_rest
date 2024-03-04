package org.iesvdm.api_rest.exception;

public class NotCouplingIdException extends RuntimeException{

    public NotCouplingIdException(Long id1, Long id2, Class entity){
        super("Not coupling id1 = " + id1 + " with id2 = " + id2 + " for entity " + entity.getSimpleName());
    }
}
