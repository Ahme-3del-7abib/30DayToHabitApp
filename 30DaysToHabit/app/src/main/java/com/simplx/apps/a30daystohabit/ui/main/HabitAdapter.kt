package com.simplx.apps.a30daystohabit.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.simplx.apps.a30daystohabit.R
import com.simplx.apps.a30daystohabit.pojo.Days
import com.simplx.apps.a30daystohabit.pojo.Habit
import com.simplx.apps.a30daystohabit.ui.add.AddHabitActivity
import com.simplx.apps.a30daystohabit.ui.update.UpdateActivity
import com.simplx.apps.a30daystohabit.utils.HabitUtils

class HabitAdapter(
    private val onHabitClickListener: OnHabitClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    private var habitList: List<Habit> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.habit_list_items, parent, false), onHabitClickListener
        )
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun setList(list: List<Habit>) {
        this.habitList = list
        notifyDataSetChanged()
    }

    fun getHabitByPosition(position: Int): Habit {
        return habitList[position]
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.title?.text = habitList[position].name
        holder.desc?.text = habitList[position].desc

        holder.title?.typeface = HabitUtils.getTypeFace(context)

        holder.settingBtn?.setOnClickListener {

            context.startActivity(
                Intent(context, UpdateActivity::class.java)
                    .putExtra("id", habitList[position].ID)
                    .putExtra("name", habitList[position].name)
                    .putExtra("desc", habitList[position].desc)
                    .putExtra("notification", habitList[position].notification)
                    .putExtra("time", habitList[position].time)
            )
        }
    }

    class HabitViewHolder(itemView: View, listener: OnHabitClickListener) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var onHabitClickListener: OnHabitClickListener? = null

        var title: TextView? = null
        var desc: TextView? = null
        var settingBtn: ImageView? = null

        init {
            title = itemView.findViewById(R.id.title_id)
            desc = itemView.findViewById(R.id.motivate_id)
            settingBtn = itemView.findViewById(R.id.settings_id)

            this.onHabitClickListener = listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onHabitClickListener?.onHabitClick(adapterPosition)
        }
    }

    interface OnHabitClickListener {
        fun onHabitClick(position: Int)
    }
}