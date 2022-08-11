package br.com.alura.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import br.com.alura.orgs.extensions.vaiPara
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.usuarioLogadoPreferences

abstract class UsuarioBaseActivity : AppCompatActivity() {

    protected suspend fun deslogaUsuario() {
        dataStore.edit { preferences ->
            preferences.remove(usuarioLogadoPreferences)
        }
        vaiParaLogin()
    }

    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java) {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        finish()
    }
}