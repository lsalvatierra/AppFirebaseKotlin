package idat.edu.pe.appfirebasekotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.common.primitives.UnsignedBytes.toInt
import com.google.firebase.firestore.FirebaseFirestore
import idat.edu.pe.appfirebasekotlin.R
import kotlinx.android.synthetic.main.activity_login.*


class RegistroFragment : Fragment() {

    lateinit var firestore: FirebaseFirestore
    lateinit var etnombre : TextInputEditText
    lateinit var etapellido : TextInputEditText
    lateinit var etedad : TextInputEditText
    lateinit var btnregistrar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_registro, container, false)
        etnombre  = view.findViewById(R.id.etnombre)
        etapellido = view.findViewById(R.id.etapellido)
        etedad = view.findViewById(R.id.etedad)
        btnregistrar = view.findViewById(R.id.btnregistrar)
        firestore = FirebaseFirestore.getInstance()
        btnregistrar.setOnClickListener {
            if (etnombre.text?.isNotEmpty()!! &&
                etapellido.text?.isNotEmpty()!! &&
                etedad.text?.isNotEmpty()!!){
                registrarPersona()
            }

        }
        return view
    }

    private fun registrarPersona(){
        val persona = hashMapOf(
            "apellido" to etapellido.text.toString(),
            "edad" to etedad.text.toString().toInt(),
            "nombre" to etnombre.text.toString()
        )
        firestore.collection("Persona")
            .add(persona)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(context,
                    "El Id del registro es: ${documentReference.id}", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->

            }

    }

}