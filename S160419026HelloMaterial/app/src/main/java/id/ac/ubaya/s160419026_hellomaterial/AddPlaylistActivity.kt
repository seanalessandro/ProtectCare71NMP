package id.ac.ubaya.s160419026_hellomaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_add_playlist.*

class AddPlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_playlist)

        btnCancel.setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            var title = txtTitle.text.toString()
            var subtitle = txtSubtitle.text.toString()
            var desc = txtDesc.text.toString()
            var img_url = txtURL.text.toString()

            var queue = Volley.newRequestQueue(this)
            var url = "http://ubaya.fun/native/160419026/insert_data.php"
            val stringRequest = object : StringRequest(
                Method.POST,
                url,
                Response.Listener {
                    Log.d("peekparams",it)
                    Toast.makeText(this, "Insert musik baru berhasil", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {
                    Log.d("peekerror",it.message.toString())
                    Toast.makeText(this, "Insert musik baru gagal", Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["title"] = title
                    params["subtitle"] = subtitle
                    params["description"] = desc
                    params["image_url"] = img_url

                    return params
                }
            }
            queue.add(stringRequest)
            finish()
        }
    }
}