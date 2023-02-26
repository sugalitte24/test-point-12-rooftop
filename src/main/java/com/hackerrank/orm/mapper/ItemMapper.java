package com.hackerrank.orm.mapper;

import com.hackerrank.orm.dto.ItemDto;
import com.hackerrank.orm.dto.ItemRequest;
import com.hackerrank.orm.model.Item;
import java.util.List;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ItemMapper {

    Item toModel( ItemRequest itemRequest );

    ItemDto toDtoFromModel( Item item );

    List<ItemDto> toDtoList( List<Item> itemList );

    void update( ItemRequest request, @MappingTarget Item item );

}
