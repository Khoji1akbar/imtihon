package uz.ghost.imtihon.Db

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ghost.imtihon.databinding.ItemViewBinding

class Adabter {
    class UserAdabter(val list:List<String>): RecyclerView.Adapter<UserAdabter.VH>() {
        inner class VH(val itemViewBinding: ItemViewBinding): RecyclerView.ViewHolder(itemViewBinding.root){
            fun old(name:String){
                itemViewBinding.tvitem.text = name
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.old(list[position])
        }

        override fun getItemCount(): Int = list.size
    }
}