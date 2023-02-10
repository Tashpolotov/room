package com.example.room.di

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.Task
import com.example.room.databinding.ItemMainBinding
import com.example.room.extension.convertLongTime


class MainAdapter(private var onClick :(Int)  ->Unit):RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    fun getTask(pos:Int):Task{
        return tasks[pos]
    }
    private var tasks = ArrayList<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tasks: List<Task>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }


    inner class ViewHolder(val view: ItemMainBinding): RecyclerView.ViewHolder(view.root) {
        fun onBind(task: Task) {
            view.tvItem.text = task.task
            view.itemDataTv.text = task.time.convertLongTime()

            itemView.setOnClickListener{
                val builder = AlertDialog.Builder(itemView.context)
                with(builder){
                    setTitle("Вы точно хотите удалить")
                    setPositiveButton("Da"){ dialog, which ->
                        onClick(adapterPosition)

                    }
                    setNegativeButton("net"){dialog, which ->
                       dialog.dismiss()
                    }
                    show()
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(tasks[position])

    }
}