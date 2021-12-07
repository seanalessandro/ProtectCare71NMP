package id.ac.ubaya.a160419026_week12

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQUEST_SELECT_CONTACT = 1
    val REQUEST_SELECT_TEMPLATE = 2
    val REQUEST_IMAGE_CAPTURE = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data =
                Uri.parse("https://api.whatsapp.com/send?phone=" +
                        txtHP.text.toString() + "&text=" +
                        txtMessage.text.toString())
            val shareIntent = Intent.createChooser(sendIntent,
                "Send My Text")
            startActivity(shareIntent)

        }

        textInputLayoutHP.setEndIconOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        }

        btnPickTemplate.setOnClickListener {
            val intent = Intent(this,MessageTemplateActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_TEMPLATE)
        }

        fabPhoto.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_IMAGE_CAPTURE)
            } else {
                takePicture()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_SELECT_CONTACT){
                val contactUri = data?.data
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val cursor =
                    contactUri?.let { contentResolver.query(it, projection, null, null, null) }
               if(cursor != null && cursor.moveToFirst()){
                   val hp = cursor.getString(0)
                   txtHP.setText(hp)
               }
            }
            else if(requestCode == REQUEST_SELECT_TEMPLATE) {
                val message = data?.getStringExtra(MessageTemplateActivity.TEMPLATE_MESSAGE)
                txtMessage.setText(message)
            }
            else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val extras = data!!.extras
                val imageBitmap: Bitmap = extras!!.get("data") as Bitmap
                imgPhoto.setImageBitmap(imageBitmap)
            }

        }
    }

    fun takePicture() {
        val i = Intent()
        i.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when(requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        takePicture()
                    else
                        Toast.makeText(this, "You must grant permission to access the camera.", Toast.LENGTH_LONG).show()
                }
            }

    }

}