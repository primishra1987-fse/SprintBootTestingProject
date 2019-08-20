package com.example.springbootrestProgram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springbootrestProgram.dao.BooksDao;
import com.example.springbootrestProgram.entity.Books;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BooksDao bookDao;

	@Test
	public void DaoTest() throws Exception
	{
		Books entityBook = new Books(6,"Book-6",300,3);
		
		this.entityManager.persist(entityBook);
		Optional<Books> book = this.bookDao.findById(Long.parseLong("6"));
		assertThat(book.get().getTitle()).isEqualTo("Book-6");
		assertThat(book.get().getVolume()).isEqualTo(3);
		
		
	}
	
	
	
	

}
