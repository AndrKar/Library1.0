package ru.karasev.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karasev.library.dao.BookDAO;
import ru.karasev.library.dao.PersonDAO;
import ru.karasev.library.model.Book;

import javax.validation.Valid;

@RequestMapping("/books")
@Controller
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String indexBooks(Model model) {
        model.addAttribute("books", bookDAO.bookList());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("people", personDAO.personList()); // для выдачи
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/take")
    public String takeBook(@PathVariable("id") int id,
                           @RequestParam("personId") int personId) {
        bookDAO.takeBook(id, personId);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.giveAwayBook(id);
        return "redirect:/books/{id}";
    }
}