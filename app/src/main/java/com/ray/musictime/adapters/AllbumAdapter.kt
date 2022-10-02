package app.medrx.MedrxApp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.medrx.MedrxApp.R
import app.medrx.MedrxApp.databinding.CategoryListRowBinding
import app.medrx.MedrxApp.model.Category
import app.medrx.MedrxApp.view.interfaces.CategoryClickListener

class CategoryAdapter(var itemList:List<Category>,  onClickListener: CategoryClickListener): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    var onClickListener = onClickListener

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryListRowBinding.bind(view)
        var imgView = binding.imgView
        var cardView = binding.cardView
        var categoryName = binding.categoryName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_list_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position].image
        holder.imgView.setImageResource(item)
        holder.cardView.setOnClickListener {
            onClickListener.categoryClick(itemList[position])
        }

        holder.categoryName.text = itemList[position].categoryName
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}