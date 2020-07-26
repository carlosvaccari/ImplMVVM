package br.com.cvaccari.orders.previousorders

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cvaccari.orders.commons.MainCoroutineRule
import br.com.cvaccari.orders.commons.getOrAwaitValue
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModel
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModelImpl
import br.com.cvaccari.orders.previousorders.PreviousOrdersHelper.getItemsList
import br.com.cvaccari.orders.previousorders.PreviousOrdersHelper.newItemCount
import br.com.cvaccari.orders.usecases.GetPreviousOrdersUseCase
import br.com.cvaccari.orders.usecases.UpdatePreviousOrdersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PreviousOrdersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var getPreviousOrdersUseCase: GetPreviousOrdersUseCase

    @MockK
    lateinit var updatePreviousOrdersUseCase: UpdatePreviousOrdersUseCase

    lateinit var viewModel: PreviousOrdersViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel =
            PreviousOrdersViewModelImpl(getPreviousOrdersUseCase, updatePreviousOrdersUseCase)
    }

    @Test
    fun `get items with success`() {
        coEvery { getPreviousOrdersUseCase(Unit) } returns flowOf(getItemsList())

        assertTrue(viewModel.items.getOrAwaitValue().isNotEmpty())
        assertTrue(viewModel.items.getOrAwaitValue().size == 3)
    }

    @Test
    fun `get items with error`() {
        coEvery { getPreviousOrdersUseCase(Unit) } returns flowOf(listOf())

        assertTrue(viewModel.items.getOrAwaitValue().isEmpty())
        assertTrue(viewModel.hasError().getOrAwaitValue())
    }

    @Test
    fun `update items with success`() {
        coEvery { getPreviousOrdersUseCase(Unit) } returns flowOf(getItemsList())
        coEvery { updatePreviousOrdersUseCase(any()) } just runs
        viewModel.updateItem(newItemCount())

        assertTrue(!viewModel.isLoading().getOrAwaitValue())
        assertTrue(viewModel.showSuccessMessage().getOrAwaitValue())
    }

}