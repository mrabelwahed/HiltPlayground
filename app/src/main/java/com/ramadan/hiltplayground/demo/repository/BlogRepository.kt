package com.ramadan.hiltplayground.demo.repository

import com.ramadan.hiltplayground.demo.domain.Blog
import com.ramadan.hiltplayground.demo.retrofit.BlogAPI
import com.ramadan.hiltplayground.demo.retrofit.NetworkMapper
import com.ramadan.hiltplayground.demo.room.BlogDAO
import com.ramadan.hiltplayground.demo.room.CacheMapper
import com.ramadan.hiltplayground.demo.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class BlogRepository  constructor(
    private val blogDAO: BlogDAO,
    private val blogAPI: BlogAPI,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper
) {

    suspend fun getBlogs():Flow<DataState<List<Blog>>> =  flow{
      emit(DataState.Loading)
        delay(1000) // for testing only not production
        try {
            val networkBlogs = blogAPI.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs)
                blogDAO.insert(cacheMapper.mapToEntity(blog))
            val cachedBlogs = blogDAO.getBlogs()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
           emit(DataState.Error(e))
        }
    }


}