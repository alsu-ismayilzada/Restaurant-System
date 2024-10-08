package com.example.restaurantsystem.groovy.service

import com.example.restaurantsystem.dto.OrderDto
import com.example.restaurantsystem.entity.Order
import com.example.restaurantsystem.mapper.OrderMapper
import com.example.restaurantsystem.repository.OrderRepository
import com.example.restaurantsystem.service.OrderService
import com.example.restaurantsystem.service.impl.OrderManager
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class OrderServiceTest extends  Specification{

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private OrderRepository orderRepository
    private OrderMapper orderMapper
    private OrderService orderService

    def setup() {
        orderRepository = Mock()
        orderMapper = Mock()
        orderService = new OrderManager(orderRepository,orderMapper)
    }

    def "TestGetById success" () {
        given:
         def id = random.nextObject(Integer)
         def entity = random.nextObject(Order)
         def dto = random.nextObject(OrderDto)

        when:
        def result = orderService.getById(id)

        then:
         1 * orderRepository.findById(id) >> Optional.of(entity)
         1 * orderMapper.toOrderDto(entity) >> dto
         result == dto
    }

    def "TestAddOrder success" () {
        given:
        def orderDto = random.nextObject(OrderDto)
        def orderEntity = random.nextObject(Order)

        and:
        orderMapper.toOrderEntity(orderDto) >> orderEntity

        when:
        orderService.addOrder(orderDto)

        then:
        1 * orderRepository.save(orderEntity)
    }
}
