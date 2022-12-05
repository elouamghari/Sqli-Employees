package com.elouamghari.sqli.employees.core.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elouamghari.sqli.employees.core.listeners.OnLoadMoreListener

fun RecyclerView.loadMore(listener: OnLoadMoreListener){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val linearLayoutManager = layoutManager as LinearLayoutManager?
            if (linearLayoutManager != null && adapter != null){
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter!!.itemCount - 1){
                    listener.onLoadMore()
                }
            }
        }
    })
}