package com.example.restaurantsystem.groovy.mapper


import com.example.restaurantsystem.dto.response.OrderResponse
import com.example.restaurantsystem.entity.Order
import com.example.restaurantsystem.mapper.OrderMapper
import com.example.restaurantsystem.mapper.OrderMapperImpl
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class OrderMapperTest extends Specification{

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    OrderMapper orderMapper = new OrderMapperImpl()

    def "TestToOrderDto"(){
        given:
         def order = random.nextObject(Order)

        when:
         def orderDto = orderMapper.toOrderDto(order)

        then:
         orderDto != null
         orderDto.getItem() == order.getItem()
         orderDto.getUser() == order.getUser()
         orderDto.getBill() == order.getBill()
         orderDto.getDate() == order.getDate()
    }

    def "TestToOrderEntity"(){
        given:
         def orderDto = random.nextObject(OrderResponse)

        when:
         def order = orderMapper.toOrderEntity(orderDto)

        then:        order != null
        orderDto.getItem() == order.getItem()
        orderDto.getUser() == order.getUser()
        orderDto.getBill() == order.getBill()
        orderDto.getDate() == order.getDate()
        }


}
