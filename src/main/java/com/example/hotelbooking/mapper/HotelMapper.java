package com.example.hotelbooking.mapper;

import com.example.hotelbooking.dto.HotelDto;
import com.example.hotelbooking.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelDto toDto(Hotel hotel);
    Hotel toEntity(HotelDto hotelDto);
    List<HotelDto> toDtoList(List<Hotel> hotels);

    void updateEntityFromDto(HotelDto dto, @MappingTarget Hotel entity);
}