package com.example.springbootrestProgram.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrestProgram.entity.Books;
import com.example.springbootrestProgram.service.BooksService;


@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	BooksService booksService;
	
	
	
			@CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value="/bookList", method=RequestMethod.GET, 
			produces = { "application/json", "application/xml" })
			public ResponseEntity<List<Books>> getBookListDetails(){
				List<Books> bookList ;
				
				bookList = booksService.findAllBooks();
				
				
				 return ResponseEntity.ok()
					        .header("Custom-Header", "foo")
					        .body(bookList);
				
			}
			
			@CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value="/bookById/{id}", method=RequestMethod.GET, 
					produces = { "application/json", "application/xml" })
					public ResponseEntity<Books> getBookDetails(@PathVariable("id") Long id){
						Books book ;
						
						book = booksService.findBookByID(id);
						
						if ( book !=null) {
						 return ResponseEntity.ok()
							        .header("Custom-Header", "foo")
							        .body(book);
						}
						else {
							return ResponseEntity.notFound()
									.header("Custom-Header", "IDNotFound").build();
							
							        
							
						}
						
					}
			
			@CrossOrigin(origins = "http://localhost:4200")
			@RequestMapping(value="/putjson", method=RequestMethod.PUT, 
					produces = { "application/json", "application/xml" }, consumes={ "application/json", "application/xml" })
					public Books putEmployeeDetails123(@RequestBody @Valid Books book){
				
				System.out.println("Alok1");
				Books bookFromReq ;
				bookFromReq = booksService.findBookByID(book.getBookId());
				try {
				
				if (bookFromReq != null) {
					System.out.println("test");
				bookFromReq.setBookId(book.getBookId());
				bookFromReq.setTitle(book.getTitle());
				bookFromReq.setPrice(book.getPrice());
				bookFromReq.setPublishDate(book.getPublishDate());
				bookFromReq.setVolume(book.getVolume());
				bookFromReq.setSubjectID(book.getSubjectID());
				
				booksService.saveBook(bookFromReq);
				
				}
				}
				catch (Exception e){
					e.getMessage();
					return null;
				}
				return bookFromReq;
				
				
			}
			
			
			
			
			@RequestMapping(value="/deletejson", method=RequestMethod.DELETE, 
					 consumes={ "application/json", "application/xml" })
					public void deleteEmployeeDetails123(@RequestBody @Valid Books book){
					
					booksService.deleteBookByID(book.getBookId());
					
			}
			
			@RequestMapping(value="/bookjson", method=RequestMethod.POST, 
					produces="application/xml", consumes={ "application/json", "application/xml" })
					public Books getEmployeeDetails123(@RequestBody @Valid Books book){
				
				if (book != null && book.getBookId() != 0){
				System.out.println("Alok1");
				Books insertBook = new Books ();
				insertBook.setBookId(book.getBookId());
				insertBook.setTitle(book.getTitle());
				insertBook.setPrice(book.getPrice());
				insertBook.setPublishDate(book.getPublishDate());
				insertBook.setVolume(book.getVolume());
				insertBook.setSubjectID(book.getSubjectID());
				
				booksService.saveBook(insertBook);
				
				
				return insertBook;
				}
				else {
					return null;
				}
			}
	

	
}
