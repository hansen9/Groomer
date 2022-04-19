package com.example.groomer

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.groomer.database.DatabaseContract
import com.example.groomer.entity.Review
import com.example.groomer.database.ReviewHelper
import kotlinx.android.synthetic.main.activity_review_add_update.*

class ReviewAddUpdateActivity : AppCompatActivity(), View.OnClickListener {
    private var isEdit = false
    private var reviews: Review? = null
    private var position: Int = 0
    private lateinit var reviewHelpers: ReviewHelper

    companion object{
        const val EXTRA_NOTE = "extra_review"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_add_update)

        reviewHelpers = ReviewHelper.getInstance(applicationContext)
        reviews = intent.getParcelableExtra(EXTRA_NOTE)

        if (reviews != null){
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            reviews = Review()
        }

        val actionBarTitle: String
        val btnTitle: String

        if(isEdit){
            actionBarTitle = "Ubah"
            btnTitle = "Update"
            reviews?.let{
                edt_pemilik.setText(it.nama_pemilik)
                edt_komentar.setText(it.komentar)
            }
        } else {
            actionBarTitle = "Tambahkan"
            btnTitle = "Simpan"
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_submit.text = btnTitle
        btn_submit.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_submit){
            val nama = edt_pemilik.text.toString().trim()
            val komen = edt_komentar.text.toString().trim()
            if(nama.isEmpty()){
                edt_pemilik.error = "Field tidak boleh kosong"
                return
            }
            reviews?.nama_pemilik = nama
            reviews?.komentar = komen

            val intent = Intent()
            intent.putExtra(EXTRA_NOTE, reviews)
            intent.putExtra(EXTRA_POSITION, position)

            val values = ContentValues()
            values.put(DatabaseContract.ReviewColumns.Pemilik, nama)
            values.put(DatabaseContract.ReviewColumns.Komentar, komen)

            if (isEdit){
                val result = reviewHelpers.update(reviews?.id.toString(), values).toLong()
                if(result>0){
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    Toast.makeText(this@ReviewAddUpdateActivity, "Gagal mengupdate data", Toast.LENGTH_SHORT).show()
                }
            } else {
                val result = reviewHelpers.insert(values)
                if(result>0){
                    reviews?.id = result.toInt()
                    setResult(RESULT_ADD, intent)
                    finish()
                } else{
                    Toast.makeText(this@ReviewAddUpdateActivity, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit){
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int){
        val isDialogClose  = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if(isDialogClose){
            dialogTitle = "Batal"
            dialogMessage = "Apakah anda ingin membatalkan perubahan?"
        } else{
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?"
            dialogTitle = "Hapus review"
        }
        val alertDialogBuilder =  AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder.setMessage(dialogMessage).setCancelable(false).setPositiveButton("Ya")
        { dialog, id->
            if(isDialogClose){
                finish()
            } else {
                val result = reviewHelpers.deleteById(reviews?.id.toString()).toLong()
                if(result>0){
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                    finish()
                } else {
                    Toast.makeText(this@ReviewAddUpdateActivity,"Gagal menghapus data", Toast.LENGTH_SHORT).show()
                }
            }
        }
        .setNegativeButton("Tidak"){ dialog, id -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}