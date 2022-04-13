package net.sherafatpour.cryptocurrencylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.sherafatpour.cryptocurrencylist.data.models.CryptoModel
import net.sherafatpour.cryptocurrencylist.databinding.CryptoItemBinding


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
                binding.cryptoName.text = symbol.uppercase() +" "+name
                binding.cryptoPrice.text = "\$"+current_price
                binding.cryptoRank.text = "${position + 1}"
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

    inner class CryptoViewHolder(val binding: CryptoItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}