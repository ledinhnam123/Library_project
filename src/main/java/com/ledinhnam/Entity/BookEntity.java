/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hp
 */

@Entity
@Table(name = "book")
public class BookEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "description")
	private String description;
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "category_id") // duoidb
	private CategoryEntity cetegory;
        @Column(name="image")
        private String image;
	
	@OneToMany(mappedBy = "book")
	private Set<BorrowTicketDetailsEntity> borrowTicketDetails;

	@OneToMany(mappedBy = "bookI")
	private Set<InventoryEntity> inventorys;
	public BookEntity() {

	}


	public Set<InventoryEntity> getInventorys() {
		return inventorys;
	}

	public void setInventorys(Set<InventoryEntity> inventorys) {
		this.inventorys = inventorys;
	}

	public CategoryEntity getCetegory() {
		return cetegory;
	}

	public void setCetegory(CategoryEntity cetegory) {
		this.cetegory = cetegory;
	}

	

	public Set<BorrowTicketDetailsEntity> getBorrowTicketDetails() {
		return borrowTicketDetails;
	}

	public void setBorrowTicketDetails(Set<BorrowTicketDetailsEntity> borrowTicketDetails) {
		this.borrowTicketDetails = borrowTicketDetails;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
	

}

