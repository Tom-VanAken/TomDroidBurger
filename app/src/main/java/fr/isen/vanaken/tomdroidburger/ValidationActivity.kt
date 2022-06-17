package fr.isen.vanaken.tomdroidburger

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)



        val titre = findViewById<TextView>(R.id.TitreConfirmation)
        val confAddr = findViewById<TextView>(R.id.choixAdresse)
        val confBurger = findViewById<TextView>(R.id.choixBurger)
        val confHeure = findViewById<TextView>(R.id.choixHeure)


        val sharedPreferenceNom = getSharedPreferences("preferenceNom", Context.MODE_PRIVATE)
        val sharedPreferencesPrenom = getSharedPreferences("preferencePrenom", Context.MODE_PRIVATE)
        val affNom = sharedPreferenceNom.getString("nom","No imported")
        val affPrenom = sharedPreferencesPrenom.getString("prenom","No imported")

        var adresse = ""
        var burger = ""
        var heure = ""
        var addrMail = "marc.mollinari@gmail.com"
        var confCommande = "Conirmation commande"
        var confCommandeText = "Votre commande a bien été enregistrée"


        if(intent.hasExtra("adresse")){
            adresse = intent.getStringExtra("adresse").toString()
            confAddr.text = "Votre adresse : $adresse"
        }
        if(intent.hasExtra("burger")){
            burger = intent.getStringExtra("burger").toString()
            confBurger.text = "Choix du burger : $burger"
        }
        if(intent.hasExtra("heure")){
            heure = intent.getStringExtra("heure").toString()
            confHeure.text = "Heure de livraison : $heure"
        }
        titre.text = "Merci pour votre commande $affNom $affPrenom"


        val button = findViewById<Button>(R.id.buttonMail)

        button.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_EMAIL,"mar.mollinari@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT,confCommande)
                putExtra(Intent.EXTRA_TEXT,confCommandeText)
            }
            startActivity(intent)
        }

    }
}