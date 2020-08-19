package com.ramadan.hiltplayground.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ramadan.hiltplayground.R
import com.ramadan.hiltplayground.demo.domain.Blog
import com.ramadan.hiltplayground.demo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_demo.*
import java.lang.StringBuilder

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {
    private val blogViewmodel: BlogViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        subscribeObservers()
        blogViewmodel.setStateEvent(BlogStateEvent.GetBlogEvents)
    }

    fun subscribeObservers(){
        blogViewmodel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> ->{
                  showProgressBar(false)
                    bindTitles(dataState.data)
                }
                is DataState.Error ->{
                    showProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading ->{
                   showProgressBar(true)
                }
            }
        })
    }

    fun displayError(message: String?){
        if (message !=null)
            textView.text = message
        else
            textView.text = "Unknown error"
    }

    fun showProgressBar(isDisplayed : Boolean){
        progressBar.visibility =  if(isDisplayed) View.VISIBLE else View.GONE
    }

    fun bindTitles(blogs : List<Blog>){
        val sb = StringBuilder()
        blogs.forEach { blog ->
            sb.append(blog.title).append(" \n")
        }
        textView.text = sb.toString()
    }
}