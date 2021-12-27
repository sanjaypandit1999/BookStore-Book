package com.bridgelab.bookstore.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelab.bookstore.dto.BookDTO;
import com.bridgelab.bookstore.dto.ResponseDTO;
import com.bridgelab.bookstore.exception.BookControllerException;
import com.bridgelab.bookstore.model.Book;
import com.bridgelab.bookstore.repository.BookRepository;
import com.bridgelab.bookstore.util.JwtToken;
@Service
public class BookService implements IBookService {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	JwtToken jwtToken;
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseDTO createBook(String token, BookDTO bookDto) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token="+token, Boolean.class);
		if(verify) {
		Book createBook = modelmapper.map(bookDto, Book.class);
		bookRepository.save(createBook);
		
		return new ResponseDTO("Book Details",createBook,200);
		}else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public ResponseDTO getAllBookDetails(String token) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			return new ResponseDTO("", bookRepository.findAll(), 200);
		}else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public Book getBookDetails(String token, Long id) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			Optional<Book> isUserPresent = bookRepository.findById(id);
			if(isUserPresent.isPresent()) {
				return isUserPresent.get();
			}else
				throw new BookControllerException("Book is not present");
				
		} else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public ResponseDTO updateBookDetails(String token, Long id, BookDTO updateDTO) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			Optional<Book> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setBookAuthor(updateDTO.getBookAuthor());
				isUserPresent.get().setBookName(updateDTO.getBookName());
				isUserPresent.get().setBookPrice(updateDTO.getBookPrice());
				isUserPresent.get().setBookQuantity(updateDTO.getBookQuantity());
				isUserPresent.get().setBookdescription(updateDTO.getBookdescription());
                bookRepository.save(isUserPresent.get());
            	return new ResponseDTO("",isUserPresent,200);
            } else {

            	throw new BookControllerException("Book is not present");
            }
		} else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public void deleteBookById(String token, Long id) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			Optional<Book> isUserPresent = bookRepository.findById(id);
			if (isUserPresent.isPresent()) {
				bookRepository.deleteById(id);
			} else {
				throw new BookControllerException("Book is not present");
			}
		} else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public ResponseDTO changeBookQuanity(String token, long id, int quantity) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			Optional<Book> isBookPresent = bookRepository.findById(id);
			if(isBookPresent.isPresent()) {
				isBookPresent.get().setBookQuantity(quantity);
				bookRepository.save(isBookPresent.get());
				return new ResponseDTO("Book quantity is changed",isBookPresent.get(),200);
			} else 
				throw new BookControllerException("Book is not present");
		} else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

	@Override
	public ResponseDTO changeBookPrice(String token, long id, double price) {
		boolean verify = restTemplate.getForObject("http://localhost:8082/user/verify?token=" + token, Boolean.class);
		if (verify) {
			Optional<Book> isBookPresent = bookRepository.findById(id);
			if(isBookPresent.isPresent()) {
				isBookPresent.get().setBookPrice(price);
				bookRepository.save(isBookPresent.get());
				return new ResponseDTO("Book price is changed",isBookPresent.get(),200);
			} else 
				throw new BookControllerException("Book is not present");
		} else
			throw new BookControllerException("Access Denied...! please check the login token");
	}

}
