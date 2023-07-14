package ru.alexashin.firstproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alexashin.firstproject.models.Book;
import ru.alexashin.firstproject.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
     public List<Person> index(){//все люди
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
     }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?",new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, age) VALUES(?,?)",person.getName(),person.getAge());
    }
    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE person SET name=?, age=? where id =?", updatePerson.getName(),updatePerson.getAge(),updatePerson.getId());
    }
    public void delete(int id){
        jdbcTemplate.update("delete from person where id=?",id);
    }
    public List<Book> getBookByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM book where idOFOwner=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
