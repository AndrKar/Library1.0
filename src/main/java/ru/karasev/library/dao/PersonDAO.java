package ru.karasev.library.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.karasev.library.model.Book;
import ru.karasev.library.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> personList() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person showPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year) VALUES (?, ?)",
                person.getName(), person.getYear());
    }

    public void updatePerson(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE person SET name=?, year=? WHERE id=?",
                updatePerson.getName(), updatePerson.getYear(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public List<Book> getBooksByPerson(int personId) {
        String sql = "SELECT b.* FROM book b WHERE b.id_person = ?";
        return jdbcTemplate.query(sql, new Object[]{personId}, new BookMapper());
    }
}