package com.mobiquity.views.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.R
import com.mobiquity.database.enties.LocationEntity
import com.mobiquity.utils.Constants.CITY_NAME
import com.mobiquity.utils.Constants.LAT
import com.mobiquity.utils.Constants.LNG
import com.mobiquity.views.city.CityActivity

/**
 * This is a list adapter to add in view
 */
class LocationListAdapter(private val mList: List<LocationEntity>, private val context: Context) :
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

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tv_city_name: TextView = itemView.findViewById(R.id.tv_city_name)
    }
}