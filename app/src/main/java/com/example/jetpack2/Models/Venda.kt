package com.example.jetpack2.Models

class Venda(var artesao:String, var qtd:Int, var produto:String,var valor:Double, var cartao:String?, var pagamento:String?) {

    fun total():Double{
        return qtd*valor
    }

}