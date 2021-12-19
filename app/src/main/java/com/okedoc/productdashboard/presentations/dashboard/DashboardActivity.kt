package com.okedoc.productdashboard.presentations.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.okedoc.productdashboard.data.local.PreferencesKeys
import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.databinding.ActivityDashboardBinding
import com.okedoc.productdashboard.databinding.AddProductSheetBinding
import com.okedoc.productdashboard.databinding.EditProductSheetBinding
import com.okedoc.productdashboard.domain.entities.product.ProductDto
import com.okedoc.productdashboard.presentations.adapter.ProductActionListener
import com.okedoc.productdashboard.presentations.adapter.ProductAdapter
import com.okedoc.productdashboard.presentations.login.LoginFragmentSheet
import com.okedoc.productdashboard.presentations.register.RegisterFragmentSheet
import com.okedoc.productdashboard.utils.debounce
import com.okedoc.productdashboard.utils.observeUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding
    private lateinit var addProductDialog: BottomSheetDialog
    private lateinit var editProductDialog: BottomSheetDialog

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val productDataStore by preferencesDataStore(name = "ProductDashboard")

    private val progressBar by lazy {
        ProgressBar(
            this,
            null,
            android.R.attr.progressBarStyleLarge
        )
    }

    private val productAdapter by lazy { ProductAdapter(itemProductListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(dashboardBinding.root)
        setSupportActionBar(dashboardBinding.toolbarDashboard)

        initActionView()
        initRecyclerView()
        initBottomSheet()
        initObserveState()

        dashboardViewModel.getAllProducts()
    }

    private fun initBottomSheet() {
        addProductDialog = createBottomSheetLayout(
            AddProductSheetBinding.inflate(layoutInflater)
        ) {
            buttonAdd.setOnClickListener {
                val sku = addSkuInput.text.toString()
                val product = addProductInput.text.toString()
                val quantity = addQuantityInput.text.toString()
                val price = addPriceInput.text.toString()
                val unit = addUnitInput.text.toString()
                val status = addStatusInput.text.toString()

                dashboardViewModel.addProduct(
                    ProductRequest(
                        sku = sku,
                        name = product,
                        quantity = quantity.toInt(),
                        price = price.toInt(),
                        unit = unit,
                        status = status
                    )
                )
            }
        }

        editProductDialog = createBottomSheetLayout(
            EditProductSheetBinding.inflate(layoutInflater)
        ) {

            buttonEdit.setOnClickListener {
                val sku = editSkuInput.text.toString()
                val product = editProductInput.text.toString()
                val quantity = editQuantityInput.text.toString()
                val price = editPriceInput.text.toString()
                val unit = editUnitInput.text.toString()
                val status = editStatusInput.text.toString()

                dashboardViewModel.updateProduct(
                    ProductRequest(
                        sku = sku,
                        name = product,
                        quantity = quantity.toInt(),
                        price = price.toInt(),
                        unit = unit,
                        status = status
                    )
                )
            }
        }
    }

    private fun initActionView() {
        dashboardBinding.buttonAddProduct.setOnClickListener {
            addProductDialog.show()
        }

        dashboardBinding.loginBtn.setOnClickListener {
            LoginFragmentSheet.newInstance()
                .show(supportFragmentManager, LoginFragmentSheet::class.simpleName)
        }

        dashboardBinding.registerBtn.setOnClickListener {
            RegisterFragmentSheet.newInstance()
                .show(supportFragmentManager, RegisterFragmentSheet::class.simpleName)
        }

        dashboardBinding.searchTextBySku.doOnTextChanged(
            debounce(
                scope = lifecycleScope,
                onEmit = {
                    Log.d("DashboardActivity", it.toString())
                    dashboardViewModel.getProductBySku(it.toString())
                })
        )
    }

    private fun initObserveState() {
        dashboardViewModel.products.observe(this) {
            observeUiState(it, progressBar) { products ->
                productAdapter.setData(products)
            }
        }

        dashboardViewModel.productDetail.observe(this) {
            observeUiState(it, progressBar) {

            }
        }

        dashboardViewModel.addProduct.observe(this) {
            observeUiState(it, progressBar) {

            }
        }

        dashboardViewModel.deleteProducts.observe(this) {
            observeUiState(it, progressBar) {

            }
        }

        productDataStore.data.map {
            it[PreferencesKeys.IS_LOGIN]
        }.asLiveData().observe(this) {
            if (it != null && it == true) {
                dashboardBinding.buttonAddProduct.visibility = View.VISIBLE
            } else {
                dashboardBinding.buttonAddProduct.visibility = View.GONE
            }
        }
    }

    private fun <T : ViewBinding> createBottomSheetLayout(
        view: T,
        onViewCreated: T.() -> Unit
    ): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(this)

        bottomSheetDialog.setContentView(view.root)
        onViewCreated(view)

        return bottomSheetDialog
    }

    private fun initRecyclerView() {
        dashboardBinding.listProducts.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = productAdapter
        }
    }

    private val itemProductListener = object : ProductActionListener {
        override fun onEditItemClick(data: ProductDto) {
            editProductDialog.show()
        }

        override fun onDeleteItemClick(data: ProductDto) {
            dashboardViewModel.deleteProduct(data.sku)
        }
    }

}