package com.example.restaurantsystem.groovy.service.impl

import com.example.restaurantsystem.entity.ItemInfo
import com.example.restaurantsystem.repository.ItemInfoRepository
import com.example.restaurantsystem.service.ItemInfoService
import com.example.restaurantsystem.service.impl.ItemInfoServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ItemInfoServiceImplTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ItemInfoService itemInfoService
    private ItemInfoRepository itemInfoRepository

    def setup(){
        itemInfoRepository = Mock()
        itemInfoService = new ItemInfoServiceImpl(itemInfoRepository)
    }

    def "FindItemInfoById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(ItemInfo)

        when:
        def result = itemInfoService.findItemInfoById(id)

        then:
        1* itemInfoRepository.findById(id) >> Optional.of(entity)
        result != null
        result == entity
    }

    def "SaveItemInfo"() {
        given:
        def entity = random.nextObject(ItemInfo)

        when:
        def result = itemInfoService.saveItemInfo(entity)

        then:
        1* itemInfoRepository.save(entity) >> entity
        result != null
        result == entity

    }
}
