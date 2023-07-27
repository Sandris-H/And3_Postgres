package com.example.test3.dao;

import com.example.test3.model.Person;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PersonDAO {

    private static int PEOPLE_COUNT = 2;

    private static final String URL = "jdbc:postgresql://192.168.0.134:5432/people";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new CopyOnWriteArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM people";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                people.add(person);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return people;


    }

    public void save(Person person) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO People VALUES(?, ?, ?, ?)");

            preparedStatement.setInt(1, ++PEOPLE_COUNT);
            preparedStatement.setString(2, person.getName());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person show(int id) {

        Person person = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM People WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setSurname(resultSet.getString("surname"));
            person.setAge(resultSet.getInt("age"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return person;
    }

    public void update(int id, Person updatedPerson) {
        Person updatingPerson = show(id);
        updatingPerson.setName(updatedPerson.getName());
        updatingPerson.setAge(updatedPerson.getAge());
        updatingPerson.setSurname(updatedPerson.getSurname());

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE People SET name=?, age=?, surname=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getSurname());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void delete(int id) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM People WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

}
