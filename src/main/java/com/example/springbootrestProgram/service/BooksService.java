package com.example.springbootrestProgram.service;

import java.util.List;

import com.example.springbootrestProgram.entity.Books;

public interface BooksService {
	
	public List<Books>  findAllBooks();
	
	public Books  findBookByID(Long id);
	
	public Books  saveBook(Books book);
	
	public Long  deleteBookByID(Long bookID);
	
	

}
