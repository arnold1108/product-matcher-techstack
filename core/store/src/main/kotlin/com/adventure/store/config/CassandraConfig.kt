package com.adventure.store.config

import com.adventure.store.query.converter.StoreCategoryToStringConverter
import com.adventure.store.query.converter.StringToStoreCategoryConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.core.convert.CassandraConverter
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext

@Configuration
class CassandraConfig : AbstractCassandraConfiguration() {

    @Value("\${spring.application.keyspace-name}")
    private lateinit var keySpaceName: String

    @Autowired
    lateinit var storeCategoryToStringConverter: StoreCategoryToStringConverter

    @Autowired
    lateinit var stringToStoreCategoryConverter: StringToStoreCategoryConverter

    override fun getKeyspaceName(): String {
        return keySpaceName
    }

    override fun customConversions(): CassandraCustomConversions {
        val converters = listOf(storeCategoryToStringConverter, stringToStoreCategoryConverter)
        return CassandraCustomConversions(converters)
    }

    override fun cassandraConverter(): CassandraConverter {
        val mappingContext = CassandraMappingContext()
        return MappingCassandraConverter(mappingContext)
    }
}