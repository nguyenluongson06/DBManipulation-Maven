package com.example;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskService {
    private static final Logger logger = LogManager.getLogger(TaskService.class);

    public void getAllTasks() {
        String query = "SELECT * FROM tasks";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.isBeforeFirst()) {
                logger.info("All tasks fetched successfully");
                System.out.println("All tasks fetched successfully");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int taskId = resultSet.getInt("task_id");
                    Timestamp timestamp = resultSet.getTimestamp("date");
                    LocalDateTime dateTime = timestamp.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String formattedDate = dateTime.format(formatter);

                    float time = resultSet.getFloat("time");
                    String assignee = resultSet.getString("assignee");
                    String reviewer = resultSet.getString("reviewer");
                    System.out.println("ID: " + id + ", Name: " + name + ", Task ID: " + taskId + ", Date: " + formattedDate + ", Time: " + time + ", Assignee: " + assignee + ", Reviewer: " + reviewer);
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving tasks", e);
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        String query = "INSERT INTO tasks (name, task_id, date, time, assignee, reviewer) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getName());
            statement.setInt(2, task.getTaskTypeId());
            statement.setTimestamp(3, task.getDate());
            statement.setFloat(4, task.getTime());
            statement.setString(5, task.getAssignee());
            statement.setString(6, task.getReviewer());
            statement.executeUpdate();
            logger.info("Added new task: {}", task.getName());
            System.out.println("Added new task: " + task.getName());
        } catch (Exception e) {
            logger.error("Error adding task", e);
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void deleteTask(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("Deleted task with id: {}", id);
            System.out.println("Deleted task with id: " + id);
        } catch (SQLException e) {
            logger.error("Error deleting task", e);
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}