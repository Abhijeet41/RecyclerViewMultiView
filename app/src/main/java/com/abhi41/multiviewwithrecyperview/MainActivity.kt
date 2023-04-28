package com.abhi41.multiviewwithrecyperview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi41.multiviewwithrecyperview.adapter.ParentAdapter
import com.abhi41.multiviewwithrecyperview.databinding.ActivityMainBinding
import com.abhi41.multiviewwithrecyperview.model.ChildModel
import com.abhi41.multiviewwithrecyperview.model.ParentModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val parentList = ArrayList<ParentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.rvParent.setHasFixedSize(true)
        binding.rvParent.layoutManager = LinearLayoutManager(applicationContext)


        addDataToList()
        val adapter = ParentAdapter(parentList)
        binding.rvParent.adapter = adapter
    }

    private fun addDataToList() {

        val childIntem1 = ArrayList<ChildModel>()
        childIntem1.add(ChildModel("C", R.drawable.c))
        childIntem1.add(ChildModel("C#", R.drawable.csharp))
        childIntem1.add(ChildModel("Java", R.drawable.java))
        parentList.add(ParentModel("Game Development",R.drawable.console,childIntem1))

        val childItem2 = ArrayList<ChildModel>()
        childItem2.add(ChildModel("Kotlin", R.drawable.kotlin))
        childItem2.add(ChildModel("XML", R.drawable.xml))
        childItem2.add(ChildModel("Java", R.drawable.java))
        parentList.add(
            ParentModel(
                "Android Development",
                R.drawable.android,
                childItem2
            )
        )

        val childItem3 = ArrayList<ChildModel>()
        childItem3.add(ChildModel("JavaScript", R.drawable.javascript))
        childItem3.add(ChildModel("HTML", R.drawable.html))
        childItem3.add(ChildModel("CSS", R.drawable.css))
        parentList.add(
            ParentModel(
                "Front End Web",
                R.drawable.front_end,
                childItem3
            )
        )

        val childItem4 = ArrayList<ChildModel>()
        childItem4.add(ChildModel("Julia", R.drawable.julia))
        childItem4.add(ChildModel("Python", R.drawable.python))
        childItem4.add(ChildModel("R", R.drawable.r))
        parentList.add(
            ParentModel(
                "Artificial Intelligence",
                R.drawable.ai,
                childItem4
            )
        )

        val childItem5 = ArrayList<ChildModel>()
        childItem5.add(ChildModel("Java", R.drawable.java))
        childItem5.add(ChildModel("Python", R.drawable.python))
        childItem5.add(ChildModel("PHP", R.drawable.php))
        childItem5.add(ChildModel("JavaScript", R.drawable.javascript))
        parentList.add(
            ParentModel(
                "Back End Web",
                R.drawable.backend,
                childItem5
            )
        )
    }
}