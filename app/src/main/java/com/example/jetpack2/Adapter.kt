package com.example.jetpack2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack2.Dialogs.EditVendaDialog
import com.example.jetpack2.Models.Venda


class VendasAdapter (private val vendas: List<Venda>) : RecyclerView.Adapter<VendasAdapter.ViewHolder>()
{
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val artesaoTextView = itemView.findViewById<TextView>(R.id.artesaoTxt)
        val produtoTextView = itemView.findViewById<TextView>(R.id.produtoTxt)
        val totalTextView = itemView.findViewById<TextView>(R.id.totalTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val adapterView = inflater.inflate(R.layout.activity_adapter, parent, false)
        return ViewHolder(adapterView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val venda: Venda = vendas.get(position)
        viewHolder.artesaoTextView.text = venda.artesao
        println("vendaBind:"+viewHolder.artesaoTextView.text)
        viewHolder.produtoTextView.text = venda.produto
        viewHolder.totalTextView.text = venda.total().toString()
        viewHolder.itemView.setOnClickListener({
            MainActivity.showEditVenda(venda)
        })
    }

    override fun getItemCount(): Int {
        return vendas.size
    }
}
