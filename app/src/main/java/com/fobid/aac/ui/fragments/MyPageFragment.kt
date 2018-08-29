package com.fobid.aac.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fobid.aac.R
import com.fobid.aac.viewmodels.MyPageViewModel
import kotlinx.android.synthetic.main.f_my_page.*

class MyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.f_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(MyPageViewModel::class.java)

        lifecycle.addObserver(viewModel)

        viewModel.name()
                .observe(this, Observer { name ->
                    Log.d("MyPageFragment", "name: ")
                    this.name.text = name
                })

        viewModel.email()
                .observe(this, Observer { email ->
                    this.email.text = email
                })
    }
}