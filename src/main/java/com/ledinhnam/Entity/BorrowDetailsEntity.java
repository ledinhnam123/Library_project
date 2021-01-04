/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

/**
 *
 * @author Hp
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrow_detail")
public class BorrowDetailsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name ="quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="borrow_id")
    private BorrowBookEntity borrowBook;
    
    @ManyToOne
    @JoinColumn(name="book_id")
    private BookEntity book;
    
    public BorrowDetailsEntity(){
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the borrowBook
     */
    public BorrowBookEntity getBorrowBook() {
        return borrowBook;
    }

    /**
     * @param borrowBook the borrowBook to set
     */
    public void setBorrowBook(BorrowBookEntity borrowBook) {
        this.borrowBook = borrowBook;
    }

    /**
     * @return the book
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }
    
   
}
