package com.simplx.apps.a30daystohabit.ui.archive

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.pojo.ArchivedHabit
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.ui.add.AddHabitActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils

class ArchiveAdapter(private val context: Context) :
    RecyclerView.Adapter<ArchiveAdapter.ArchiveHabitsViewHolder>() {

    private var archiveList: List<ArchivedHabit> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveHabitsViewHolder {
        return ArchiveHabitsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.archive_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return archiveList.size
    }

    fun setList(list: List<ArchivedHabit>) {
        this.archiveList = list
        notifyDataSetChanged()
    }

    fun getHabitByPosition(position: Int): ArchivedHabit {
        return archiveList[position]
    }

    override fun onBindViewHolder(holder: ArchiveHabitsViewHolder, position: Int) {
        holder.title?.text = archiveList[position].name
        holder.title?.typeface = HabitUtils.getTypeFace(context)
        holder.achTV?.typeface = HabitUtils.getTypeFace(context)

        holder.progress?.text = archiveList[position].achievement.toInt().toString()
        holder.successDays?.text = archiveList[position].successDays.toString()
        holder.failedDays?.text = archiveList[position].failedDays.toString()
    }

    class ArchiveHabitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView? = null
        var progress: TextView? = null
        var successDays: TextView? = null
        var failedDays: TextView? = null
        var achTV: TextView? = null

        init {
            title = itemView.findViewById(R.id.title_Arc_id)
            progress = itemView.findViewById(R.id.achievementArcId)
            successDays = itemView.findViewById(R.id.successDayArcId)
            failedDays = itemView.findViewById(R.id.failedDayArcId)
            achTV = itemView.findViewById(R.id.ac_arc_id)
        }
    }
}