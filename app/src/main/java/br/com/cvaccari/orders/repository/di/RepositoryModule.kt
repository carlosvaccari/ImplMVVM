package br.com.cvaccari.orders.repository.di

import br.com.cvaccari.orders.repository.Repository
import br.com.cvaccari.orders.repository.RepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


val repositoryModule = Kodein.Module("repositoryModule") {

    bind<Repository>() with provider {
        RepositoryImpl(
            instance(),
            instance(),
            instance()
        )
    }
}