package chkan.example.perfectday.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chkan.example.perfectday.domain.TasksRepository
import chkan.example.perfectday.domain.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    tasksRepository: TasksRepository,
): ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

     val dailyTasks: StateFlow<List<Task>> = tasksRepository.getDailyTasksFlow()
         .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

     val weeklyTasks: StateFlow<List<Task>> = tasksRepository.getWeeklyTasksFlow()
         .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            delay(3000)
            _isReady.value = true
        }
    }
}