package br.com.cvaccari.orders.commons.custombindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.orders.R
import br.com.cvaccari.orders.base.BaseAdapter
import com.bumptech.glide.Glide

@BindingAdapter("entries")
fun <T> RecyclerView.setEntries(entries: List<T>?) {
    if (entries != null) {
        val adapter = this.adapter as? BaseAdapter<T, *>
        adapter?.addAll(entries)
    }
}

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(adapter: RecyclerView.Adapter<*>) {
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(this.context)
    this.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
    this.adapter = adapter
}

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    if (imageUrl != null) {
        if (getTag(R.id.image_url) == null || getTag(R.id.image_url) != imageUrl) {
            setImageDrawable(null)
            setTag(R.id.image_url, imageUrl)
            Glide.with(this).load(imageUrl).into(this)
        }
    } else {
        setTag(R.id.image_url, null)
        setImageBitmap(null)
    }
}