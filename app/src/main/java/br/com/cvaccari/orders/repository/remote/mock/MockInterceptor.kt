package br.com.cvaccari.orders.repository.remote.mock

import br.com.cvaccari.orders.BuildConfig
import br.com.cvaccari.orders.BuildConfig.DEBUG
import okhttp3.*

class MockInterceptor : Interceptor {

    companion object {
        const val SUCCESS_CODE = 200
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.contains(BuildConfig.BASE_URL.plus("previous-orders")) -> previousOrders
                else -> ""
            }
            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            throw IllegalAccessError(
                "br.com.ps.core.data.mock.MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}

const val previousOrders = """
[ 
        {
          "id": 1,
          "name": "Pepsi Lata",
          "item_count": 5,
          "description": "120ML CAN",
          "description_quantity": "1x1 Unidad",
          "current_price": 10.20,
          "previous_price": 10.60,
          "image_url": "-R002150.png"
        },
        {
          "id": 2,
          "name": "PEPSI LIGHT 24/120NZ LATA",
          "item_count": 5,
          "description": "120ML LATA",
          "description_quantity": "1x24 Unidades",
          "current_price": 603.90,
          "image_url": "-R002151.png"
        },
        {
          "id": 3,
          "name": "Coca",
          "item_count": 4,
          "description": "Bebida",
          "description_quantity": "1x10",
          "current_price": 200.90,
          "previous_price": 380.20,
          "image_url": "-R002151.png"
        }
]
"""