package ca.qc.cstj.tp01.presentation.deliveries.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.tp01.R
import ca.qc.cstj.tp01.databinding.ItemRessourcesInfoBinding
import ca.qc.cstj.tp01.domain.models.Delivery

class DeliveriesRecyclerViewAdapter(var deliveries : List<Delivery>): RecyclerView.Adapter<DeliveriesRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ressources_info,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = deliveries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.bind(delivery)

    }





    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemRessourcesInfoBinding.bind(view)

        fun bind(delivery: Delivery) {

            binding.txtElementEx.text = String.format("%.2f", delivery.ewhyx)
            binding.txtElementWu.text = String.format("%.2f", delivery.wusnyx)
            binding.txtElementI.text = String.format("%.2f", delivery.iaspyx)
            binding.txtElementSm.text = String.format("%.2f", delivery.smiathil)
            binding.txtElementVe.text = String.format("%.2f", delivery.vathyx)

        }

    }


}