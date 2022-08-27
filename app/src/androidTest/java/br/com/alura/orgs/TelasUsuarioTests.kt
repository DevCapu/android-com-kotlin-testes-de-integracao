package br.com.alura.orgs

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.alura.orgs.ui.activity.FormularioCadastroUsuarioActivity
import br.com.alura.orgs.ui.activity.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TelasUsuarioTests {
    @Test
    fun deveMostrarNomeDoAplicativoQuandoEstaNaTelaDeLogin() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withText("Orgs")).check(matches(isDisplayed()))
    }

    @Test
    fun deveTerTodosOsCamposNecessariosFazerLogin() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.activity_login_usuario)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_login_senha)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_login_botao_entrar)).check(matches(isDisplayed()))
    }

    @Test
    fun deveTerTodosOsCamposNecessariosFazerCadastro() {
        ActivityScenario.launch(FormularioCadastroUsuarioActivity::class.java)

        onView(withId(R.id.activity_formulario_cadastro_usuario)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_cadastro_email)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_cadastro_senha)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_cadastro_botao_cadastrar)).check(matches(isDisplayed()))
    }
}