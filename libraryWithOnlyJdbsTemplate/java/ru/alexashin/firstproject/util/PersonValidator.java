package ru.alexashin.firstproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexashin.firstproject.dao.PersonDAO;
import ru.alexashin.firstproject.models.Person;
@Component
public class PersonValidator implements Validator {

    PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDAO.show(person.getName()).isPresent()){
            errors.rejectValue("name","","This name already taken");
        }
    }
}
