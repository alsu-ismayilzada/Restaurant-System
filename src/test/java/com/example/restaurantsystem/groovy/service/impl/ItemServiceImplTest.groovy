package com.example.restaurantsystem.groovy.service.impl

import com.example.restaurantsystem.dto.request.ItemRequest
import com.example.restaurantsystem.dto.response.ItemResponse
import com.example.restaurantsystem.entity.Item
import com.example.restaurantsystem.mapper.ItemMapper
import com.example.restaurantsystem.repository.ItemRepository
import com.example.restaurantsystem.service.ItemService
import com.example.restaurantsystem.service.impl.ItemServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class ItemServiceImplTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    private ItemRepository itemRepository

    private ItemService itemService

    private ItemMapper itemMapper

    def setup(){
        itemRepository = Mock()
        itemMapper = Mock()
        itemService = new ItemServiceImpl(itemRepository, itemMapper)
    }

    def "AddItem"() {
        given:
        def itemRequest = random.nextObject(ItemRequest)
        def itemEntity = random.nextObject(Item)

        when:
        itemService.addItem(itemRequest)

        then:
        1* itemMapper.toItemEntity(itemRequest) >> itemEntity
        1* itemRepository.save(itemEntity)
    }

    def "DeleteById"() {
        given:
        def id = random.nextObject(Long)

        when:
        itemService.deleteById(id)

        then:
        1* itemRepository.deleteById(id)
    }

    def "GetById success"() {
        given:
        def id = random.nextObject(Long)
        def itemResponse = random.nextObject(ItemResponse)
        def itemEntity = random.nextObject(Item)

        when:
        itemService.findById(id)

        then:
        itemMapper.toItemDto(itemEntity) >> itemResponse
        1*itemRepository.findById(id) >> Optional.of(itemEntity)

    }

    def "GetById Not Found"() {
        given:
        def id = random.nextObject(Long)

        when:
        itemService.findById(id)

        then:
        1* itemRepository.findById(id) >> Optional.empty()

        ResponseStatusException ex = thrown()
        ex.reason == "Item Not Found"

    }

    def "GetAll success"() {
        given:
        Pageable pageable = Mock(Pageable)
        List<Item> items = [random.nextObject(Item), random.nextObject(Item)]
        List<ItemResponse> itemResponses = items.collect { EnhancedRandom.random(ItemResponse) }
        Page<Item> itemPage = Mock(Page)

        when:
        itemService.getAll(pageable)
        itemPage.getContent() >> items
        itemMapper.toItemDto(_) >> { item -> itemResponses[items.indexOf(item)] }

        then:
        1*itemRepository.findAll(pageable) >> itemPage
    }

    def "UpdateById"() {
        given:
        def id = random.nextObject(Long)
        def item = random.nextObject(Item)
        def itemRequest = random.nextObject(ItemRequest)
        def itemResponse = random.nextObject(ItemResponse)

        when:
        itemService.updateById(id, itemRequest)
        itemMapper.updateItem(item, itemRequest) >> {}
        itemMapper.toItemDto(item) >> itemResponse

        then:
        1* itemRepository.findById(id) >> Optional.of(item)
    }

    def "FindById"() {
        given:
        def id = random.nextObject(Long)
        def item = random.nextObject(Item)

        when:
        itemService.findById(id)

        then:
        1* itemRepository.findById(id) >> Optional.of(item)

    }

    def "FindAllById"() {
        given:
        List<Long> ids = [1L, 2L]
        List<Item> items = [random.nextObject(Item), random.nextObject(Item)]

        when:
        itemService.findAllById(ids)

        then:
        1* itemRepository.findAllById {ids} >> items

    }
}
