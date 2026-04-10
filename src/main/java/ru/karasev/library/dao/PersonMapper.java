package ru.karasev.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.karasev.library.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setYear(rs.getInt("year"));
        return person;
    }
}
