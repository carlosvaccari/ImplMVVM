package br.com.cvaccari.orders.usecases.di

import br.com.cvaccari.orders.usecases.GetPreviousOrdersUseCase
import br.com.cvaccari.orders.usecases.UpdatePreviousOrdersUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val useCaseModule = Kodein.Module("useCaseModule") {
    bind<GetPreviousOrdersUseCase>() with singleton {
        GetPreviousOrdersUseCase(
            instance()
        )
    }
    bind<UpdatePreviousOrdersUseCase>() with singleton {
        UpdatePreviousOrdersUseCase(
            instance()
        )
    }
}