package br.com.cvaccari.orders.previousorders

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import br.com.cvaccari.orders.R
import br.com.cvaccari.orders.commons.adapters.ItemsAdapter
import br.com.cvaccari.orders.commons.launchFragment
import br.com.cvaccari.orders.features.App
import br.com.cvaccari.orders.features.previousorders.PreviousOrdersFragment
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModel
import br.com.cvaccari.orders.previousorders.PreviousOrdersHelper.getItemsList
import br.com.cvaccari.orders.repository.Repository
import br.com.cvaccari.orders.usecases.GetPreviousOrdersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class PreviousOrdersRobot {

    val viewModel: PreviousOrdersViewModel = mockk()

    val repository: Repository = mockk(relaxed = true)

    val getPreviousOrdersUseCase: GetPreviousOrdersUseCase = mockk()

    fun launch(robotCommands: PreviousOrdersFragment.() -> Unit) {
        setUpInjections()
        launchFragment(PreviousOrdersFragment()) {
            this.apply(robotCommands)
        }
    }

    private fun setUpInjections() {
        ApplicationProvider.getApplicationContext<App>().kodein.addConfig {
            bind<PreviousOrdersViewModel>(overrides = true) with provider {
                viewModel
            }
            bind<Repository>(overrides = true) with provider {
                repository
            }
            bind<GetPreviousOrdersUseCase>(overrides = true) with singleton {
                getPreviousOrdersUseCase
            }
        }
        coEvery { getPreviousOrdersUseCase(Unit) } returns flowOf(getItemsList())
        coEvery { viewModel.isLoading() } returns MutableLiveData(false)
        coEvery { viewModel.items } returns MutableLiveData(getItemsList())
        coEvery { viewModel.showSuccessMessage() } returns MutableLiveData(false)
        coEvery { viewModel.showRemoveItemError() } returns MutableLiveData(false)
    }

    fun injectSuccessfulRequest() {
        coEvery { viewModel.hasError() } returns MutableLiveData(false)
        coEvery { viewModel.itemsAdapter } returns getAdapterWithItems()
    }

    fun injectFailureRequest() {
        coEvery { viewModel.hasError() } returns MutableLiveData(true)
        coEvery { viewModel.itemsAdapter } returns getAdapterWithoutItems()
    }

    fun checkListIsShown() {
        onView(withId(R.id.recyclerview_previous_orders))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkTitleIsShown() {
        onView(withId(R.id.textview_title))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    fun checkErrorContainerIsShown() {
        onView(withId(R.id.textview_message_error))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.textview_title_error))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    private fun getAdapterWithItems() = ItemsAdapter(viewModel).apply {
        addAll(getItemsList())
    }

    private fun getAdapterWithoutItems() = ItemsAdapter(viewModel)
}