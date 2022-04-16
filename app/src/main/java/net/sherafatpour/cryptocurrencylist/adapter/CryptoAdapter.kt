package net.sherafatpour.cryptocurrencylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.data.models.CryptoModelItem
import net.sherafatpour.cryptocurrencylist.databinding.CryptoItemBinding
import java.text.DecimalFormat
import java.text.NumberFormat


class CryptoAdapter(private val cryptoList: CryptoModel)
    :RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
    }

    override fun getItemCount() = cryptoList.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        with(holder){
            with(cryptoList[position]) {


                val dec = DecimalFormat("#,###.00")


                val percentFormat: NumberFormat = NumberFormat.getPercentInstance()
                 percentFormat.minimumFractionDigits = 5

                binding.cryptoName.text = StringBuilder(symbol.uppercase() +" "+name)
                binding.cryptoPrice.text = StringBuilder("\$"+dec.format(current_price))
                binding.cryptoRank.text = StringBuilder("${position + 1}")
                binding.cryptoCap.text = StringBuilder("\$"+priceFormatter(market_cap))
               Glide.with(holder.itemView.context)
                    .load(image)
                    .into(binding.cryptoPhoto)

                holder.itemView.setOnClickListener {
                    Toast.makeText(holder.itemView.context, name,
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun priceFormatter(market_cap:Long): String {
        var numberString = ""
        numberString = when {
            Math.abs(market_cap / 1000000000) > 1 -> {
                (market_cap / 1000000000).toString() + " B  "
            }
            Math.abs(market_cap / 1000000) > 1 -> {
                (market_cap / 1000000).toString() + " M  "
            }
            Math.abs(market_cap / 1000) > 1 -> {
                (market_cap / 1000).toString() + " K  "
            }
            else -> {
                market_cap.toString()
            }
        }
        return numberString
    }

    inner class CryptoViewHolder(val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}