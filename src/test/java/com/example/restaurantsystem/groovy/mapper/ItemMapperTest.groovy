package com.example.restaurantsystem.groovy.mapper

import com.example.restaurantsystem.dto.request.ItemRequest
import com.example.restaurantsystem.entity.Item
import com.example.restaurantsystem.mapper.ItemMapper
import com.example.restaurantsystem.mapper.ItemMapperImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ItemMapperTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    private ItemMapper itemMapper

    def setup(){
        itemMapper = new ItemMapperImpl()
    }

    def "ToItemDto"() {
        given:
        def item = random.nextObject(Item)

        when:
        def response = itemMapper.toItemDto(item)

        then:
        item.id == response.id
        item.name == response.name
        item.photo == response.photo
        item.price == response.price
        item.itemType == response.itemType
    }

    def "ToItemEntity"() {
        given:
        def request = random.nextObject(ItemRequest)

        when:
        def item = itemMapper.toItemEntity(request)

        then:
        item.name == request.name
        item.photo == request.photo
        item.price == request.price
        item.itemType == request.itemType
    }

    def "UpdateItem"() {
        given:
        def item = random.nextObject(Item)
        def request = random.nextObject(ItemRequest)

        when:
        itemMapper.updateItem(item, request)

        then:
        request.photo == item.photo
        request.name == item.name
        request.price == item.price
        request.itemType == item.itemType
    }
}
