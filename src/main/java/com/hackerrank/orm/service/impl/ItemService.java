package com.hackerrank.orm.service.impl;

import com.hackerrank.orm.dto.ItemDto;
import com.hackerrank.orm.dto.ItemRequest;
import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.mapper.ItemMapper;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import com.hackerrank.orm.service.ItemInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class ItemService implements ItemInterface {
    public final ItemRepository itemRepository;

    public final ItemMapper mapper;

    public ItemService( ItemMapper mapper, ItemRepository itemRepository ) {
        this.mapper = mapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> findAll() {
        return mapper.toDtoList(itemRepository.findAll());
    }

    @Override
    public ItemDto findById( Long id ) {
        Optional<Item> exist = itemRepository.findById(id.intValue());
        if (!exist.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item No Exist");
        }
        return mapper.toDtoFromModel(exist.get());
    }

    @Transactional
    public ItemDto save( ItemRequest request ) {
        Item model = mapper.toModel(request);
        Boolean itemExist = itemRepository.existsByItemName(request.getItemName());
        if (itemExist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Exist");
        }
        Item itemEntity = itemRepository.save(model);
        return mapper.toDtoFromModel(itemEntity);
    }

    @Transactional
    public ItemDto update( Long id, ItemRequest request ) {
        Optional<Item> exist = itemRepository.findById(id.intValue());
        if (!exist.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item No Exist");
        }
        mapper.update(request, exist.get());
        return mapper.toDtoFromModel(itemRepository.save(exist.get()));
    }

    @Override
    public void deleteById( Long id ) {
        Optional<Item> exist = itemRepository.findById(id.intValue());
        if (!exist.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item No Exist");
        }
        itemRepository.deleteById(id.intValue());
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    public List<ItemDto> findByStatusAndUser( ItemStatus itemStatus, String itemEnteredByUser ) {
        List<Item> item = itemRepository.findByItemStatusAndItemEnteredByUser(itemStatus, itemEnteredByUser);
        return mapper.toDtoList(item);
    }

    @Override
    public Page<ItemDto> findAllPage( Pageable page ) {
        return itemRepository.findAll(page).map(mapper::toDtoFromModel);
    }
}
