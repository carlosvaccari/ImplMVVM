package br.com.cvaccari.orders.repository.remote.di

import br.com.cvaccari.orders.features.App.Companion.CONTEXT_TAG
import br.com.cvaccari.orders.BuildConfig
import br.com.cvaccari.orders.commons.connection.ConnectionManager
import br.com.cvaccari.orders.repository.remote.RemoteDataSource
import br.com.cvaccari.orders.repository.remote.mock.MockInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = Kodein.Module("networkModule") {
    bind<Retrofit>() with provider {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(instance())
            .client(instance())
            .build()
    }

    bind<Converter.Factory>() with provider { GsonConverterFactory.create() }

    bind<RemoteDataSource>() with provider {
        instance<Retrofit>().create(RemoteDataSource::class.java)
    }

    bind<Interceptor>() with provider {
        MockInterceptor()
    }

    bind<OkHttpClient>() with provider {
        OkHttpClient.Builder()
            .addInterceptor(instance())
            .build()
    }

    bind<ConnectionManager>() with provider {
        ConnectionManager(
            instance(tag = CONTEXT_TAG)
        )
    }
}