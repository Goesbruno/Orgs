package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Produto
import java.math.BigDecimal


//Data Access Object - classe da qual irão pertencer os comportamentos relacionados ao manuseio de dados
class ProdutosDao {

    //Função que adiciona itens novos a multablelist de produtos
    fun adiciona(produto: Produto){
        produtos.add(produto)
    }

    //Função que retorna os valores de produtos como uma lista
    // para que outras partes do código não possam fazer alteração
    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    //Singleton que irá armazenar a lista de dados
    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Cesta de frutas",
                descricao = "Laranja, maçã e uva",
                valor = BigDecimal("9.99")
            )
        )
    }

}