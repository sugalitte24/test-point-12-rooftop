package com.hackerrank.orm.service;

import com.hackerrank.orm.dto.ItemDto;
import com.hackerrank.orm.dto.ItemRequest;
import com.hackerrank.orm.enums.ItemStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemInterface {
    List<ItemDto> findAll();

    ItemDto findById( Long id );

    ItemDto save( ItemRequest request );

    ItemDto update( Long id, ItemRequest request );

    void deleteById( Long id );

    void deleteAll();

    List<ItemDto> findByStatusAndUser( ItemStatus itemStatus, String itemEnteredByUser );

    Page<ItemDto> findAllPage( Pageable page );
}
