package com.abhi41.multiviewwithrecyperview.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ActivityAmazonSettingScreenBinding
import com.abhi41.multiviewwithrecyperview.model.DataAccessories
import com.abhi41.multiviewwithrecyperview.model.DataLanguage
import com.abhi41.multiviewwithrecyperview.model.ParentAmazonSetting
import com.abhi41.multiviewwithrecyperview.ui.adapter.ParentAccessoriesAdapter

class AmazonSettingScreen : AppCompatActivity() {

    lateinit var binding: ActivityAmazonSettingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAmazonSettingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSettingMain.setHasFixedSize(true)
        binding.rvSettingMain.layoutManager = LinearLayoutManager(this)

        val parentItemList = mutableListOf<ParentAmazonSetting>()
        //first item
        val childItems1 = mutableListOf<DataLanguage>()
        childItems1.add(DataLanguage("C" , R.drawable.c))
        childItems1.add(DataLanguage("C++" , R.drawable.cplusplus))
        childItems1.add(DataLanguage("Java" , R.drawable.java))
        childItems1.add(DataLanguage("C#" , R.drawable.csharp))
        val parentContent1 = DataAccessories(R.drawable.console , "Game Development" , childItems1)

        val childItems2 = mutableListOf<DataLanguage>()
        childItems2.add(DataLanguage("Kotlin" , R.drawable.kotlin))
        childItems2.add(DataLanguage("XML" , R.drawable.xml))
        childItems2.add(DataLanguage("Java" , R.drawable.java))
        val parentContent2 = DataAccessories(R.drawable.android , "Android Development" , childItems2)

        parentItemList.add(ParentAmazonSetting(parentContent1 , parentContent2))

        val childItems3 = ArrayList<DataLanguage>()
        childItems3.add(DataLanguage("JavaScript", R.drawable.javascript))
        childItems3.add(DataLanguage("HTML", R.drawable.html))
        childItems3.add(DataLanguage("CSS", R.drawable.css))
        val parentContent3 = DataAccessories(R.drawable.front_end, "Front End Web", childItems3)

        val childItems4 = ArrayList<DataLanguage>()
        childItems4.add(DataLanguage("Julia", R.drawable.julia))
        childItems4.add(DataLanguage("Python", R.drawable.python))
        childItems4.add(DataLanguage("R", R.drawable.r))

        val parentContent4 = DataAccessories(R.drawable.ai, "Artificial Intelligence", childItems4)
        parentItemList.add(ParentAmazonSetting(parentContent3, parentContent4))

        val childItems5 = ArrayList<DataLanguage>()
        childItems5.add(DataLanguage("Java", R.drawable.java))
        childItems5.add(DataLanguage("Python", R.drawable.python))
        childItems5.add(DataLanguage("PHP", R.drawable.php))
        childItems5.add(DataLanguage("JavaScript", R.drawable.javascript))

        val parentContent5 = DataAccessories(R.drawable.backend, "Back End Web", childItems5)
        val parentContent6 = DataAccessories(R.drawable.console, "Game", childItems1)
        parentItemList.add(ParentAmazonSetting(parentContent5, parentContent6))


        val parentContent7 = DataAccessories(R.drawable.front_end, "Front End Web", childItems3)
        val parentContent8 = DataAccessories(R.drawable.ai, "Artificial Intelligence", childItems4)

        parentItemList.add(ParentAmazonSetting(parentContent7, parentContent8))

        val adapter = ParentAccessoriesAdapter(applicationContext,parentItemList)
        binding.rvSettingMain.adapter = adapter
    }
}