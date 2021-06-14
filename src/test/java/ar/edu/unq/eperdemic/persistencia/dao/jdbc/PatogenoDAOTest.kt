package ar.edu.unq.eperdemic.persistencia.dao.jdbc

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.exception.PatogenoNotFoundRunTimeException
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.MegalodonService
import ar.edu.unq.eperdemic.utils.DataService
import ar.edu.unq.eperdemic.utils.jdbc.DataServiceJDBC
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PatogenoDAOTest{
    private var dao : PatogenoDAO = JDBCPatogenoDAO()
    private val dataService : DataService = DataServiceJDBC(dao)

    @Before
    fun crearModelo() {
        dataService.crearSetDeDatosIniciales()
    }

    @Test(expected = PatogenoNotFoundRunTimeException::class)
    fun alRecuperarUnPatogenoDeIDNoExistenteLanzaUnaExcepcion() {
        dao.recuperar(42)
    }

    @Test
    fun alCrearElPrimerPatogenoEsteTieneId1() {
        this.eliminarModelo()
        val patogeno = Patogeno()
        patogeno.tipo = "sarasa"
        val idPrimeroCreado = dao.crear(patogeno)
        val patogenoRecuperado = dao.recuperar(1)
        Assert.assertEquals(1, patogenoRecuperado.id)
    }

    @Test
    fun alCrearElIdSeAutoincrementa() {
        this.eliminarModelo()
        val patogeno1 = Patogeno()
        patogeno1.tipo = "sarasa1"
        val idPrimeroCreado = dao.crear(patogeno1)
        Assert.assertEquals(1, idPrimeroCreado)
        val patogeno2 = Patogeno()
        patogeno2.tipo = "sarasa2"
        val idSegundoCreado = dao.crear(patogeno2)
        Assert.assertEquals(2, idSegundoCreado)
        val patogeno3 = Patogeno()
        patogeno3.tipo = "sarasa3"
        val idTerceroCreado = dao.crear(patogeno3)
        Assert.assertEquals(3, idTerceroCreado)
    }

    @Test
    fun alCrearYLuegoRecuperarSeObtienePatogenosSimilares() {
        val patogenoRecuperado = dao.recuperar(1)
        Assert.assertEquals(1, patogenoRecuperado.id)
        Assert.assertEquals("Protozoo", patogenoRecuperado.tipo)
        Assert.assertEquals(0, patogenoRecuperado.cantidadDeEspecies)
    }

    @Test
    fun alGuardarYLuegoRecuperarSeObtienePatogenosSimilaresHabiendoMasDeUnoEnLaTabla() {
        val patogenoRecuperado = dao.recuperar(4)
        Assert.assertEquals(4, patogenoRecuperado.id)
        Assert.assertEquals("Bacteria", patogenoRecuperado.tipo)
        Assert.assertEquals(0, patogenoRecuperado.cantidadDeEspecies)
    }


    @Test(expected = PatogenoNotFoundRunTimeException::class)
    fun alActualizarUnPatogenoDeIDNoExistenteParaLaDBLanzaUnaExcepcion() {
        val patogenoFruta = Patogeno()
        patogenoFruta.tipo = "Sarasa"
        patogenoFruta.id = 42
        dao.actualizar(patogenoFruta)
    }

    @Test
    fun seModificanLosValoresDelPatogenoAlActualizarlo() {
        val patogenoOriginal = dao.recuperar(4)
        Assert.assertEquals(4, patogenoOriginal.id)
        Assert.assertEquals("Bacteria", patogenoOriginal.tipo)
        Assert.assertEquals(0, patogenoOriginal.cantidadDeEspecies)

        val patogenoActualizado = Patogeno()
        patogenoActualizado.tipo = "Batman"
        patogenoActualizado.id = 4
        patogenoActualizado.cantidadDeEspecies = 42
        dao.actualizar(patogenoActualizado)
        val patogenoRecuperado = dao.recuperar(4)
        Assert.assertEquals(patogenoOriginal.id, patogenoRecuperado.id)
        Assert.assertEquals(4, patogenoRecuperado.id)
        Assert.assertEquals("Batman", patogenoRecuperado.tipo)
        Assert.assertEquals(42, patogenoRecuperado.cantidadDeEspecies)
    }

    @Test(expected = PatogenoNotFoundRunTimeException::class)
    fun alIntentarActualizarUnPatogenoQueNoExisteArrojaUnaExcepcion() {
        val fruta = Patogeno()
        fruta.tipo = "Sarasa"
        fruta.id = 666
        dao.actualizar(fruta)
    }

    @Test
    fun elRecuperarTodosTraeUnaListaVaciaCuandoNoHayNingunDatoPersistido() {
        this.eliminarModelo()
        val patogenosRecuperados = dao.recuperarATodos()
        Assert.assertEquals(0, patogenosRecuperados.size)
        Assert.assertTrue(patogenosRecuperados.isEmpty())
    }

    @Test
    fun elRecuperarTodosTraeTodosLosPatogenosPersistidos() {
        val patogenosRecuperados = dao.recuperarATodos()
        Assert.assertEquals(4, patogenosRecuperados.size)
        val nombres = patogenosRecuperados.map{ it.tipo }
        Assert.assertTrue(nombres.contains("Bacteria"))
        Assert.assertTrue(nombres.contains("Protozoo"))
        Assert.assertTrue(nombres.contains("Hongo"))
        Assert.assertTrue(nombres.contains("Virus"))
        val ids = patogenosRecuperados.map{ it.id}
        Assert.assertTrue(ids.contains(1))
        Assert.assertTrue(ids.contains(2))
        Assert.assertTrue(ids.contains(3))
        Assert.assertTrue(ids.contains(4))
    }

    @Test
    fun elRecuperarTodosTraeUnaListaCon4PatogenosOrdenadosAlfabeticamenteSegunSuTipo() {
        val patogenosRecuperados = dao.recuperarATodos()
        Assert.assertEquals(4, patogenosRecuperados.size)
        Assert.assertFalse(patogenosRecuperados.isEmpty())
        Assert.assertEquals("Bacteria", patogenosRecuperados.get(0).tipo)
        Assert.assertEquals("Hongo", patogenosRecuperados.get(1).tipo)
        Assert.assertEquals("Protozoo", patogenosRecuperados.get(2).tipo)
        Assert.assertEquals("Virus", patogenosRecuperados.get(3).tipo)
    }

    @Test
    fun elEliminarTodoBorraTodosLosPatogenos() {
        var patogenosRecuperados = dao.recuperarATodos()
        this.eliminarModelo()
        Assert.assertEquals(4, patogenosRecuperados.size)
        patogenosRecuperados = dao.recuperarATodos()
        Assert.assertTrue(patogenosRecuperados.isEmpty())
        Assert.assertEquals(0, patogenosRecuperados.size)
    }

    @After
    fun eliminarModelo() {
        MegalodonService().eliminarTodo()
    }
}