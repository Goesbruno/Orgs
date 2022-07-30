package br.com.alura.orgs.extensions

import android.widget.ImageView
import coil.load

//Função que carrega a imagem a partir de uma url utilizando a função load do Coil.
//Em caso de erro é carregado um placeholder
fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url){
        fallback(br.com.alura.orgs.R.drawable.erro)
        error(br.com.alura.orgs.R.drawable.erro)
        placeholder(br.com.alura.orgs.R.drawable.placeholder)
    }
}