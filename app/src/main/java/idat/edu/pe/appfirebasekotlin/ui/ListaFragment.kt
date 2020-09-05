package idat.edu.pe.appfirebasekotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import idat.edu.pe.appfirebasekotlin.R
import idat.edu.pe.appfirebasekotlin.adapter.ImagenAdapter
import idat.edu.pe.appfirebasekotlin.adapter.PersonaAdapter
import idat.edu.pe.appfirebasekotlin.model.Imagen
import idat.edu.pe.appfirebasekotlin.model.Persona


class ListaFragment : Fragment() {

    lateinit var rvfirestore : RecyclerView
    lateinit var firestoredb: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_lista, container, false)
        val lstpersonas : ArrayList<Persona> = ArrayList()
        rvfirestore = view.findViewById(R.id.rvfirestore)
        firestoredb = FirebaseFirestore.getInstance()
        firestoredb.collection("Persona")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    lstpersonas.add(Persona(document.getString("nombre").toString(),
                        document.getString("apellido").toString(),
                        document.getLong("edad").toString().toInt()))
                }
            }
            .addOnFailureListener { exception ->

            }
            .addOnCompleteListener {
                rvfirestore.adapter = PersonaAdapter(lstpersonas, view.context)
                rvfirestore.layoutManager = LinearLayoutManager(view.context)
            }
        return view
    }

}