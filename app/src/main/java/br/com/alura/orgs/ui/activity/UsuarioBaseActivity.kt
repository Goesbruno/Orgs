package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.extensions.vaiPara
import br.com.alura.orgs.model.Usuario
import br.com.alura.orgs.preferences.datastore
import br.com.alura.orgs.preferences.usuarioLogadoPreference
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class UsuarioBaseActivity : AppCompatActivity() {

    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    private val _usuario: MutableStateFlow<Usuario?> = MutableStateFlow(null)
    protected val usuario: StateFlow<Usuario?> = _usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            verificaUsuarioLogado()
        }
    }

    //Verifica se há algum usuário salvo no DataStore
    private suspend fun verificaUsuarioLogado() {
        datastore.data.collect { preferences ->
            preferences[usuarioLogadoPreference]?.let { usuarioId ->
                buscaUsuario(usuarioId)
            } ?: vaiParaLogin()
        }
    }

    private suspend fun buscaUsuario(usuarioId: String): Usuario? {
        return usuarioDao
            .buscaPorId(usuarioId)
            .firstOrNull().also {
                _usuario.value = it
            }
    }

    //Remove o usuário salvo no DataStore
    protected suspend fun deslogaUsuario() {
        datastore.edit { preferences ->
            preferences.remove(usuarioLogadoPreference)
        }
    }

    //Função que exibe a tela de login caso não haja usuário logado
    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}