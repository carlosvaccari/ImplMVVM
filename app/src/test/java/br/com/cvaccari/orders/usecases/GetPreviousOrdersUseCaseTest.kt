package br.com.cvaccari.orders.usecases

import br.com.cvaccari.orders.previousorders.PreviousOrdersHelper
import br.com.cvaccari.orders.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GetPreviousOrdersUseCaseTest {

    @MockK
    lateinit var repository: Repository

    lateinit var getPreviousOrdersUseCase: GetPreviousOrdersUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        this.getPreviousOrdersUseCase = GetPreviousOrdersUseCase(repository)
    }

    @Test
    fun getPreviousOrdersWithSucess() = runBlocking {
        coEvery { repository.getPreviousOrders() } returns flowOf(PreviousOrdersHelper.getItemsList())

        val list = getPreviousOrdersUseCase(Unit)

        assertNotNull(list)
        assertTrue(list.single().size == 3)
    }
}