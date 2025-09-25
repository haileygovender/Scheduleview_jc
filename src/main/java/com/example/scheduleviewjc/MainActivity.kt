package com.example.scheduleviewjc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleviewjc.ui.theme.ScheduleViewJCTheme
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleViewJCTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WeeklyScheduleScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTopBar(
    currentDate: LocalDate,
    onPreviousWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onMoreClick: (String) -> Unit,
    onOpenLegend: () -> Unit
) {
    val dateText = remember(currentDate) { getFormattedDate(currentDate) }

    //creating a top bar to hold the dates and menu bar
    TopAppBar(
        title = { Text(dateText, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            IconButton(onClick = {/*TODO*/}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_calendar_today_24),
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF006064),
            titleContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onPreviousWeek) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Previous Week", tint = Color.White)
            }
            IconButton(onClick = onNextWeek) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next Week",tint = Color.White)
            }
            var expanded by remember { mutableStateOf(false) }
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More",tint = Color.White)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Legend") },
                        onClick = { expanded = false; onOpenLegend() }
                    )
                    DropdownMenuItem(
                        text = { Text("About") },
                        onClick = { expanded = false; onMoreClick("About") }
                    )
                }
            }
        }
    )
}
@Composable
//retrieve the taskList to create a lazy column
// This will allow card views to be created for each task
fun TaskList(tasks: List<TaskItem>) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        items(tasks) { task ->
            val statusColor = when (task.status) {
                TaskStatus.COMPLETED.toString() -> Color(0xFF4CAF50)
                TaskStatus.NEW.toString() -> Color(0xFF2196F3)
                TaskStatus.EXPIRED.toString() -> Color.DarkGray
                else -> Color.LightGray
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {/*TODO*/}
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)) {
                    Box(
                        modifier = Modifier
                            .width(30.dp) // thickness of the color strip
                            .fillMaxHeight()
                            .background(statusColor)
                    )
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column( modifier = Modifier.weight(1f))
                        {
                            Text(
                                task.location, fontWeight =
                                    FontWeight.Bold, fontSize = 16.sp
                            )
                            Text(
                                "${task.startTime.format(timeFormatter)} - ${task.endTime.format(timeFormatter)}",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        //This is to display the number of tasks completed out of the total tasks
                        //A circular progress indicator is used to visually represent the progress
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                            progress = { task.completedSubtasks.toFloat() / task.totalSubtasks },
                            modifier = Modifier.size(48.dp),
                            color = if (task.status == TaskStatus.EXPIRED.toString()) Color.DarkGray else Color(0xFF2196F3),
                            strokeWidth = 4.dp,
                            trackColor = Color.LightGray
                            )
                            Text(
                                "${task.completedSubtasks}/${task.totalSubtasks}",
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayTabs(
    weekDates: List<LocalDate>,
    selectedIndex: Int,
    onDaySelected: (Int) -> Unit
) {
    //These are daily tabs to allow user to select a specific day of the week
    // We are limited to view one day at a time and 7 days in a week
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 0.dp,
        containerColor = Color(0xFF1A1A1A),
        indicator = {tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                color = Color(0xFFFFC107)
            )
        }) {
        weekDates.forEachIndexed { index, date ->
            val dayLabel = date.dayOfWeek.name.take(3) // First 3 letters of the day
            val dateLabel = date.dayOfMonth.toString()
            val selected = selectedIndex == index
            Tab(
                modifier = Modifier.fillMaxWidth(),
                selected =selected,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray,
                onClick = { onDaySelected(index) },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(dayLabel,
                            fontWeight = FontWeight.Bold,
                            color = if (selected) Color.White else Color.LightGray)

                        Text(dateLabel,
                            fontSize = 12.sp,
                            color = if (selected) Color.White else Color.LightGray)
                    }
                }
            )
        }
    }
}


fun getCurrentWeekDates(startDate: LocalDate = LocalDate.now()): List<LocalDate> {
    val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
    return (0..6).map { firstDayOfWeek.plusDays(it.toLong()) }
}

fun getFormattedDate(date: LocalDate = LocalDate.now()): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE dd, MMMM yyyy", Locale.getDefault())
    return date.format(formatter)
}

@Composable
fun WeeklyScheduleScreen() {
    val showLegendDialog = remember { mutableStateOf(false) }
    var weekStartDate by remember { mutableStateOf(LocalDate.now().with(DayOfWeek.MONDAY)) }
    val weekDates = remember(weekStartDate) { getCurrentWeekDates(weekStartDate) }
    val todayIndex = weekDates.indexOf(LocalDate.now())
    val pagerState = rememberPagerState(initialPage = if (todayIndex >= 0) todayIndex else 0,
        pageCount = {weekDates.size})
    val coroutineScope = rememberCoroutineScope()

    Column {
        ScheduleTopBar(
            currentDate = weekDates[pagerState.currentPage],
            onPreviousWeek = {
                weekStartDate = weekStartDate.minusWeeks(1)
                coroutineScope.launch { pagerState.scrollToPage(0) }
            },
            onNextWeek = {
                weekStartDate = weekStartDate.plusWeeks(1)
                coroutineScope.launch { pagerState.scrollToPage(0) }
            },
            onMoreClick = { option -> /*handle option*/ },
            onOpenLegend = { showLegendDialog.value = true},
        )

        if (showLegendDialog.value) {
            LegendDialog(onDismiss = { showLegendDialog.value = false })
        }

        DayTabs(
            weekDates = weekDates,
            selectedIndex = pagerState.currentPage,
            onDaySelected = { index ->
             coroutineScope.launch { pagerState.animateScrollToPage(index) } }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val tasksForDay = allTasks.filter { it.startTime.toLocalDate() == weekDates[page] }
            TaskList(tasks = tasksForDay)
        }
    }
}

@Composable
fun LegendDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Legend") },
        text = {
            Column {
                Text("🔵 New Task")
                Text("🟢 Completed Task")
                Text("⚫ Expired Task")
                Text("🟡 In-Progress Task")
            }

        },
        confirmButton = {
            IconButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScheduleViewJCTheme {
        WeeklyScheduleScreen()
    }
}