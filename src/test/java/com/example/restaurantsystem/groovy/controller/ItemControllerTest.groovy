package com.example.restaurantsystem.groovy.controller

import com.example.restaurantsystem.controller.ItemController
import com.example.restaurantsystem.dto.request.ItemRequest
import com.example.restaurantsystem.dto.response.ItemResponse
import com.example.restaurantsystem.service.ItemService
import com.example.restaurantsystem.service.impl.ItemServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class ItemControllerTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private ItemServiceImpl itemService
    private MockMvc mockMvc
    private ItemController itemController

    def setup(){
        itemService = Mock()
        itemController = new ItemController(itemService)
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .build()
    }

    def "AddItem"() {
//        given:
//        def url = "/api/item"
//        def request = random.nextObject(ItemRequest)
//
//        when:
//        def result = mockMvc.perform(post(url)
//                .contentType("application/json")
//                .content("""
//                {
//                    "name": "${request.name}",
//                    "price": ${request.price},
//                    "itemType": "${request.itemType}",
//                    "photo": "${request.photo}"
//                }
//                """))
//                .andReturn()
//
//        then:
//        1* itemService.addItem(request)
//        def response = result.response
//        response.status == 200
    }

    def "DeleteById"() {
    }

    def "GetById"() {
//        given:
//        def itemResponse = random.nextObject(ItemResponse)
//        def id = itemResponse.getId()
//        def url = "/api/item/$id"
//
////        def expectedResponse = [
////                    "id": 1,
////                    "name": itemResponse.name,
////                    "photo": itemResponse.photo,
////                    "price": itemResponse.price,
////                    "itemType": itemResponse.itemType ]
//
//        when:
//        def result = mockMvc.perform(get(url)).andReturn()
//
//        then:
//        1* itemService.getById(id) >> itemResponse
//        result.response.status == 200
    }

    def "GetAll"() {
    }

    def "UpdateItem"() {
    }
}
