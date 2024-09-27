package com.example;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskTypeService {
    private static final Logger logger = LogManager.getLogger(TaskTypeService.class);

    public void addTaskType(String name) {
        String query = "INSERT INTO task_type (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.executeUpdate();
            logger.info("Added task type: " + name);
            System.out.println("Added task type: " + name);
        } catch (SQLException e) {
            logger.error("Error adding task type", e);
            System.out.println("Error adding task type: " + e.getMessage());
        }
    }

    public void deleteTaskType(int id) {
        String query = "DELETE FROM task_type WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Deleted task type with ID: {}", id);
            System.out.println("Deleted task type with ID: " + id);
        } catch (SQLException e) {
            logger.error("Error deleting task type", e);
            System.out.println("Error deleting task type: " + e.getMessage());
        }
    }

    public void getAllTaskTypes() {
        String query = "SELECT * FROM task_type";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            logger.error("Error retrieving task types", e);
            System.out.println("Error retrieving task types: " + e.getMessage());
        }
    }
}
