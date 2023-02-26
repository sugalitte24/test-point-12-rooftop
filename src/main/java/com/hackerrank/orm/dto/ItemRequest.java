package com.hackerrank.orm.dto;

import com.hackerrank.orm.enums.ItemStatus;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class ItemRequest {
    private String itemName;
    private String itemEnteredByUser;
    private Timestamp itemEnteredDate;
    private double itemBuyingPrice;
    private double itemSellingPrice;
    private final Date itemLastModifiedDate = new Date();
    private String itemLastModifiedByUser;
    private final ItemStatus itemStatus = ItemStatus.AVAILABLE;
}
