package chkan.example.perfectday.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//class MainViewModel(tasksRepository: TasksRepository): ViewModel() {
class MainViewModel: ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    /* val dailyTasks: StateFlow<List<Task>> = tasksRepository.getDailyTasksFlow()
         .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

     val weeklyTasks: StateFlow<List<Task>> = tasksRepository.getWeeklyTasksFlow()
         .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())*/

    init {
        viewModelScope.launch {
            delay(3000)
            _isReady.value = true
        }
    }
}