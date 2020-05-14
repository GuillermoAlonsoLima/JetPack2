package com.example.jetpack2.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.jetpack2.MainActivity
import com.example.jetpack2.Models.Venda
import com.example.jetpack2.R
import com.example.jetpack2.VendasAdapter

class EditVendaDialog : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            println("funcionouEditVendaDialog")
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.edit_venda, null)

            val artesaoTxt = view.findViewById<TextView>(R.id.artesaoTxt)
            val qtdTxt = view.findViewById<TextView>(R.id.qtdTxt)
            val produtoTxt = view.findViewById<TextView>(R.id.produtoTxt)
            val valorTxt = view.findViewById<TextView>(R.id.valorTxt)
            val totalTxt = view.findViewById<TextView>(R.id.totalTxt)
            val cartaoTxt = view.findViewById<TextView>(R.id.cartaoTxt)
            val pagamentoTxt = view.findViewById<TextView>(R.id.pagamentoTxt)
            println("funcionouEditVendaDialog1")
            if(MainActivity.selectedVenda != null){
                println("funcionouEditVendaDialog2")
                artesaoTxt.text = if(MainActivity.selectedVenda!!.artesao == null) "" else MainActivity.selectedVenda!!.artesao.toString()
                qtdTxt.text = if(MainActivity.selectedVenda!!.qtd == null) "" else MainActivity.selectedVenda!!.qtd.toString()
                produtoTxt.text = if(MainActivity.selectedVenda!!.produto == null) "" else MainActivity.selectedVenda!!.produto.toString()
                valorTxt.text = MainActivity.selectedVenda!!.valor.toString()
                totalTxt.text = MainActivity.selectedVenda!!.total().toString()
                cartaoTxt.text = MainActivity.selectedVenda!!.cartao.toString()
                pagamentoTxt.text = MainActivity.selectedVenda!!.pagamento.toString()
            }

            if(MainActivity.selectedVenda == null){
                println("funcionouEditVendaDialog3")
                builder.setView(view)
                    .setPositiveButton("Adicionar",
                        DialogInterface.OnClickListener { dialog, id ->
                            MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.add(Venda(
                                    artesaoTxt.text.toString(),
                                    qtdTxt.text.toString().toInt(),
                                    produtoTxt.text.toString(),
                                    valorTxt.text.toString().toDouble(),
                                    cartaoTxt.text.toString(),
                                    pagamentoTxt.text.toString()
                                )
                            )
                            MainActivity.binding!!.listVendasRv.adapter!!.notifyItemInserted(MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.size)
                            if(!MainActivity.existDia()){
                                MainActivity.dias!!.add(MainActivity!!.diaViewModel!!.getCurrentDia()!!)
                            }
                            println("venda:"+MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.size)
                            getDialog()!!.cancel()
                        })
                    .setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            getDialog()!!.cancel()
                        })
            }else{
                println("funcionouEditVendaDialog4")
                builder.setView(view)
                    .setPositiveButton("Editar",
                        DialogInterface.OnClickListener { dialog, id ->
                            MainActivity.selectedVenda!!.artesao = artesaoTxt.text.toString()
                            MainActivity.selectedVenda!!.qtd = qtdTxt.text.toString().toInt()
                            MainActivity.selectedVenda!!.produto = produtoTxt.text.toString()
                            MainActivity.selectedVenda!!.valor = valorTxt.text.toString().toDouble()
                            MainActivity.selectedVenda!!.cartao = cartaoTxt.text.toString()
                            MainActivity.selectedVenda!!.pagamento = pagamentoTxt.text.toString()
                            MainActivity.binding!!.listVendasRv.adapter!!.notifyItemChanged(MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.indexOf(MainActivity.selectedVenda!!))
                            getDialog()!!.cancel()
                        })
                    .setNeutralButton("Deletar",
                        DialogInterface.OnClickListener { dialog, id ->
                            MainActivity.binding!!.listVendasRv.adapter!!.notifyItemRemoved(MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.indexOf(MainActivity.selectedVenda!!))
                            MainActivity.diaViewModel!!.getCurrentDia()!!.vendas.remove(MainActivity.selectedVenda!!)
                            getDialog()!!.cancel()
                        })
                    .setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            getDialog()!!.cancel()
                        })
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}