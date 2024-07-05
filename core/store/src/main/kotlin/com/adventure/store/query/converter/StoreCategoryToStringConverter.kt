package com.adventure.store.query.converter

import com.adventure.apis.store.State.StoreCategory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component

@WritingConverter
@Component
class StoreCategoryToStringConverter : Converter<StoreCategory, String> {
    override fun convert(source: StoreCategory): String {
        return source.name
    }
}

