package com.mycompany.exp5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> findAll() throws SQLException {
        String sql = "SELECT id, name, course, email FROM public.students ORDER BY id";
        List<Student> students = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(readStudent(resultSet));
            }
        }
        return students;
    }

    public Student findById(int id) throws SQLException {
        String sql = "SELECT id, name, course, email FROM public.students WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? readStudent(resultSet) : null;
            }
        }
    }

    public void insert(String name, String course, String email) throws SQLException {
        String sql = "INSERT INTO public.students (name, course, email) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, course);
            statement.setString(3, email);
            statement.executeUpdate();
        }
    }

    public boolean update(int id, String name, String course, String email) throws SQLException {
        String sql = "UPDATE public.students SET name = ?, course = ?, email = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, course);
            statement.setString(3, email);
            statement.setInt(4, id);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM public.students WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Student readStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("course"),
                resultSet.getString("email"));
    }
}