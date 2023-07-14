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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[] {id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(NAMEOFBOOK, NAMEOFCREATOR, YEAR) values (?,?,?)",book.getNameOfBook(),book.getNameOfCreator(),book.getYear());
    }
    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE book set nameOfBook=?, nameOfCreator=?,year=? where id = ?"
                ,updateBook.getNameOfBook(),updateBook.getNameOfCreator(),updateBook.getYear(),id);
    }
    public Optional<Person> getBookOwner(int idOfBook){
        return jdbcTemplate.query("SELECT person.* from book join person on book.idOfOwner = person.id where book.id =?",new Object[]{idOfBook},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void delFromBook(int idBook){
        jdbcTemplate.update("UPDATE book set idOfOwner=null where id = ?", idBook);
    }
    public void addToBook(int id, Person person){
        jdbcTemplate.update("UPDATE book set idOfOwner=? where id=?",person.getId(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from book where id=?",id);
    }
}
