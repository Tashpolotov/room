package com.example.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.room.R
import com.example.room.databinding.ActivityMainBinding
import com.example.room.di.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initialize()
        listener()
        observers()
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.sort-> {
                vm.sort()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initialize() {
        binding.rvTodo.adapter = vm.adapter

        vm.getAllTask()

    }

    private fun observers() {
        vm.tasks.observe(this){
            binding.swipeTodo.isRefreshing = false
            vm.adapter.updateList(it)



        }

        vm.event.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }


    private fun listener() {
        binding.btnTodo.setOnClickListener{
            vm.insertTask(binding.edtTodo.text.toString())


        }

        binding.swipeTodo.setOnRefreshListener {
            vm.getAllTask()
        }
    }
}