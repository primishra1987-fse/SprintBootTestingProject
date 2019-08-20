package com.example.springbootrestProgram.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootrestProgram.dao.BooksDao;
import com.example.springbootrestProgram.entity.Books;


@Service
@Transactional
public class BooksServiceImpl implements BooksService{

	@Autowired
	BooksDao booksDao;

	@Override
	public List<Books> findAllBooks() {
		List <Books> bookList = booksDao.findAll();
		return bookList;
	}

	@Override
	public Books findBookByID(Long id) {
		Optional<Books> book = booksDao.findById(id) ;
		
		if (book.get()!=null)
			return book.get();
		else
			return null;
	}

	@Override
	public Books saveBook(Books book) {
		return booksDao.save(book);
		
	}

	@Override
	public Long deleteBookByID(Long bookID) {
		return booksDao.deleteBookByID(bookID);
		
	}
	
}
