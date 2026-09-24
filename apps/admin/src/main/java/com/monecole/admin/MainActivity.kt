package com.monecole.admin

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var root: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDashboard()
    }

    private fun base(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(247, 249, 252))
            setPadding(dp(18), dp(18), dp(18), dp(24))
        }
    }

    private fun scroll(content: View): ScrollView {
        return ScrollView(this).apply {
            setBackgroundColor(Color.rgb(247, 249, 252))
            addView(content)
        }
    }

    private fun title(text: String, size: Float = 28f): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = size
            setTextColor(Color.rgb(18, 32, 50))
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
            setPadding(0, dp(8), 0, dp(8))
        }
    }

    private fun subtitle(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 15f
            setTextColor(Color.rgb(90, 105, 120))
            setPadding(0, 0, 0, dp(14))
        }
    }

    private fun action(
        text: String,
        description: String,
        onClick: () -> Unit
    ): MaterialCardView {

        val card = MaterialCardView(this).apply {
            radius = dp(16).toFloat()
            cardElevation = dp(2).toFloat()
            setCardBackgroundColor(Color.WHITE)
            setStrokeWidth(dp(1))
            setStrokeColor(Color.rgb(225, 231, 238))
            isClickable = true
            isFocusable = true
            setOnClickListener { onClick() }
        }

        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(16), dp(18), dp(16))
        }

        val t = TextView(this).apply {
            this.text = text
            textSize = 18f
            setTextColor(Color.rgb(18, 32, 50))
            typeface = Typeface.DEFAULT_BOLD
        }

        val d = TextView(this).apply {
            this.text = description
            textSize = 13f
            setTextColor(Color.rgb(100, 112, 125))
            setPadding(0, dp(5), 0, 0)
        }

        box.addView(t)
        box.addView(d)
        card.addView(box)

        return card
    }

    private fun button(text: String, onClick: () -> Unit): MaterialButton {
        return MaterialButton(this).apply {
            this.text = text
            isAllCaps = false
            cornerRadius = dp(12)
            setOnClickListener { onClick() }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(52)
            ).apply {
                setMargins(0, dp(7), 0, dp(7))
            }
        }
    }

    private fun showDashboard() {
        root = base()

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 0, 0, dp(10))
        }

        header.addView(title("MON ÉCOLE"))
        header.addView(subtitle("Administration • Amour • Travail • Succès"))

        root.addView(header)

        val scrollContent = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        addSection(scrollContent, "DIRECTION")
        addCard(scrollContent, "Élèves", "Gérer les élèves de l'établissement") {
            showModule("ÉLÈVES", "Gestion des élèves")
        }
        addCard(scrollContent, "Classes", "Classes, niveaux et affectations") {
            showModule("CLASSES", "Gestion des classes")
        }
        addCard(scrollContent, "Enseignants", "Personnel enseignant et affectations") {
            showModule("ENSEIGNANTS", "Gestion des enseignants")
        }
        addCard(scrollContent, "Parents", "Parents et responsables légaux") {
            showModule("PARENTS", "Gestion des parents")
        }
        addCard(scrollContent, "Années scolaires", "Années et périodes scolaires") {
            showModule("ANNÉES SCOLAIRES", "Gestion des années scolaires")
        }
        addCard(scrollContent, "Comptes & permissions", "Accès, rôles et autorisations") {
            showModule("COMPTES & PERMISSIONS", "Gestion des comptes et permissions")
        }
        addCard(scrollContent, "Statistiques", "Vue générale des indicateurs") {
            showModule("STATISTIQUES", "Tableau des statistiques")
        }

        addSection(scrollContent, "SCOLARITÉ")
        addCard(scrollContent, "Notes", "Saisie et consultation des notes") {
            showModule("NOTES", "Gestion des notes")
        }
        addCard(scrollContent, "Moyennes", "Résultats et moyennes scolaires") {
            showModule("MOYENNES", "Calcul et consultation des moyennes")
        }
        addCard(scrollContent, "Absences", "Présences, absences et justificatifs") {
            showModule("ABSENCES", "Gestion des absences")
        }
        addCard(scrollContent, "Retards", "Suivi des retards") {
            showModule("RETARDS", "Gestion des retards")
        }
        addCard(scrollContent, "Conduite", "Suivi de la conduite des élèves") {
            showModule("CONDUITE", "Suivi de la conduite")
        }
        addCard(scrollContent, "Appréciations", "Observations des enseignants") {
            showModule("APPRÉCIATIONS", "Gestion des appréciations")
        }
        addCard(scrollContent, "Devoirs", "Devoirs et travaux scolaires") {
            showModule("DEVOIRS", "Gestion des devoirs")
        }
        addCard(scrollContent, "Emplois du temps", "Organisation des cours") {
            showModule("EMPLOIS DU TEMPS", "Gestion des emplois du temps")
        }
        addCard(scrollContent, "Bulletins", "Bulletins et résultats scolaires") {
            showModule("BULLETINS", "Gestion des bulletins")
        }

        addSection(scrollContent, "COMMUNICATION")
        addCard(scrollContent, "Actualités", "Journal et informations de l'école") {
            showModule("ACTUALITÉS", "Gestion des actualités")
        }
        addCard(scrollContent, "Événements", "Événements de l'établissement") {
            showModule("ÉVÉNEMENTS", "Gestion des événements")
        }
        addCard(scrollContent, "Photos", "Galerie de l'établissement") {
            showModule("PHOTOS", "Gestion des photos")
        }
        addCard(scrollContent, "Présentation", "Présentation officielle de l'école") {
            showModule("PRÉSENTATION", "Informations de présentation")
        }
        addCard(scrollContent, "Prospectus", "Documents et informations") {
            showModule("PROSPECTUS", "Documents de l'établissement")
        }
        addCard(scrollContent, "Tarifs", "Informations tarifaires") {
            showModule("TARIFS", "Gestion des tarifs")
        }
        addCard(scrollContent, "Contacts", "Coordonnées de l'établissement") {
            showModule("CONTACTS", "Contacts officiels")
        }

        addSection(scrollContent, "INTELLIGENCE ARTIFICIELLE")
        addCard(scrollContent, "Import photo / texte", "Importer des informations pour analyse") {
            showModule("IMPORT", "Importation de données")
        }
        addCard(scrollContent, "Recherche intelligente", "Recherche dans les données") {
            showModule("RECHERCHE", "Recherche intelligente")
        }
        addCard(scrollContent, "Analyse des résultats", "Analyse pédagogique des résultats") {
            showModule("ANALYSE DES RÉSULTATS", "Analyse des résultats scolaires")
        }
        addCard(scrollContent, "Analyse des absences", "Analyse des absences et tendances") {
            showModule("ANALYSE DES ABSENCES", "Analyse des absences")
        }
        addCard(scrollContent, "Assistant administratif", "Outils d'assistance à l'administration") {
            showModule("ASSISTANT ADMINISTRATIF", "Assistant administratif")
        }
        addCard(scrollContent, "Validation humaine", "Contrôle et validation des actions") {
            showModule("VALIDATION HUMAINE", "Validation des opérations")
        }

        addSection(scrollContent, "AUDIT")
        addCard(scrollContent, "Connexions", "Historique des connexions") {
            showModule("CONNEXIONS", "Journal des connexions")
        }
        addCard(scrollContent, "Modifications", "Historique des modifications") {
            showModule("MODIFICATIONS", "Journal des modifications")
        }
        addCard(scrollContent, "Créations", "Historique des créations") {
            showModule("CRÉATIONS", "Journal des créations")
        }
        addCard(scrollContent, "Suppressions", "Historique des suppressions") {
            showModule("SUPPRESSIONS", "Journal des suppressions")
        }
        addCard(scrollContent, "Appareils autorisés", "Appareils et accès autorisés") {
            showModule("APPAREILS AUTORISÉS", "Gestion des appareils")
        }
        addCard(scrollContent, "Actions sensibles", "Opérations nécessitant une surveillance") {
            showModule("ACTIONS SENSIBLES", "Audit des actions sensibles")
        }

        scrollContent.addView(
            button("DÉCONNEXION", {}) {
                finish()
            }
        )

        root.addView(scroll(scrollContent),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(root)
    }

    private fun addSection(parent: LinearLayout, text: String) {
        parent.addView(
            TextView(this).apply {
                this.text = text
                textSize = 13f
                setTextColor(Color.rgb(65, 85, 105))
                typeface = Typeface.DEFAULT_BOLD
                setPadding(dp(2), dp(20), 0, dp(8))
            }
        )
    }

    private fun addCard(
        parent: LinearLayout,
        name: String,
        description: String,
        onClick: () -> Unit
    ) {
        val card = action(name, description, onClick)
        parent.addView(
            card,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, dp(9))
            }
        )
    }

    private fun showModule(name: String, description: String) {
        val content = base()

        content.addView(title(name))
        content.addView(subtitle(description))

        val info = MaterialCardView(this).apply {
            radius = dp(16).toFloat()
            setCardBackgroundColor(Color.WHITE)
            setStrokeWidth(dp(1))
            setStrokeColor(Color.rgb(225, 231, 238))
        }

        val infoBox = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
        }

        infoBox.addView(
            TextView(this).apply {
                text = "Module prêt"
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD
                setTextColor(Color.rgb(18, 32, 50))
            }
        )

        infoBox.addView(
            TextView(this).apply {
                text = "\nCet écran est maintenant réellement navigable.\n\nLes données serveur pourront être branchées ici sans transformer l'application en simple page de texte."
                textSize = 15f
                setTextColor(Color.rgb(80, 95, 110))
            }
        )

        info.addView(infoBox)
        content.addView(info)

        content.addView(
            button("← RETOUR AU TABLEAU DE BORD") {
                showDashboard()
            }
        )

        setContentView(scroll(content))
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()
}
