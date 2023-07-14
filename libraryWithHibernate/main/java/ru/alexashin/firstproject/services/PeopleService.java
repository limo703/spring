package ru.alexashin.firstproject.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexashin.firstproject.models.Book;
import ru.alexashin.firstproject.models.Person;
import ru.alexashin.firstproject.repositories.PeopleRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {return peopleRepository.findAll();}

    public Person findOne(int id) {return peopleRepository.findById(id).orElse(null);}
    @Transactional
    public void save(Person person){ peopleRepository.save(person);}
    @Transactional
    public void update(int id, Person updatedPerson){
    updatedPerson.setId(id);
    peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
    peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByName(String name){
    return peopleRepository.findByName(name);
    }

    public List<Book> getBooksByPersonId(int id){
    Optional<Person> person = peopleRepository.findById(id);
    if(person.isPresent()){
        Hibernate.initialize(person.get().getBooks());
        person.get().getBooks().forEach(book -> {
            long diffInMilles = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
            if(diffInMilles > 864000000)
                book.setExpired(true);
        });
        return person.get().getBooks();

    }
    else{
        return Collections.emptyList();
    }
    }
}
