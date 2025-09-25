package com.example.scheduleviewjc

import java.time.LocalDateTime

data class TaskItem(
    val taskId: Int,
    val location: String,
    val startTime: LocalDateTime, // "10:00"
    val endTime: LocalDateTime,  // "11:00"
    val status : String,
    val completedSubtasks:Int,
    val totalSubtasks:Int
)

// Example tasks for the selected day
//This can be stored in Firestore, however for demo purposes, we will use a static list
val allTasks = listOf(
    TaskItem(
        1,
        "Hillcrest",
        LocalDateTime.of(2025, 9, 1, 10, 0),
        LocalDateTime.of(2025, 9, 1, 11, 0),
        TaskStatus.COMPLETED.toString(),
        5, 5
    ),
    TaskItem(
        2,
        "Durban",
        LocalDateTime.of(2025, 9, 10, 14, 0),
        LocalDateTime.of(2025, 9, 10, 15, 30),
        TaskStatus.NEW.toString(),
        0, 4
    ),
    TaskItem(
        3,
        "Pinetown",
        LocalDateTime.of(2025, 8, 31, 16, 0),
        LocalDateTime.of(2025, 8, 31, 17, 0),
        TaskStatus.EXPIRED.toString(),
        1, 3
    ),
    TaskItem(
        4,
        "Umhlanga",
        LocalDateTime.of(2025, 9, 2, 9, 30),
        LocalDateTime.of(2025, 9, 2, 10, 30),
        TaskStatus.COMPLETED.toString(),
        5, 5
    ),
    TaskItem(
        5,
        "Westville",
        LocalDateTime.of(2025, 9, 3, 9, 0),
        LocalDateTime.of(2025, 9, 3, 10, 0),
        TaskStatus.COMPLETED.toString(),
        0, 1
    ),
    TaskItem(
        6,
        "Hillcrest",
        LocalDateTime.of(2025, 9, 3, 12, 0),
        LocalDateTime.of(2025, 9, 3, 13, 15),
        TaskStatus.NEW.toString(),
        0, 4
    ),
    TaskItem(
        7,
        "Overport",
        LocalDateTime.of(2025, 9, 3, 15, 0),
        LocalDateTime.of(2025, 9, 3, 16, 15),
        TaskStatus.NEW.toString(),
        0, 2
    ),
    TaskItem(
        8,
        "Ballito",
        LocalDateTime.of(2025, 9, 4, 15, 0),
        LocalDateTime.of(2025, 9, 4, 16, 30),
        TaskStatus.NEW.toString(),
        0, 6
    ),
    TaskItem(
        9,
        "Chatsworth",
        LocalDateTime.of(2025, 9, 5, 8, 0),
        LocalDateTime.of(2025, 9, 5, 9, 0),
        TaskStatus.NEW.toString(),
        0, 3
    ),
    TaskItem(
        10,
        "Phoenix",
        LocalDateTime.of(2025, 9, 6, 11, 0),
        LocalDateTime.of(2025, 9, 6, 12, 0),
        TaskStatus.NEW.toString(),
        0, 3
    ),
    TaskItem(
        11,
        "Glenwood",
        LocalDateTime.of(2025, 9, 7, 17, 0),
        LocalDateTime.of(2025, 9, 7, 18, 0),
        TaskStatus.NEW.toString(),
        0, 2
    )
)
