package com.bosandroidapp.aopayfinance.data.model

data class NavParentItem(val title: String,
                         val children: List<String>,
                         var isExpanded: Boolean = false)
