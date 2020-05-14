package com.example.jetpack2.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.jetpack2.MainActivity
import com.example.jetpack2.R
import org.w3c.dom.Text

class EditDiaDIalog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.edit_dia, null)

            val caixaTxt = view.findViewById<TextView>(R.id.caixaTxt)
            val notaFiscalTxt = view.findViewById<TextView>(R.id.notaFiscalTxt)
            val plantonistasTxt = view.findViewById<TextView>(R.id.plantonistasTxt)
            val dinheiroTxt = view.findViewById<TextView>(R.id.dinheiroTxt)
            val cartaoTxt = view.findViewById<TextView>(R.id.cartaoTxt)
            val depositoTxt = view.findViewById<TextView>(R.id.depositoTxt)
            val totalTxt = view.findViewById<TextView>(R.id.totalTxt)

            caixaTxt.text = if(MainActivity.diaViewModel!!.getCurrentDia()!!.caixa == null) "" else MainActivity.diaViewModel!!.getCurrentDia()!!.caixa.toString()
            notaFiscalTxt.text = if(MainActivity.diaViewModel!!.getCurrentDia()!!.notaFiscal == null) "" else MainActivity.diaViewModel!!.getCurrentDia()!!.notaFiscal.toString()
            plantonistasTxt.text = if(MainActivity.diaViewModel!!.getCurrentDia()!!.plantonistas == null) "" else MainActivity.diaViewModel!!.getCurrentDia()!!.plantonistas.toString()
            dinheiroTxt.text = "Dinheiro:"+MainActivity.diaViewModel!!.getCurrentDia()!!.dinheiro().toString()
            cartaoTxt.text = "Cartão:"+MainActivity.diaViewModel!!.getCurrentDia()!!.cartao().toString()
            depositoTxt.text = "Deposito:"+MainActivity.diaViewModel!!.getCurrentDia()!!.deposito().toString()
            totalTxt.text = "Total:"+MainActivity.diaViewModel!!.getCurrentDia()!!.total().toString()

            builder.setView(view)
                .setPositiveButton("Editar",
                    DialogInterface.OnClickListener { dialog, id ->
                        MainActivity.diaViewModel!!.getCurrentDia()!!.caixa = caixaTxt!!.text.toString().toDouble()
                        MainActivity.diaViewModel!!.getCurrentDia()!!.notaFiscal = notaFiscalTxt!!.text.toString().toDouble()
                        MainActivity.diaViewModel!!.getCurrentDia()!!.plantonistas = plantonistasTxt!!.text.toString()
                        if(!MainActivity.existDia()){
                            MainActivity.dias!!.add(MainActivity.diaViewModel!!.getCurrentDia()!!)
                        }
                        getDialog()!!.cancel()
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}