//package com.example.restaurantsystem.groovy.mapper
//
//
//import com.example.restaurantsystem.dto.response.ItemResponse
//import com.example.restaurantsystem.entity.Item
//import com.example.restaurantsystem.mapper.ItemMapper
//import com.example.restaurantsystem.mapper.ItemMapperImpl
//import io.github.benas.randombeans.EnhancedRandomBuilder
//import io.github.benas.randombeans.api.EnhancedRandom
//import spock.lang.Specification
//
//class ItemMapperTest extends Specification {
//    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
//    ItemMapper itemMapper = new ItemMapperImpl()
//
//   def "TestToItemDto"() {
//       given:
//        def item = random.nextObject(Item)
//
//       when:
//        def actual = itemMapper.toItemDto(item)
//
//       then:
//       actual != null
//       actual.getName() == item.getName()
//       actual.getItemType() == item.getItemType()
//       actual.getPhoto() == item.getPhoto()
//       actual.getPrice() == item.getPrice()
//   }
//
//    def "TestToItemEntity"(){
//        given:
//         def itemDto = random.nextObject(ItemResponse)
//
//        when:
//         def item = itemMapper.toItemEntity(itemDto)
//
//        then:
//        item != null
//        item.getName()== itemDto.getName()
//        item.getItemType() == itemDto.getItemType()
//        item.getPhoto() == itemDto.getPhoto()
//        item.getPrice() == itemDto.getPrice()
//    }
//
//}
