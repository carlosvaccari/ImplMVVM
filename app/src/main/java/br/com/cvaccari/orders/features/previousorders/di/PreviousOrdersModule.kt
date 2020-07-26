package br.com.cvaccari.orders.features.previousorders.di

import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModel
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModelImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val previousOrdersModule = Kodein.Module("previousOrdersModule") {
    bind<PreviousOrdersViewModel>() with provider{
        PreviousOrdersViewModelImpl(
            instance(),
            instance()
        )
    }
}