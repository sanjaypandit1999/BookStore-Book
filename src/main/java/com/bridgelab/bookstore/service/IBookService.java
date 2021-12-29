package com.bridgelab.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelab.bookstore.dto.BookDTO;
import com.bridgelab.bookstore.dto.ResponseDTO;
import com.bridgelab.bookstore.model.Book;
@Service
public interface IBookService {

	ResponseDTO createBook(String token, BookDTO bookDto);

	ResponseDTO getAllBookDetails(String token);

	Book getBookDetails(String token, Long id);

	ResponseDTO updateBookDetails(String token, Long id, BookDTO bookDTO);

	void deleteBookById(String token, Long id);

	ResponseDTO changeBookQuanity(String token, long id, int quantity);

	ResponseDTO changeBookPrice(String token, long id, double price);

	ResponseDTO setBookLogo(String token,Long id, MultipartFile multipartFile);

}
