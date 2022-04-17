package net.sherafatpour.cryptocurrencylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.sherafatpour.cryptocurrencylist.databinding.ActivityMainBinding
import net.sherafatpour.cryptocurrencylist.viewModel.CryptoViewModel
import net.sherafatpour.cryptocurrencylist.adapter.CryptoAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var cryptoRecyclerView: RecyclerView
    private val cryptoViewModel: CryptoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        cryptoViewModel.getCryptoCurrency(
            vs_currency = "usd",
            order = "market_cap_desc",
            per_page = "20",
            page = "1",
            false
        )


        cryptoRecyclerView = binding.cryptoRecyclerview
        lifecycleScope.launchWhenStarted {
            cryptoViewModel.cryptoStatus.collect { event ->
                when (event) {
                    is CryptoViewModel.CryptoStatus.Success -> {
                        binding.progress.isVisible = false
                        binding.text.isVisible = false
                        runOnUiThread {

                            cryptoAdapter = CryptoAdapter(event.result)
                            cryptoRecyclerView.apply {
                                adapter = cryptoAdapter
                                layoutManager= LinearLayoutManager(this@MainActivity)
                                setHasFixedSize(true)
                            }
                        }


                    }
                    is CryptoViewModel.CryptoStatus.Failure ->{
                        binding.progress.isVisible = false
                        binding.text.isVisible = true
                        binding.text.text = event.error

                    }
                    is CryptoViewModel.CryptoStatus.Loading-> {
                        binding.progress.isVisible = true
                        binding.text.isVisible = false


                    }
                    else -> Unit


                }


            }
        }


    }
}