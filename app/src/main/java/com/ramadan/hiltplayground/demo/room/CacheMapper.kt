package com.ramadan.hiltplayground.demo.room

import com.ramadan.hiltplayground.demo.domain.Blog
import com.ramadan.hiltplayground.demo.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            image = entity.image,
            body = entity.body,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            image = domainModel.image,
            body = domainModel.body,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities:List<BlogCacheEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}