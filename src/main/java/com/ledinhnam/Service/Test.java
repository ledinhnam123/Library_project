/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Service;

import com.ledinhnam.Entity.BookEntity;
import java.util.List;

/**
 *
 * @author Hp
 */
public class Test {
    
    public static void main(String[] args) {
      BookService bookService = new BookService();
        
        List<BookEntity> books = bookService.getAllBook();
       for(BookEntity book : books){
           System.err.println("Book Name" + book.getBookName());
                   
       }
     
    }
}
