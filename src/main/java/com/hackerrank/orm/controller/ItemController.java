package com.hackerrank.orm.controller;

import com.hackerrank.orm.dto.ItemDto;
import com.hackerrank.orm.dto.ItemRequest;
import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.service.ItemInterface;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/item")
public class ItemController {
    final
    ItemInterface itemInterface;

    public ItemController( ItemInterface itemService ) {
        this.itemInterface = itemService;
    }

    //1. insert POST
    @PostMapping
    ResponseEntity<ItemDto> create( @RequestBody ItemRequest request ) {
        return new ResponseEntity<>(itemInterface.save(request), HttpStatus.CREATED);
    }

    //2. update PUT
    @PutMapping("/{id}")
    ResponseEntity<ItemDto> update( @PathVariable Long id, @RequestBody ItemRequest request ) {
        return new ResponseEntity<>(itemInterface.update(id, request), HttpStatus.OK);
    }

    //3. delete by itemId DELETE
    @DeleteMapping("/{id}")
    void delete( @PathVariable Long id ) {
        itemInterface.deleteById(id);
    }

    //4. delete all DELETE

    @DeleteMapping
    void deleteAll() {
        itemInterface.deleteAll();
    }

    //5. get by itemId GET
    @GetMapping("/{id}")
    ResponseEntity<ItemDto> findById( @PathVariable Long id ) {
        return new ResponseEntity<>(itemInterface.findById(id), HttpStatus.OK);
    }

    //6. get all GET

    @GetMapping
    ResponseEntity<List<ItemDto>> findAll() {
        return new ResponseEntity<>(itemInterface.findAll(), HttpStatus.OK);
    }
    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={modifiedBy} GET

    @GetMapping("/filter")
    ResponseEntity<List<ItemDto>> findByStatusAndUser(
            @RequestParam ItemStatus itemStatus,
            @RequestParam String itemEnteredByUser
    ) {
        return new ResponseEntity<>(itemInterface.findByStatusAndUser(itemStatus, itemEnteredByUser), HttpStatus.OK);
    }

    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET
    @GetMapping("/page")
    ResponseEntity<Page<ItemDto>> findAllPage( Pageable page ) {
        return new ResponseEntity<>(itemInterface.findAllPage(page), HttpStatus.OK);
    }
}
