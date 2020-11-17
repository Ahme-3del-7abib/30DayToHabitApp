package com.simplx.apps.a30daystohabit.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.reminder.AlarmScheduler
import com.simplx.apps.a30daystohabit.ui.add.AddHabitActivity
import com.simplx.apps.a30daystohabit.ui.archive.ArchiveActivity
import com.simplx.apps.a30daystohabit.ui.trace.TracerActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), HabitAdapter.OnHabitClickListener {

    private lateinit var adapter: HabitAdapter
    private lateinit var viewModel: HabitTracerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()

        add_habit.setOnClickListener {

            startActivity(
                Intent(this, AddHabitActivity::class.java)
            )
        }

        arch_Btn.setOnClickListener {
            startActivity(Intent(this, ArchiveActivity::class.java))
        }
    }

    private fun setView() {

        title_toolbar.typeface = HabitUtils.getTypeFace(this)

        viewModel = ViewModelProviders.of(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        adapter = HabitAdapter(this, this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        loadHabits()
        setUpSwipe()

    }

    private fun loadHabits() {
        viewModel.habitList?.observe(this, Observer {

            if (it.isNotEmpty()) {

                recycler.visibility = View.VISIBLE
                emptyView.visibility = View.GONE

                adapter.setList(it)
                recycler.adapter?.notifyDataSetChanged()

            } else {
                recycler.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpSwipe() {

        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
                    , target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    showDialog(viewHolder)
                }
            }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recycler)
    }

    private fun showDialog(viewHolder: RecyclerView.ViewHolder) {

        val options = arrayOf("Yes", "No")

        val builder = AlertDialog
            .Builder(this)
            .setTitle("Delete this habit ?")
            .setCancelable(false)
            .setItems(options) { _, which ->

                if (which == 0) {

                    val position = viewHolder.adapterPosition
                    val habit: Habit = adapter.getHabitByPosition(position)

                    habit.ID?.let { AlarmScheduler.cancelAlarm(this, it) }
                    habit.ID?.let { viewModel.deleteHabit(it) }
                    HabitUtils.showToast(applicationContext, "Habit Deleted.")
                    adapter.notifyDataSetChanged()
                }

                if (which == 1) {
                    adapter.notifyDataSetChanged()
                }
            }

        builder.create().show()

    }

    override fun onHabitClick(position: Int) {

        val id: Int = adapter.getHabitByPosition(position).ID!!
        val name: String = adapter.getHabitByPosition(position).name
        val notify: String = adapter.getHabitByPosition(position).notification

        var intent: Intent = Intent(this, TracerActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)

        startActivity(intent)
    }
}