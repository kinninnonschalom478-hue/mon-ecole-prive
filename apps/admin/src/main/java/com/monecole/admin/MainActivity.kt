package com.monecole.admin

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
                setPadding(0, 10, 0, 10)
            }
        }

        root.addView(label("MON ÉCOLE ADMIN", 30f))
        root.addView(label("Amour • Travail • Succès", 16f))
        root.addView(label("Administration officielle", 18f))

        root.addView(label("DIRECTION", 21f))
        root.addView(
            label(
                "Élèves\nClasses\nEnseignants\nParents\nAnnées scolaires\nComptes\nPermissions\nStatistiques",
                16f
            )
        )

        root.addView(label("SCOLARITÉ", 21f))
        root.addView(
            label(
                "Notes\nMoyennes\nAbsences\nRetards\nConduite\nAppréciations\nDevoirs\nEmplois du temps\nBulletins",
                16f
            )
        )

        root.addView(label("COMMUNICATION", 21f))
        root.addView(
            label(
                "Actualités\nÉvénements\nPhotos\nPrésentation\nProspectus\nTarifs\nContacts",
                16f
            )
        )

        root.addView(label("INTELLIGENCE ARTIFICIELLE", 21f))
        root.addView(
            label(
                "Import photo/texte\nRecherche intelligente\nAnalyse des résultats\nAnalyse des absences\nAssistant administratif\nValidation humaine",
                16f
            )
        )

        root.addView(label("AUDIT", 21f))
        root.addView(
            label(
                "Connexions\nModifications\nCréations\nSuppressions\nAppareils autorisés\nActions sensibles",
                16f
            )
        )

        setContentView(root)
    }
}
