//package com.example.restaurantsystem.mappertest;
//
//import com.example.restaurantsystem.dto.ReservationDto;
//import com.example.restaurantsystem.entity.Reservation;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//class ReservationMapperTest {
//
//    private final ReservationMapper reservationMapper = new ReservationMapperImpl();
//    @Test
//    void toReservationDto() {
//        //given
//        Reservation reservation = new Reservation(null,null, LocalDateTime.now(),null,10.0);
//        ReservationDto expected = new ReservationDto(null,LocalDateTime.now(),null,10.0);
//        //when
//        ReservationDto actual = reservationMapper.toReservationDto(reservation);
//        //then
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    void toReservationEntity() {
//        //given
//        ReservationDto reservationDto = new ReservationDto(null, LocalDateTime.of(2002,12,12,12,45,45),null,10.0);
//        Reservation expected = new Reservation(null,null, LocalDateTime.of(2002,12,12,12,45,45),null,10.0);
//
//        //when
//        Reservation actual = reservationMapper.toReservationEntity(reservationDto);
//        //then
//        Assertions.assertThat(actual).isEqualTo(expected);
//    }
//}