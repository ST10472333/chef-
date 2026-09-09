package com.example.masterchefapp2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChefActivity : AppCompatActivity() {

    private lateinit var dishName: EditText
    private lateinit var description: EditText
    private lateinit var course: Spinner
    private lateinit var price: EditText
    private lateinit var addBtn: Button
    private lateinit var menuList: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef)

        dishName = findViewById(R.id.dishName)
        description = findViewById(R.id.description)
        course = findViewById(R.id.course)
        price = findViewById(R.id.price)
        addBtn = findViewById(R.id.addBtn)
        menuList = findViewById(R.id.menuList)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        menuList.adapter = adapter

        val courses = arrayOf("Starter", "Main", "Dessert")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        course.adapter = spinnerAdapter

        addBtn.setOnClickListener {
            val dish = dishName.text.toString().trim()
            val desc = description.text.toString().trim()
            val crs = course.selectedItem.toString()
            val prc = price.text.toString().trim()

            if (dish.isEmpty() || desc.isEmpty() || crs.isEmpty() || prc.isEmpty()) {
                Toast.makeText(this, "⚠️ Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = "🍴 $dish ($crs)\n$desc\n💰 Price: R$prc"
            items.add(item)
            adapter.notifyDataSetChanged()

            dishName.text.clear()
            description.text.clear()
            price.text.clear()
        }
    }
}
