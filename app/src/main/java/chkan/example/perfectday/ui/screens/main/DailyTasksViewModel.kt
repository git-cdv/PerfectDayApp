package chkan.example.perfectday.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chkan.example.perfectday.domain.models.Task
import chkan.example.perfectday.domain.usecases.daily_tasks.AddDailyTaskUseCase
import chkan.example.perfectday.domain.usecases.daily_tasks.GetDailyTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val addDailyTask: AddDailyTaskUseCase,
    getDailyTasks: GetDailyTasksUseCase,
): ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    val dailyTasks: Flow<List<Task>> = getDailyTasks.run()

    init {
        viewModelScope.launch {
            delay(2000)
            _isReady.value = true
        }
    }

    fun addDailyTask(title: String, now: LocalDateTime) {
        viewModelScope.launch(Dispatchers.IO) {
            addDailyTask.run(title,now)
        }
    }
}