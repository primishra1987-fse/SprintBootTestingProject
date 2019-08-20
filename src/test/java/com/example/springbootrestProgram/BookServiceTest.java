package com.example.springbootrestProgram;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springbootrestProgram.dao.BooksDao;
import com.example.springbootrestProgram.entity.Books;
import com.example.springbootrestProgram.service.BooksServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


	//mock object	@Mock
	@MockBean
    private BooksDao daoMock;

	
	// injecting mock object into employee service
    //@InjectMocks
    @Autowired
	private BooksServiceImpl service1;
	
    @Before
    public void setUp() throws Exception {
        // booksList = getTestData(); 
    }
	
    
    
    @Test
 	public void testfindBookById() {
    			Books dummy=new Books();
    			dummy.setBookId(1);
     		
    			when(daoMock.findById(any(Long.class)))
     			.thenReturn(Optional.of(dummy));
    			
    			Books d=service1.findBookByID(Long.parseLong("1"));
     		System.out.println(d.getBookId());
     		assertThat(service1.findBookByID(Long.parseLong("1")), is(notNullValue())); 	
     		}
    
    @Test
    @Transactional
	public void testDeleteByID() {
		
		Mockito.doReturn(Long.parseLong("1001"))
		.when(daoMock)
		.deleteBookByID(Long.parseLong("1001"));
		
		
		Long deletedRecord = service1.deleteBookByID(Long.parseLong("1001"));
		assertEquals("deleted Book count is not matching", 1001, deletedRecord.intValue());
		
    }
    
    
    
    @Test
 	public void testfindAllBooks() {
    			List<Books> bookList =new ArrayList<Books>();
    			Books book = new Books();
    			book.setBookId(1);
    			bookList.add(book);
     		
    			when(daoMock.findAll())
     			.thenReturn(bookList);
    			
    			List<Books>  d=service1.findAllBooks();
     		System.out.println(d.get(0).getBookId());
     		assertThat(service1.findAllBooks(), is(notNullValue())); 	
     		}
    
    @Test
	public void testSave() {
    		when(daoMock.save(any(Books.class)))
    					.thenReturn(new Books());
    		//assertTrue(service1.insertEmployee(new Employee()));
    		assertThat(service1.saveBook(new Books()),is(instanceOf(Books.class)));
	}
    
    
    
	    


	
	
}
