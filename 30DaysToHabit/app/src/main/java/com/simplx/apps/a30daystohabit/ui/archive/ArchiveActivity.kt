package com.simplx.apps.a30daystohabit.ui.archive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.ui.main.MainActivity
import com.simplx.apps.a30daystohabit.utils.getTypeFace
import com.simplx.apps.a30daystohabit.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_archive.*

@AndroidEntryPoint
class ArchiveActivity : AppCompatActivity() {

    private lateinit var adapter: ArchiveAdapter
    private lateinit var viewModel: ArchiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)

        viewModel = ViewModelProvider(this).get(ArchiveViewModel::class.java)

        adapter = ArchiveAdapter(this)
        archiveRecycler.layoutManager = LinearLayoutManager(this)
        archiveRecycler.adapter = adapter

        arch_tv.typeface = this.getTypeFace()
        title_Archive_toolbar.typeface = this.getTypeFace()

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
            .setTitle(resources.getString(R.string.delete_msg))
            .setItems(options) { _, which ->

                if (which == 0) {

                    val position = viewHolder.adapterPosition
                    val habit = adapter.getHabitByPosition(position)

                    habit.id?.let { viewModel.deleteArchivedHabit(it) }
                    this.showToast(resources.getString(R.string.confirm_delete))
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