package com.mobiquity.views.home

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.mobiquity.R
import com.mobiquity.database.AppDatabase
import com.mobiquity.utils.Constants
import java.util.*

/**
 * This class is used to show list of city info
 */
class ShowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val db = Room.databaseBuilder(
            this@ShowListActivity,
            AppDatabase::class.java, Constants.DB_TABLE_NAME
        ).build()
        val locationDao = db.locationDao()
        AsyncTask.execute {
            val data = locationDao.getAll()
            val adapter = LocationListAdapter(data, this)
            recyclerview.adapter = adapter
        }
    }
}