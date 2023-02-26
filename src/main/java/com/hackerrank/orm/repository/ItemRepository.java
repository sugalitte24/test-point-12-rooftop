package com.hackerrank.orm.repository;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Boolean existsByItemName( String itemName );

    List<Item> findByItemStatusAndItemEnteredByUser( ItemStatus itemStatus, String itemEnteredByUser );
}
