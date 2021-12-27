package com.bridgelab.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="book_details")
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long Id;
	
	private String bookName;
	private String bookAuthor;
	private String bookdescription;
	private String bookLogo;
	private double bookPrice;
	private int bookQuantity;
}
