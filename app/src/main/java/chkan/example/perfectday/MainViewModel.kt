package chkan.example.perfectday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chkan.example.perfectday.data.TasksRepository
import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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