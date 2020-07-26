package br.com.cvaccari.orders.usecases

import br.com.cvaccari.orders.previousorders.PreviousOrdersHelper
import br.com.cvaccari.orders.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception


@RunWith(JUnit4::class)
class UpdatePreviousOrdersUseCaseTest {

    @MockK
    lateinit var repository: Repository

    lateinit var updatePreviousOrdersUseCase: UpdatePreviousOrdersUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        this.updatePreviousOrdersUseCase = UpdatePreviousOrdersUseCase(repository)
    }

    @Test
    fun getPreviousOrdersWithSucess() = runBlocking {
        val item = PreviousOrdersHelper.newItemCount()
        coEvery { repository.updatePreviousOrders(item) } returns listOf(0L)

        val insertedIds = updatePreviousOrdersUseCase(item)

        assertNotNull(insertedIds)
    }

    @Test(expected = Exception::class)
    fun getPreviousOrdersWithError() = runBlocking {
        val item = PreviousOrdersHelper.newItemCount()
        coEvery { repository.updatePreviousOrders(item) } throws Exception()
        updatePreviousOrdersUseCase(item)
    }
}