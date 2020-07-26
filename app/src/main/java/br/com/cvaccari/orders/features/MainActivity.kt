package br.com.cvaccari.orders.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import br.com.cvaccari.orders.R
import kotlinx.android.synthetic.main.content_app_bar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar_main)
        setContentView(R.layout.activity_main)
        Navigation.findNavController(this, R.id.fragment_host)
            .navigate(R.id.previous_orders_fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
