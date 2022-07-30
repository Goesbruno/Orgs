package br.com.alura.orgs.model

import java.math.BigDecimal

//Data Class para criar o objeto "Produto"
data class Produto(
        val nome: String,
        val descricao: String,
        val valor: BigDecimal,
        val imagem: String? = null
)
