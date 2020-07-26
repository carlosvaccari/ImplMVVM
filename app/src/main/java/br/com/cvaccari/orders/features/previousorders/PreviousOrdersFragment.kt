package br.com.cvaccari.orders.features.previousorders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.cvaccari.orders.R
import br.com.cvaccari.orders.databinding.FragmentPreviousOrdersBinding
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModel
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.annotations.TestOnly
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class PreviousOrdersFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: PreviousOrdersViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPreviousOrdersBinding>(
            inflater,
            R.layout.fragment_previous_orders,
            container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserFeedback()
    }

    private fun initUserFeedback() {
        viewModel.showSuccessMessage().observe(viewLifecycleOwner, Observer {
            if (it) showSuccesMessage()
        })

        viewModel.showRemoveItemError().observe(viewLifecycleOwner, Observer {
            if (it) showRemoveItemError()
        })
    }

    private fun showRemoveItemError() {
        showMessage(getString(R.string.general_error_remove_item))
    }

    private fun showSuccesMessage() {
        showMessage(getString(R.string.general_success_message))
    }

    private fun showMessage(message: String) {
        view?.apply {
            Snackbar.make(this, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }
}