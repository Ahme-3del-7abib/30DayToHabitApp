package com.simplx.apps.a30daystohabit.ui.archive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.factory.FactoryViewModel
import com.simplx.apps.a30daystohabit.model.HabitTracerViewModel
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.ui.main.HabitAdapter
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_main.*

class ArchiveActivity : AppCompatActivity() {

    private lateinit var adapter: ArchiveAdapter
    private lateinit var viewModel: HabitTracerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        setView()
    }

    private fun setView() {
        arch_tv.typeface = HabitUtils.getTypeFace(this)
        title_Archive_toolbar.typeface = HabitUtils.getTypeFace(this)

        viewModel = ViewModelProvider(this, FactoryViewModel(this.application))
            .get(HabitTracerViewModel::class.java)

        adapter = ArchiveAdapter(this)
        archiveRecycler.layoutManager = LinearLayoutManager(this)
        archiveRecycler.adapter = adapter

        loadHabits()
        setUpSwipe()
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
        itemTouchHelper.attachToRecyclerView(archiveRecycler)
    }

    private fun loadHabits() {
        viewModel.habitArcList?.observe(this, Observer {

            if (it.isNotEmpty()) {

                archiveRecycler.visibility = View.VISIBLE
                archiveEmptyView.visibility = View.GONE

                adapter.setList(it)
                archiveRecycler.adapter?.notifyDataSetChanged()

            } else {
                archiveRecycler.visibility = View.GONE
                archiveEmptyView.visibility = View.VISIBLE
            }
        })
    }

    private fun showDialog(viewHolder: RecyclerView.ViewHolder) {

        val options = arrayOf("Yes", "No")

        val builder = AlertDialog
            .Builder(this)
            .setTitle("Delete this habit From Archive ?")
            .setItems(options) { _, which ->

                if (which == 0) {

                    val position = viewHolder.adapterPosition
                    val habit = adapter.getHabitByPosition(position)

                    habit.id?.let { viewModel.deleteArchivedHabit(it) }
                    HabitUtils.showToast(applicationContext, "Habit Deleted.")
                    adapter.notifyDataSetChanged()
                }

                if (which == 1) {
                    adapter.notifyDataSetChanged()
                }
            }

        builder.create().show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}