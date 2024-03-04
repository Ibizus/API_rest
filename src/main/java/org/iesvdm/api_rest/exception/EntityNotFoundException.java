package org.iesvdm.api_rest.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id, Class entity){
        super("Not found Entity" + entity.getSimpleName() + " with id: " + id);
    }
}
