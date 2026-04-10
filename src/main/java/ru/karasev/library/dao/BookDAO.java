package ru.karasev.library.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.karasev.library.model.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> bookList() {
        String sql = "SELECT b.*, p.name as person_name FROM book b " +
                "LEFT JOIN person p ON b.id_person = p.id";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public Book showBook(int id) {
        String sql = "SELECT b.*, p.name as person_name FROM book b " +
                "LEFT JOIN person p ON b.id_person = p.id WHERE b.id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBook(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void takeBook(int idBook, int personId) {
        jdbcTemplate.update("UPDATE book SET id_person = ? WHERE id = ? AND id_person IS NULL",
                personId, idBook);
    }

    public void giveAwayBook(int idBook) {
        jdbcTemplate.update("UPDATE book SET id_person = NULL WHERE id = ?", idBook);
    }
}
