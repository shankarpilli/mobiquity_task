package com.mobiquity.views.home

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.R
import com.mobiquity.database.AppDatabase
import com.mobiquity.database.daos.LocationDao
import com.mobiquity.database.enties.LocationEntity
import com.mobiquity.utils.Constants.CITY_NAME
import com.mobiquity.utils.Constants.LAT
import com.mobiquity.utils.Constants.LNG
import com.mobiquity.views.city.CityActivity
import android.os.Looper

/**
 * This is a list adapter to add in view
 */
class LocationListAdapter(
    private val mList: ArrayList<LocationEntity>,
    private val locationDao: LocationDao,
    private val context: Context
) :
    RecyclerView.Adapter<LocationListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val locationEntity = mList[position]
        holder.tv_city_name.text = locationEntity.city
        holder.tv_city_name.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, CityActivity::class.java)
            intent.putExtra(CITY_NAME, locationEntity.city)
            intent.putExtra(LAT, locationEntity.lat)
            intent.putExtra(LNG, locationEntity.lang)
            context.startActivity(intent)
        })
        holder.img_delete.setOnClickListener(View.OnClickListener {
            AsyncTask.execute {
                locationDao.delete(locationEntity)
                Handler(Looper.getMainLooper()).post(Runnable {
                    Toast.makeText(context, "City deleted", Toast.LENGTH_SHORT).show()
                    mList.removeAt(position)
                    notifyDataSetChanged()
                })
            }
        })

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tv_city_name: TextView = itemView.findViewById(R.id.tv_city_name)
        val img_delete: ImageView = itemView.findViewById(R.id.img_delete)
    }
}