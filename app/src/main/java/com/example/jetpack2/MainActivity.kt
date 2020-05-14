package com.example.jetpack2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack2.Dialogs.EditDiaDIalog
import com.example.jetpack2.Dialogs.EditVendaDialog
import com.example.jetpack2.Models.Dia
import com.example.jetpack2.Models.Venda
import com.example.jetpack2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    companion object{
        var diaViewModel:DiasViewModel? = null
        var dias: ArrayList<Dia>? = null
        var selectedVenda: Venda? = null
        var format:SimpleDateFormat? = null
        var calendar:Calendar? = null
        var binding: ActivityMainBinding? = null
        var fm: FragmentManager? = null

        fun existDia():Boolean{
            for(dia in dias!!){
                if(dia == diaViewModel!!.getCurrentDia()){
                    println("found1!!")
                    return true
                }
            }
            println("NOT found1!!")
            return false
        }

        fun showEditVenda(venda:Venda){
            selectedVenda = venda
            var editVendaDialog = EditVendaDialog()
            editVendaDialog.show(fm!!,"Editar Dia")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fm = supportFragmentManager

        format = SimpleDateFormat("dd/MM/yyyy")
        calendar = Calendar.getInstance()
        diaViewModel = DiasViewModel()
        diaViewModel!!.setCurrentDia(Dia(Date(),null,null,null))
        dias = ArrayList()

        binding!!.listVendasRv.layoutManager = LinearLayoutManager(this)
        binding!!.listVendasRv.adapter = VendasAdapter(diaViewModel!!.getCurrentDia()!!.vendas)
        binding!!.currentDiaTxt.setText(diaViewModel!!.getFormattedDate())
    }

    fun showEditDia(view: View){
        var editDiaDialog = EditDiaDIalog()
        editDiaDialog.show(fm!!,"Editar Dia")
    }

    fun showAddVenda(view:View){
        selectedVenda = null
        var addVendaDialog = EditVendaDialog()
        addVendaDialog.show(fm!!,"Adicionar Venda")
    }

    fun previousDay(view:View){
        calendar!!.time = diaViewModel!!.getCurrentDia()!!.data
        calendar!!.add(Calendar.DATE, -1)
        diaViewModel!!.setCurrentDia(searchDia(calendar!!.time))
        binding!!.currentDiaTxt.setText(diaViewModel!!.getFormattedDate())
        binding!!.listVendasRv.adapter = VendasAdapter(diaViewModel!!.getCurrentDia()!!.vendas)
        println("prevDate:"+diaViewModel!!.getFormattedDate())
    }

    fun nextDay(view:View){
        calendar!!.time = diaViewModel!!.getCurrentDia()!!.data
        calendar!!.add(Calendar.DATE, 1)
        diaViewModel!!.setCurrentDia(searchDia(calendar!!.time))
        binding!!.currentDiaTxt.setText(diaViewModel!!.getFormattedDate())
        binding!!.listVendasRv.adapter = VendasAdapter(diaViewModel!!.getCurrentDia()!!.vendas)
        println("nextDate:"+diaViewModel!!.getFormattedDate()+";"+diaViewModel!!.getCurrentDia()!!.vendas.size)
    }

    fun searchDia(date: Date):Dia{
        for(dia in dias!!){
            println("dia:"+format!!.format(dia.data)+";date:"+format!!.format(date))
            if(format!!.format(dia.data) == format!!.format(date)){
                println("found!!!")
                return dia
            }
        }
        println("NOT found!!!")
        return Dia(date,null,null,null)
    }

}
