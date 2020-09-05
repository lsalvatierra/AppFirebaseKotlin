package idat.edu.pe.appfirebasekotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import idat.edu.pe.appfirebasekotlin.R
import idat.edu.pe.appfirebasekotlin.model.Persona

class PersonaAdapter (private var lstpersona:List<Persona>,
                      private val context: Context)
    : RecyclerView.Adapter<PersonaAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater
            .from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.persona_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstpersona[position]
        holder.tvnompersona.text = item.nombre
        holder.tvapepersona.text = item.apellido
        holder.tvedadpersona.text = item.edad.toString()

    }

    override fun getItemCount(): Int {
        return lstpersona.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvnompersona : TextView  = itemView.findViewById(R.id.tvnompersona)
        val tvapepersona : TextView  = itemView.findViewById(R.id.tvapepersona)
        val tvedadpersona : TextView  = itemView.findViewById(R.id.tvedadpersona)

    }

}