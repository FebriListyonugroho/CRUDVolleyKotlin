package com.example.crudvolleykotlin.Adapter

import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.crudvolleykotlin.InsertData
import com.example.crudvolleykotlin.Model.ModelData
import com.example.crudvolleykotlin.R

class AdapterData(context: Context, items:List<ModelData>): RecyclerView.Adapter<AdapterData.HolderData>() {

    private val mItems:List<ModelData>
    private val context:Context
    init{
        this.mItems = items
        this.context = context
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderData {
        val layout = LayoutInflater.from(p0.getContext()).inflate(R.layout.layout_row, p0, false)
        val holderData = HolderData(layout)
        return holderData
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(p0: HolderData, p1: Int) {
        val md = mItems.get(p1)
        p0.tvnama.setText(md.nama)
        p0.tvnpm.setText(md.npm)
        p0.tvprodi.setText(md.prodi)
        p0.tvfakultas.setText(md.fakultas)
        ///Mengupdate Data
        p0.md = md


    }

    inner class HolderData(view: View):RecyclerView.ViewHolder(view) {
        var tvnama: TextView
        var tvnpm:TextView
        var tvprodi:TextView
        var tvfakultas:TextView
        ///Mengupdate Data
        lateinit var md : ModelData

        init{
            tvnama = view.findViewById(R.id.nama) as TextView
            tvnpm = view.findViewById(R.id.npm) as TextView
            tvprodi = view.findViewById(R.id.prodi) as TextView
            tvfakultas = view.findViewById(R.id.fakultas) as TextView

            ///Mengupdate Data
            view.setOnClickListener(View.OnClickListener { v ->
                val update = Intent(context,InsertData::class.java)
                update.putExtra("update",1)
                update.putExtra("npm",md.npm)
                update.putExtra("nama",md.nama)
                update.putExtra("prodi",md.prodi)
                update.putExtra("fakultas",md.fakultas)

                context.startActivity(update)
            })
        }
    }
}