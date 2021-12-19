package com.okedoc.productdashboard.presentations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okedoc.productdashboard.databinding.ItemProductsBinding
import com.okedoc.productdashboard.domain.entities.product.ProductDto

class ProductAdapter(
    private val onClickListener: ProductActionListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val listProducts: ArrayList<ProductDto> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listProducts[position])
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    fun setData(newData: List<ProductDto>) {
        listProducts.clear()
        listProducts.addAll(newData)

        notifyDataSetChanged()
    }

    class ProductViewHolder(
        private val binding: ItemProductsBinding,
        private val onClickListener: ProductActionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ProductDto) {
            binding.itemSku.text = data.sku
            binding.itemProductName.text = data.productName

            binding.buttonEditProduct.setOnClickListener {
                onClickListener.onEditItemClick(data)
            }

            binding.buttonDeleteProduct.setOnClickListener {
                onClickListener.onDeleteItemClick(data)
            }
        }
    }
}

interface ProductActionListener {
    fun onEditItemClick(data: ProductDto)
    fun onDeleteItemClick(data: ProductDto)
}