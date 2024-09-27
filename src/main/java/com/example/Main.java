package com.example;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        TaskTypeService taskTypeService = new TaskTypeService();

        while (true) {
            System.out.println("========= Task program =========");
            System.out.println("1. Add Task Type");
            System.out.println("2. Delete Task Type");
            System.out.println("3. Get All Task Types");
            System.out.println("4. Add Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Get All Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("""
                                -------Add Task Type------
                                Name:\s""");
                        scanner = new Scanner(System.in);
                        String typeName = scanner.nextLine();
                        taskTypeService.addTaskType(typeName);
                        break;
                    case 2:
                        System.out.println("""
                                -------Del Task Type-------
                                ID:\s""");
                        scanner = new Scanner(System.in);
                        int typeId = scanner.nextInt();
                        taskTypeService.deleteTaskType(typeId);
                        break;
                    case 3:
                        System.out.println("""
                                ----------------------------------------- Task ---------------------------------------""");
                        taskTypeService.getAllTaskTypes();
                        break;
                    case 4:
                        scanner = new Scanner(System.in);
                        System.out.println("""
                                ------------Add Task---------------
                                Task Name:\s""");

                        String taskName = scanner.nextLine();

                        scanner = new Scanner(System.in);
                        System.out.println("Task type ID: ");
                        int taskTypeId = scanner.nextInt();

                        scanner = new Scanner(System.in);
                        System.out.println("Date: ");
                        //get datetime input as string
                        String tempDate = scanner.nextLine();
                        //then use format to parse the input string into java.sql.Time
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate dateDay = LocalDate.parse(tempDate, formatter);
                        LocalDateTime dateTime = dateDay.atStartOfDay();
                        Timestamp date = Timestamp.valueOf(dateTime);

                        scanner = new Scanner(System.in);
                        System.out.println("From: ");
                        float from = scanner.nextFloat();
                        scanner = new Scanner(System.in);
                        System.out.println("To: ");
                        float to = scanner.nextFloat();
                        float time = to - from;

                        scanner = new Scanner(System.in);
                        System.out.println("Assignee: ");
                        String assignee = scanner.nextLine();

                        scanner = new Scanner(System.in);
                        System.out.println("Reviewer: ");
                        String reviewer = scanner.nextLine();

                        Task task = new Task(taskName, taskTypeId, date, time, assignee, reviewer);
                        taskService.addTask(task);
                        break;
                    case 5:
                        scanner = new Scanner(System.in);
                        System.out.println("""
                                ---------Del Task------
                                ID:\s""");
                        int id = scanner.nextInt();
                        taskService.deleteTask(id);
                        break;
                    case 6:
                        taskService.getAllTasks();
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }
    }
}