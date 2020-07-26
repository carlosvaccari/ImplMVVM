package br.com.cvaccari.orders.repository.local.di

import androidx.room.Room.databaseBuilder
import br.com.cvaccari.orders.features.App.Companion.CONTEXT_TAG
import br.com.cvaccari.orders.features.App.Companion.DATABASE_TAG
import br.com.cvaccari.orders.repository.local.LocalDataSource
import br.com.cvaccari.orders.repository.local.LocalDataSourceImpl
import br.com.cvaccari.orders.repository.local.database.LocalDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


val localDataSourceModule = Kodein.Module("localDataSourceModule") {

    bind<LocalDatabase>(DATABASE_TAG) with singleton {
        databaseBuilder(
            instance(CONTEXT_TAG),
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME
        ).build()
    }

    bind<LocalDataSource>() with provider {
        LocalDataSourceImpl(
            instance<LocalDatabase>(DATABASE_TAG).itemModelDao()
        )
    }
}