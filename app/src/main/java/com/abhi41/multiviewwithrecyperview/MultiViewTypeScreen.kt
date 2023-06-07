package com.abhi41.multiviewwithrecyperview

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi41.multiviewwithrecyperview.databinding.ActivityMultiViewTypeScreenBinding
import com.abhi41.multiviewwithrecyperview.model.DataItem
import com.abhi41.multiviewwithrecyperview.ui.adapter.DashBoardAdapter
import com.abhi41.multiviewwithrecyperview.ui.screen.DashBoardViewModel


class MultiViewTypeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMultiViewTypeScreenBinding
    private lateinit var viewModel: DashBoardViewModel
    var mRecyclerViewList = mutableListOf<DataItem>()
    lateinit var adapter:DashBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiViewTypeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        viewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]
        adapter = DashBoardAdapter(mRecyclerViewList,applicationContext)
        setObserver()
        initView()
    }

    private fun initView() {
        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvMain.adapter = adapter
    }

    private fun setObserver() {
        viewModel.dashBoardListData.observe(this) { listData ->
            mRecyclerViewList.addAll(listData)
            adapter.notifyDataSetChanged()
        }
    }

}