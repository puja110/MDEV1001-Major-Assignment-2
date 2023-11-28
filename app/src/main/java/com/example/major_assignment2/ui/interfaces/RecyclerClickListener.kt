package com.example.major_assignment2.ui.interfaces

interface RecyclerClickListener {
    fun onItemDeleteClick(position: Int)
    fun onEditClick(position: Int)
    fun onItemClick(position: Int)
}