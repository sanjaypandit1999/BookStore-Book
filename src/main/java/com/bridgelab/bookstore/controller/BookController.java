package com.bridgelab.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelab.bookstore.dto.BookDTO;
import com.bridgelab.bookstore.dto.ResponseDTO;
import com.bridgelab.bookstore.model.Book;
import com.bridgelab.bookstore.service.IBookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private IBookService bookService;
	@PostMapping("/addbook")
	public ResponseEntity<ResponseDTO>createBook(@RequestParam  String token, @RequestBody BookDTO bookDto){
		ResponseDTO response=bookService.createBook(token, bookDto);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	

	@GetMapping("/getallBookDetails")
	public ResponseEntity<ResponseDTO> getBookDetails(@RequestParam String token) {
		ResponseDTO respDTO = bookService.getAllBookDetails(token);
		System.out.println(respDTO);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getBookDetails(@RequestParam String token, @PathVariable Long id) {
		Book respDTO = bookService.getBookDetails(token, id);
		return new ResponseEntity<Book>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<ResponseDTO> updateBookDetails(@RequestParam String token ,@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		ResponseDTO respDTO = bookService.updateBookDetails(token, id, bookDTO);
		System.out.println(respDTO);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebook/{id}")
	public ResponseEntity<ResponseDTO> deleteBookById(@RequestParam String token,@PathVariable Long id) {
		bookService.deleteBookById(token, id);
		ResponseDTO respDTO = new ResponseDTO("Deleted Book Details with id : ", id,200);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/changequantity")
	public ResponseEntity<ResponseDTO> changeBookQuanity(@RequestParam String token, @RequestParam long id,  @RequestParam int quantity) {
			ResponseDTO responseDTO = bookService.changeBookQuanity(token, id, quantity);
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@GetMapping("/changeprice")
	public ResponseEntity<ResponseDTO> changeBookPrice(@RequestParam String token, @RequestParam long id,  @RequestParam double price) {
			ResponseDTO response =bookService.changeBookPrice(token, id, price);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	@PostMapping("/uploadImage/{id}")
	public ResponseEntity<ResponseDTO> setBookLogo(@RequestParam String token,@PathVariable Long id, @RequestParam(value="File") MultipartFile multipartFile) {
			ResponseDTO response =bookService.setBookLogo(token,id, multipartFile);
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
}
