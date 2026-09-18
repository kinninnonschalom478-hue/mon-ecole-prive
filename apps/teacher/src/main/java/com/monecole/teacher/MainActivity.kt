package com.monecole.teacher

import android.os.Bundle
import android.graphics.Color
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val prefs by lazy { getSharedPreferences("teacher", MODE_PRIVATE) }
    private var token: String? = null
    private var baseUrl = "http://10.0.2.2:3000"
    private lateinit var root: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token = prefs.getString("token", null)
        if (token.isNullOrBlank()) showLogin() else showDashboard()
    }

    private fun text(value: String, size: Float) = TextView(this).apply {
        this.text = value; textSize = size; setTextColor(Color.rgb(20,30,45)); setPadding(0,10,0,10)
    }

    private fun showLogin() {
        root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(32,40,32,32) }
        val email = EditText(this).apply { hint="Email enseignant"; inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS }
        val pass = EditText(this).apply { hint="Mot de passe"; inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD }
        val device = EditText(this).apply { hint="Identifiant appareil"; setText("TEACHER_DEVICE") }
        val login = Button(this).apply { text="SE CONNECTER" }
        val status = text("", 14f)
        root.addView(text("MON ÉCOLE",30f)); root.addView(text("ESPACE ENSEIGNANT",20f))
        root.addView(email); root.addView(pass); root.addView(device); root.addView(login); root.addView(status)
        setContentView(root)
        login.setOnClickListener {
            status.text="Connexion..."
            thread {
                val r=api("/api/auth/login","POST",JSONObject().put("email",email.text.toString()).put("password",pass.text.toString()).put("deviceId",device.text.toString()).toString(),null)
                runOnUiThread {
                    if(r.code in 200..299){
                        val j=JSONObject(r.body); token=j.optString("token",j.optString("accessToken")); prefs.edit().putString("token",token).apply(); showDashboard()
                    } else status.text="Connexion refusée : ${r.body}"
                }
            }
        }
    }

    private fun showDashboard() {
        root=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(24,32,24,24)}
        root.addView(text("👨‍🏫 ENSEIGNANT",28f))
        val info=text("Chargement du contexte enseignant...",15f)
        root.addView(info)
        val classes=Button(this).apply{text="MES CLASSES"}
        val subjects=Button(this).apply{text="MES MATIÈRES"}
        val homework=Button(this).apply{text="CRÉER UN DEVOIR"}
        val logout=Button(this).apply{text="SE DÉCONNECTER"}
        listOf(classes,subjects,homework,logout).forEach(root::addView)
        setContentView(root)
        thread { val r=api("/api/teacher-admin/me","GET",null,token);runOnUiThread{info.text="Contexte :\n${r.body}"} }
        classes.setOnClickListener{load("/api/teacher-admin/classes","Classes autorisées")}
        subjects.setOnClickListener{load("/api/teacher-admin/subjects","Matières — indiquez ensuite class_id")}
        homework.setOnClickListener{homeworkForm()}
        logout.setOnClickListener{prefs.edit().clear().apply();token=null;showLogin()}
    }

    private fun load(path:String,title:String){thread{val r=api(path,"GET",null,token);runOnUiThread{showResult(title,r.body)}}}
    private fun showResult(title:String,body:String){root.removeAllViews();root.addView(text(title,24f));root.addView(text(body,14f));root.addView(Button(this).apply{text="RETOUR";setOnClickListener{showDashboard()}})}

    private fun homeworkForm(){
        root.removeAllViews(); root.addView(text("NOUVEAU DEVOIR",25f))
        val classId=EditText(this).apply{hint="ID classe"}; val subjectId=EditText(this).apply{hint="ID matière"}
        val title=EditText(this).apply{hint="Titre"}; val description=EditText(this).apply{hint="Consignes";minLines=4}
        val due=EditText(this).apply{hint="Date limite ISO 8601";setText("2026-09-25T18:00:00+01:00")}
        val create=Button(this).apply{text="CRÉER LE DEVOIR"}; val status=text("",14f)
        listOf(classId,subjectId,title,description,due,create,status).forEach(root::addView)
        create.setOnClickListener{
            val body=JSONObject().put("class_id",classId.text.toString()).put("subject_id",subjectId.text.toString()).put("title",title.text.toString()).put("description",description.text.toString()).put("due_date",due.text.toString()).toString()
            status.text="Vérification serveur..."
            thread{val r=api("/api/teacher-admin/homework","POST",body,token);runOnUiThread{status.text=if(r.code in 200..299)"Devoir créé. Les notifications sont traitées par le serveur." else "Création refusée : ${r.body}"}}
        }
        root.addView(Button(this).apply{text="RETOUR";setOnClickListener{showDashboard()}})
    }

    data class Resp(val code:Int,val body:String)
    private fun api(path:String,method:String,body:String?,auth:String?):Resp{
        val c=URL(baseUrl+path).openConnection() as HttpURLConnection
        c.requestMethod=method;c.connectTimeout=10000;c.readTimeout=15000;c.setRequestProperty("Content-Type","application/json")
        if(!auth.isNullOrBlank())c.setRequestProperty("Authorization","Bearer $auth")
        if(body!=null){c.doOutput=true;c.outputStream.use{it.write(body.toByteArray())}}
        val s=if(c.responseCode>=400)c.errorStream else c.inputStream
        return Resp(c.responseCode,s?.bufferedReader()?.readText().orEmpty())
    }
}
