package com.example.camara

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    val FOTO:Int=1
    val VIDEO:Int=2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            sacarFoto()
        }

        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
            grabarVideo()
        }

        findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
            findViewById<VideoView>(R.id.videoView)?.start()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sacarFoto(){
        val fotoIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (fotoIntent.resolveActivity(packageManager)!=null){
            startActivityForResult(fotoIntent,FOTO)
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    fun grabarVideo(){
        val videoIntent=Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (videoIntent.resolveActivity(packageManager)!=null){
            startActivityForResult(videoIntent,VIDEO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            FOTO->{
                if(resultCode== RESULT_OK) {
                    var imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
                    findViewById<ImageView>(R.id.imageView).setImageBitmap(imageBitmap)
                }
                else{
                    Toast.makeText(this,"No se ha podido cargar la foto",Toast.LENGTH_SHORT).show()
                }
            }
            VIDEO->{
                if(resultCode== RESULT_OK) {
                    var videoUri: Uri = data?.extras?.get("data") as Uri
                    findViewById<VideoView>(R.id.videoView).setVideoURI(videoUri)
                }
                else{
                    Toast.makeText(this,"No se ha podido grabar el video",Toast.LENGTH_SHORT).show()
                }
            }
    }
    }
}