package com.ramadan.hiltplayground.demo.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ramadan.hiltplayground.demo.domain.Blog
import com.ramadan.hiltplayground.demo.repository.BlogRepository
import com.ramadan.hiltplayground.demo.util.DataState
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BlogViewmodel @ViewModelInject constructor(
    private val blogRepository: BlogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _dataState  : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val dataState : LiveData<DataState<List<Blog>>>
    get() = _dataState

    fun setStateEvent(blogStateEvent: BlogStateEvent){
        viewModelScope.launch {
            when(blogStateEvent){
                is BlogStateEvent.GetBlogEvents ->
                    blogRepository.getBlogs().onEach {dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
            }
        }
    }
}

sealed class BlogStateEvent{
    object GetBlogEvents : BlogStateEvent()
    object Non : BlogStateEvent()
}