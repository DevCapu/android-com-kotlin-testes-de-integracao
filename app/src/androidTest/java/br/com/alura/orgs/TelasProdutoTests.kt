package br.com.alura.orgs

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.ui.activity.ListaProdutosActivity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TelasProdutoTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ListaProdutosActivity::class.java)

    @Before
    fun prepara() {
        AppDatabase.instancia(InstrumentationRegistry.getInstrumentation().context).clearAllTables()
    }

    @Test
    fun devemMostrarONomeDoAplicativoNaTelaInicial() {
        onView(withText("Orgs")).check(matches(isDisplayed()))
    }

    @Test
    fun deveTerTodosOsCamposNecessariosParaCriarUmProduto() {
        clicaNoFAB()

        onView(withId(R.id.activity_formulario_produto_nome)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_descricao)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_valor)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_formulario_produto_botao_salvar)).check(matches(isDisplayed()))
    }

    @Test
    fun deveSerCapazDeCriarUmProdutoQuandoNecessario(): Unit = runBlocking {
        clicaNoFAB()
        preencheESalvaProduto(
            nome = "Banana",
            descricao = "Banana prata",
            valor = "10.29"
        )

        onView(withText("Banana")).check(matches(isDisplayed()))
        onView(withText("Banana prata")).check(matches(isDisplayed()))
        onView(withText("R\$ 10,29")).check(matches(isDisplayed()))
    }

    @Test
    fun deveSerCapazDeEditarUmProdutoQuandoTiverUmCriado(): Unit = runBlocking {
        clicaNoFAB()
        preencheESalvaProduto(
            nome = "Jabuticaba",
            descricao = "Roxa",
            valor = "0.29"
        )

        clica("Jabuticaba")
        clica(R.id.menu_detalhes_produto_editar)

        preencheESalvaProduto(
            nome = "Jabuticaba",
            descricao = "Roxa",
            valor = "6.19"
        )

        onView(withText("Jabuticaba")).check(matches(isDisplayed()))
        onView(withText("Roxa")).check(matches(isDisplayed()))
        onView(withText("R\$ 6,19")).check(matches(isDisplayed()))
    }

    private fun preencheESalvaProduto(
        nome: String,
        descricao: String,
        valor: String,
    ) {
        digita(R.id.activity_formulario_produto_nome, nome)
        digita(R.id.activity_formulario_produto_descricao, descricao)
        digita(R.id.activity_formulario_produto_valor, valor)
        clicaEmSalvar()
    }

    private fun digita(
        @IdRes res: Int,
        texto: String,
    ) = onView(withId(res)).perform(replaceText(texto), closeSoftKeyboard())

    private fun clicaEmSalvar() = clica(R.id.activity_formulario_produto_botao_salvar)

    private fun clicaNoFAB() = clica(R.id.activity_lista_produtos_fab)

    private fun clica(@IdRes res: Int) = onView(withId(res)).perform(click())

    private fun clica(texto: String) = onView(withText(texto)).perform(click())
}