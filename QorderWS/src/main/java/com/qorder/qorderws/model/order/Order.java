package com.qorder.qorderws.model.order;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.business.ABusiness;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private long id;

	@Column(name = "TABLE_NUMBER")
	private String tableNumber;

	@Column(name = "DATE_TIME")
	private String dateTime = DateFormat.getDateTimeInstance().format(new Date());

	@OneToMany(targetEntity = ProductHolder.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_ORDER_ID")
	private List<ProductHolder> orderList = new ArrayList<ProductHolder>();

	@ManyToOne(targetEntity = ABusiness.class,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "FK_BUSINESS_ID", nullable = false, updatable = false)
	private ABusiness business;

	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;

	@Column(name = "ORDER_STATUS")
	private EOrderStatus status = EOrderStatus.PENDING;

	public Order() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public List<ProductHolder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ProductHolder> orderList) {
		this.orderList = orderList;
	}

	public ABusiness getBusiness() {
		return business;
	}

	public void setBusiness(ABusiness business) {
		this.business = business;
	}

	public void add(ProductHolder productHolder) {
		orderList.add(productHolder);
	}

	public BigDecimal getTotalPrice() {
		totalPrice = new BigDecimal(0);
		for (ProductHolder productHolder : orderList) {
			totalPrice = totalPrice.add(productHolder.getHoldingProductsPrice());
		}
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public EOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EOrderStatus orderStatus) {
		this.status = orderStatus;
	}

}
