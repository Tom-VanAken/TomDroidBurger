package fr.isen.vanaken.tomdroidburger

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import java.lang.String.format
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val burgerList = resources.getStringArray(R.array.burger_list)

        val spinnerBurger = findViewById<Spinner>(R.id.spinnerBurger)
            if(spinnerBurger != null) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, burgerList)
                spinnerBurger.adapter = adapter
            }



        val editLivraison = findViewById<EditText>(R.id.inputLivraison)

        editLivraison.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                editLivraison.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }



        val button = findViewById<Button>(R.id.buttonValider)

        button.setOnClickListener{
            val good = CheckFields()

            if(good){

                val adresse = findViewById<EditText>(R.id.inputAdresse).text.toString()
                val burger = findViewById<Spinner>(R.id.spinnerBurger).selectedItem.toString()
                val livraison = findViewById<EditText>(R.id.inputLivraison).text.toString()

                val intent = Intent(this, ValidationActivity::class.java)
                intent.putExtra("adresse",adresse)
                intent.putExtra("burger",burger)
                intent.putExtra("heure",livraison)
                startActivity(intent)
            }
        }

        }

    private fun CheckFields(): Boolean {
        val nom = findViewById<EditText>(R.id.inputNom)
        val prenom = findViewById<EditText>(R.id.inputPrenom)
        val adresse = findViewById<EditText>(R.id.inputAdresse)
        val telephone = findViewById<EditText>(R.id.inputTelephone)
        val livraison = findViewById<EditText>(R.id.inputLivraison)

        if(nom.getText().toString().isEmpty()){
            nom.setError("Nom requis !")
            return false
    }
        if(prenom.getText().toString().isEmpty()){
            prenom.setError("Prénom requis !")
            return false
        }

        if(adresse.getText().toString().isEmpty()){
            adresse.setError("Adresse requise !")
            return false
        }

        if(telephone.getText().toString().isEmpty()){
            telephone.setError("Téléphone requis !")
            return false
        }
        if(livraison.getText().toString().isEmpty()){
            livraison.setError("Heure requise !")
            return false
        }

        val sharedPreferenceNom = getSharedPreferences("preferenceNom",Context.MODE_PRIVATE)
        val sharedPreferencesPrenom = getSharedPreferences("preferencePrenom",Context.MODE_PRIVATE)
        val editNom = sharedPreferenceNom.edit()
        val editPrenom = sharedPreferencesPrenom.edit()
        editNom.putString("nom",nom.text.toString())
        editPrenom.putString("prenom",prenom.text.toString())
        editNom.apply()
        editPrenom.apply()


        return true
}

}