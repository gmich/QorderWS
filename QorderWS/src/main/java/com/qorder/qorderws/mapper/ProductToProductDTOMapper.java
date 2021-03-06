package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.model.product.Product;

public class ProductToProductDTOMapper implements IMapper<Product, ProductDTO> {

	@Override
	public ProductDTO map(Product source, ProductDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPrice(source.getPrice());
		return target;
	}

}
