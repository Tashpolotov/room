package com.example.room.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.room.data.Task
import com.example.room.data.TaskDao
import com.example.room.di.MainAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


@HiltViewModel
class MainViewModel @Inject constructor(private val dao: TaskDao): ViewModel(){


    val event = MutableLiveData<String>()
    val adapter = MainAdapter(this::click)
    val tasks = MutableLiveData<List<Task>>().apply {
        value = ArrayList()
    }

    fun insertTask(task: String) {

        if(task.isNotEmpty() && task.isNotBlank())
    dao.insert(Task(task = task))
        else event.value = "Task is empty!"
    }

    private fun click(pos:Int){

        dao.deleteTask(adapter.getTask(pos))

        setData()
    }

    fun getAllTask(){
        tasks.value = dao.getTasks()
    }

    private fun setData() {
        val listOfTask = dao.getTasks()
        adapter.updateList(listOfTask)
    }

    fun sort(){
        adapter.updateList(dao.getListByAlphabet())
    }

}