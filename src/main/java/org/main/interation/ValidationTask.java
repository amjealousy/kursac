package org.main.interation;

import jakarta.validation.ConstraintViolation;
import javafx.concurrent.Task;

import validation.ValidationUtilsBackend;

import java.util.*;

public class ValidationTask<T> extends Task<String> {
    public String validateMessage;
    public String name;
    public T actedUponEntity;
    public ValidationTask(String validateMessage, T entity) {
        this.validateMessage=validateMessage;
        this.actedUponEntity =entity;
        this.name ="";
    }
    public ValidationTask(String validateMessage, T entity,String name) {
        this.validateMessage=validateMessage;
        this.actedUponEntity =entity;
        this.name=name;
    }
    private static final ValidationUtilsBackend validation = new ValidationUtilsBackend();


    @Override
    protected String call() throws Exception {
        List<String> listRes =new ArrayList<>();
        Set<ConstraintViolation<T>> valid = validation.getValid(actedUponEntity);

        for (ConstraintViolation<T> mess: valid){
            System.out.println("valid in BackgroundTask " + mess.getMessage());
            if(name.isBlank()) {
                listRes.add(mess.getMessage());
            }
            else if (name.contains(mess.getPropertyPath().toString())) {
                System.out.println("listRes added");
                listRes.add(mess.getMessage());

            }
        }

        updateValue(validateMessage);
        validateMessage=String.join("\n",listRes);
        updateProgress(1,1);
        return validateMessage;
    }
}
