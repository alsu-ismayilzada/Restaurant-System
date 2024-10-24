package com.example.restaurantsystem.groovy.service.impl

import com.example.restaurantsystem.config.StatemachineEngine
import com.example.restaurantsystem.dto.response.OrderResponse
import com.example.restaurantsystem.entity.Order
import com.example.restaurantsystem.mapper.OrderMapper
import com.example.restaurantsystem.repository.OrderRepository
import com.example.restaurantsystem.service.ItemInfoService
import com.example.restaurantsystem.service.ItemService
import com.example.restaurantsystem.service.impl.OrderServiceImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class OrderServiceTest extends  Specification{

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private OrderRepository orderRepository
    private OrderMapper orderMapper
    private OrderServiceImpl orderService
    private StatemachineEngine stateMachineService
    private ItemService itemService
    private ItemInfoService itemInfoService

    def setup() {
        orderRepository = Mock()
        orderMapper = Mock()
        stateMachineService = Mock()
        itemService = Mock()
        itemInfoService = Mock()
        orderService = new OrderServiceImpl(orderRepository,orderMapper,stateMachineService,itemService,itemInfoService)
    }

    def "TestGetById success" () {
        given:
         def id = random.nextObject(Integer)
         def entity = random.nextObject(Order)
         def dto = random.nextObject(OrderResponse)

        when:
        def result = orderService.getById(id)

        then:
         1 * orderRepository.findById(id) >> Optional.of(entity)
         1 * orderMapper.toOrderDto(entity) >> dto
         result != null
         result == dto
    }

    def "TestAddOrder success" () {
        given:
        def orderDto = random.nextObject(OrderResponse)
        def orderEntity = random.nextObject(Order)

        and:
        orderMapper.toOrderEntity(orderDto) >> orderEntity

        when:
        orderService.addOrder(orderDto)

        then:
        1 * orderRepository.save(orderEntity)
    }
}
