```markdown
# 📅 ScheduleViewJC

A modern Jetpack Compose-based weekly scheduling UI for Android, featuring dynamic day tabs, task cards with status indicators, and a top bar with week navigation and legend support.

---

## ✨ Features

- Weekly schedule view with horizontal paging
- Scrollable day tabs with dynamic styling
- Task cards with status-based color strips and progress indicators
- Top bar with week navigation and dropdown menu
- Legend dialog for task status icons

---

## 🖼️ Screenshots

### 🗓️ Weekly Schedule Screen  
https://github.com/haileygovender/Scheduleview_jc/blob/main/assets/weekly_schedule_screen.png

### 📆 Top Bar with Navigation  
<img width="371" height="147" alt="image" src="https://github.com/user-attachments/assets/ee9ff063-a9fe-4d40-8e46-e5a0c23c98dc" />

### 📋 Task Card with Progress  
<img width="367" height="197" alt="image" src="https://github.com/user-attachments/assets/b5891028-7e52-4c6b-bee5-19d268e796a2" />

### 📌 Legend Dialog  
<img width="357" height="492" alt="image" src="https://github.com/user-attachments/assets/d2c208cd-7b10-422b-b0f2-67d68905ec4f" />

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- Kotlin 1.9+
- Jetpack Compose (Material 3)

### Clone the Project
```bash
git clone https://github.com/your-username/ScheduleViewJC.git
```

### Run the App
Open the project in Android Studio and run on an emulator or physical device.

---

## 🧩 Code Structure

- `MainActivity.kt` – Entry point and theme setup  
- `WeeklyScheduleScreen.kt` – Core layout with pager and top bar  
- `TaskList.kt` – LazyColumn rendering task cards  
- `DayTabs.kt` – Scrollable day tabs  
- `LegendDialog.kt` – Dialog showing task status icons

---

## 🗂️ Task Status Legend

| Icon | Status        | Color      |
|------|---------------|------------|
| 🔵   | New Task      | Blue       |
| 🟢   | Completed     | Green      |
| ⚫   | Expired       | Dark Gray  |
| 🟡   | In Progress   | Amber      |

---

## 📌 Notes

- Tasks are filtered by day using `LocalDate`
- Progress is visualized using `CircularProgressIndicator`
- Status colors are dynamically applied via `when` logic

---

## 📬 Contact

For questions or suggestions, feel free to reach out or open an issue.

```
