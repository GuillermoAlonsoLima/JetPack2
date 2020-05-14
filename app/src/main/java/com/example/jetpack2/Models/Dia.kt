package com.example.jetpack2.Models

import java.util.*

class Dia(var data: Date, var caixa:Double?,var notaFiscal:Double?, var plantonistas:String?) {
    val vendas = ArrayList<Venda>()

    fun dinheiro():Double{
        var dinheiro = 0.0
        for(venda in vendas){
            if(venda.cartao == ""){
                dinheiro += venda.total()
            }
        }
        return dinheiro
    }

    fun cartao():Double{
        var cartao = 0.0
        for(venda in vendas){
            if(venda.cartao != ""){
                cartao += venda.total()
            }
        }
        return cartao
    }

    fun deposito():Double{
        if(notaFiscal != null){
            return dinheiro()-notaFiscal!!
        }else{
            return dinheiro()
        }
    }

    fun total():Double{
        var total = 0.0
        for(venda in vendas){
            total += venda.total()
        }
        return total
    }
}