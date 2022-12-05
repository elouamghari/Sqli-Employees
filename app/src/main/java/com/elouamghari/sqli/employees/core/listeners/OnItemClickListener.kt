package com.elouamghari.sqli.employees.core.listeners

fun interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}