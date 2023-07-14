package ru.alexashin.firstproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexashin.firstproject.dao.BookDAO;
import ru.alexashin.firstproject.dao.PersonDAO;
import ru.alexashin.firstproject.models.Book;
import ru.alexashin.firstproject.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "book/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person){
        model.addAttribute("book",bookDAO.show(id));
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner",bookOwner.get());
        }
        else{
            model.addAttribute("people", personDAO.index());
        }
        return "book/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }
        bookDAO.update(id,book);
        return "redirect:/book";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect:/book";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }
    @PatchMapping("/{id}/delFromBook")
    public String delFromBook(@PathVariable("id") int id){
        bookDAO.delFromBook(id);
        return "redirect:/book/"+id;
    }
    @PatchMapping("/{id}/addToBook")
    public String addToBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.addToBook(id,person);
        return "redirect:/book/"+id;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book/";
    }
}
