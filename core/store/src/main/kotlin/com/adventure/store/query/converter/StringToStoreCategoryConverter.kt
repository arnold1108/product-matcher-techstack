package com.adventure.store.query.converter

import com.adventure.apis.store.State.StoreCategory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.stereotype.Component

@ReadingConverter
@Component
class StringToStoreCategoryConverter : Converter<String, StoreCategory> {
    override fun convert(source: String): StoreCategory {
        return StoreCategory.valueOf(source)
    }
}