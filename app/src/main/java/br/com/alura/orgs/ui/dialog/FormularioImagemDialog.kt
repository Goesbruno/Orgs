package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {

                //Mantém a imagem e a url carregadas no dialog
                urlPadrao?.let {
                    formularioImagemImageVIew.tentaCarregarImagem(it)
                    formularioImagemUrl.setText(it)
                }

                //Configuração do botão que carrega a imagem a partir da url
                formularioImagemBotaoCarregar.setOnClickListener {
                    val url = formularioImagemUrl.text.toString()
                    formularioImagemImageVIew.tentaCarregarImagem(url)
                }

                //Configuração dos botões do dialog
                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = formularioImagemUrl.text.toString()
                        quandoImagemCarregada(url)

                    }
                    .setNegativeButton("Cancelar:") { _, _ ->

                    }
                    .show()
            }
    }
}