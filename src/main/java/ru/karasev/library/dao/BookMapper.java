package ru.karasev.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.karasev.library.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));

        int personId = rs.getInt("id_person");
        if (rs.wasNull()) {
            book.setPersonId(null);
        } else {
            book.setPersonId(personId);
        }

        try {
            book.setPersonName(rs.getString("person_name"));
        } catch (SQLException e) {
        }

        return book;
    }
}
