package net.sherafatpour.cryptocurrencylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import net.sherafatpour.cryptocurrencylist.databinding.ActivityMainBinding
import net.sherafatpour.cryptocurrencylist.viewModel.CryptoViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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

        lifecycleScope.launchWhenStarted {
            cryptoViewModel.cryptoStatus.collect { event ->
                when (event) {
                    is CryptoViewModel.CryptoStatus.Success -> {
                        binding.progress.isVisible = false
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, event.result.size.toString(), Toast.LENGTH_LONG).show()
                            binding.text.text = event.result[0].name

                        }


                    }
                    is CryptoViewModel.CryptoStatus.Failure ->{
                        binding.progress.isVisible = false
                        binding.text.text = event.error

                    }
                    is CryptoViewModel.CryptoStatus.Loading-> {
                        binding.progress.isVisible = true


                    }
                    else -> Unit


                }


            }
        }


    }
}