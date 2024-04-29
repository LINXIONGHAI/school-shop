package com.itlin.product.service;

import com.itlin.common.util.JsonData;
import com.itlin.product.dto.CartSaveDto;

public interface CartService {
    JsonData add(CartSaveDto cartSaveDto);

    JsonData getCartList();

    JsonData del(String product_id);
}
