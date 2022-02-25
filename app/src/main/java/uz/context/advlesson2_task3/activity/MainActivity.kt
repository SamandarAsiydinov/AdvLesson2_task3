package uz.context.advlesson2_task3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.context.advlesson2_task3.R
import uz.context.advlesson2_task3.adapter.MyAdapter
import uz.context.advlesson2_task3.databinding.ActivityMainBinding
import uz.context.advlesson2_task3.manager.PrefsManager
import uz.context.advlesson2_task3.model.Model

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var btnFab: FloatingActionButton
    private lateinit var editName: EditText
    private lateinit var editAge: EditText
    private lateinit var btnSave: MaterialButton
    private val list: ArrayList<Model> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        btnFab = findViewById(R.id.btn_floating)
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(this, list)
        recyclerView.adapter = myAdapter

        btnFab.setOnClickListener {
            showModalSheet()
        }
    }

    private fun showModalSheet() {
        val view: View = layoutInflater.inflate(R.layout.bottom_layout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        btnSave = view.findViewById(R.id.btn_save)
        editAge = view.findViewById(R.id.editAge)
        editName = view.findViewById(R.id.editName)

        btnSave.setOnClickListener {
            val prefsManager = PrefsManager.getInstance(this)
            val name = editName.text.toString().trim()
            val age = editAge.text.toString().trim()
            if (name.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show()
            } else {
                val model = Model()
                model.name = name
                model.age = age
                prefsManager!!.setData("model", Model())
                myAdapter.notifyDataSetChanged()
                list.add(Model())
                dialog.dismissWithAnimation = true
                dialog.dismiss()
            }
        }
    }
}