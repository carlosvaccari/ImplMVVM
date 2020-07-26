package br.com.cvaccari.orders.features

import android.app.Application
import android.content.Context
import br.com.cvaccari.orders.features.previousorders.di.previousOrdersModule
import br.com.cvaccari.orders.repository.di.repositoryModule
import br.com.cvaccari.orders.repository.local.di.localDataSourceModule
import br.com.cvaccari.orders.repository.remote.di.networkModule
import br.com.cvaccari.orders.usecases.di.useCaseModule
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class App: Application(), KodeinAware {

    companion object {
        const val CONTEXT_TAG = "CONTEXT"
        const val DATABASE_TAG = "DATABASE_TAG"
    }

    override val kodein = ConfigurableKodein(mutable = true)

    init {
        kodein.addConfig {
            bind<Context>(tag = CONTEXT_TAG) with provider { this@App }
            import(previousOrdersModule)
            import(repositoryModule)
            import(localDataSourceModule)
            import(networkModule)
            import(useCaseModule)
        }
    }
}