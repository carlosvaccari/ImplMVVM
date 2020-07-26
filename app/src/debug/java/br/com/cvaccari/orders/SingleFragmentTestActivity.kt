package br.com.cvaccari.orders

import android.os.Bundle
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment_test)
    }

    fun setupFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.fragment_host, fragment)
            .commitNow()
    }
}
