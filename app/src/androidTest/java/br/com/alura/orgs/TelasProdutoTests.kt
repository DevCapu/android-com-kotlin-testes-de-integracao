package br.com.alura.orgs

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
    fun limpaBancoDeDados() {
        AppDatabase.instancia(InstrumentationRegistry.getInstrumentation().context).clearAllTables()
    }

    @Test
    fun devemMostrarONomeDoAplicativoNaTelaInicial() {
        Espresso.onView(ViewMatchers.withText("Orgs"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun deveTerTodosOsCamposNecessariosParaCriarUmProduto() {
        clicaNoFAB()

        Espresso.onView(ViewMatchers.withId(R.id.activity_formulario_produto_nome))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_formulario_produto_descricao))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_formulario_produto_valor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.activity_formulario_produto_botao_salvar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun deveSerCapazDeCriarUmProdutoQuandoNecessario(): Unit = runBlocking {
        clicaNoFAB()
        preencheESalvaProduto(
            nome = "Banana",
            descricao = "Banana prata",
            valor = "10.29"
        )

        Espresso.onView(ViewMatchers.withText("Banana"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Banana prata"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("R\$ 10,29"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
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

        Espresso.onView(ViewMatchers.withText("Jabuticaba"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Roxa"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("R\$ 6,19"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun preencheESalvaProduto(
        nome: String,
        descricao: String,
        valor: String
    ) {
        digita(R.id.activity_formulario_produto_nome, nome)
        digita(R.id.activity_formulario_produto_descricao, descricao)
        digita(R.id.activity_formulario_produto_valor, valor)
        clicaEmSalvar()
    }


    private fun digita(@IdRes res: Int, texto: String) =
        Espresso.onView(ViewMatchers.withId(res)).perform(
            ViewActions.replaceText(texto),
            ViewActions.closeSoftKeyboard()
        )

    private fun clicaEmSalvar() = clica(R.id.activity_formulario_produto_botao_salvar)
    private fun clicaNoFAB() = clica(R.id.activity_lista_produtos_fab)
    private fun clica(@IdRes res: Int) = Espresso.onView(ViewMatchers.withId(res))
        .perform(ViewActions.click())

    private fun clica(texto: String) = Espresso.onView(ViewMatchers.withText(texto))
        .perform(ViewActions.click())
}