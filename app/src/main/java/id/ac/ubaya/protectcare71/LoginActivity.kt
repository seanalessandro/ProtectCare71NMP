package id.ac.ubaya.protectcare71

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.fun/native/160419026/login.php"
            var stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("login", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
//                        val data = obj.getJSONArray("data")
                          val intent = Intent(this, MainActivity::class.java)
                          startActivity(intent)
                    }


                },
                Response.ErrorListener {
                    Log.e("apiresult", it.message.toString())
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = editTextUsername.text.toString()
                    params["password"] = editTextPassword.text.toString()

                    return params
                }
            }
            q.add(stringRequest)
        }
    }
}