package com.bridgelab.bookstore.dto;

import lombok.Data;

@Data
public class BookDTO {
	private String bookName;
	private String bookAuthor;
	private String bookdescription;
	private String bookLogo;
	private double bookPrice;
	private int bookQuantity;

}
