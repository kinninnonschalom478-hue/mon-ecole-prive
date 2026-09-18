package com.monecole.parent

import android.os.Bundle
import android.graphics.Color
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(32, 40, 32, 32)
        root.setBackgroundColor(Color.WHITE)

        fun label(text: String, size: Float): TextView {
            return TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(Color.rgb(20,30,45))
                setPadding(0, 12, 0, 12)
            }
        }

        root.addView(label("MON ÉCOLE", 30f))

        root.addView(
            label(
                "Un enfant qu'on enseigne est un homme qu'on gagne.",
                18f
            )
        )

        root.addView(
            label(
                "Suivez votre enfant en toute sécurité.",
                17f
            )
        )

        val login = Button(this)
        login.text = "SE CONNECTER"
        root.addView(login)

        root.addView(label("👨‍👩‍👧 Suivre mon enfant", 22f))

        root.addView(
            label(
                "Résultats\n" +
                "Absences et retards\n" +
                "Conduite\n" +
                "Devoirs\n" +
                "Emploi du temps\n" +
                "Documents\n" +
                "Notifications\n" +
                "Communication avec l'école",
                16f
            )
        )

        root.addView(label("🔒 Sécurité", 22f))

        root.addView(
            label(
                "Les informations scolaires sont personnelles. " +
                "L'accès est réservé aux responsables autorisés.",
                15f
            )
        )

        setContentView(root)
    }
}
