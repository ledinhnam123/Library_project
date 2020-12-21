/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.bean;

import com.ledinhnam.Entity.BookEntity;
import com.ledinhnam.Entity.CategoryEntity;
import com.ledinhnam.Service.BookService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Hp
 */
@ManagedBean
@Named(value = "bookBean")
@RequestScoped
public class BookBean {
 
    private String name;
    private String description;
    private CategoryEntity category;
    private Part imgFile;
    private static BookService bookService = new BookService();

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public BookBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) { // vào lần đầu thì ms lấy, bấm thì không làm nữa
            String bookId = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRequestParameterMap().get("book_id");
            if (bookId != null && !bookId.isEmpty()) {
                BookEntity book = bookService.getBookById(Integer.parseInt(bookId));
               
                this.description = book.getDescription();
                this.name = book.getBookName();
                this.category = book.getCetegory(); // lấy l
            }
        }
    }

    public List<BookEntity> getBooks() {
        List<BookEntity> books = bookService.getBooks(null);

        return books;
    }

    //update and save Book
    public String addBook() {

//        String bookId = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap().get("book_id");
        BookEntity book;
     
            book = new BookEntity(); // transient
        
       
        book.setBookName(this.name);
        book.setDescription(this.description);
        book.setCetegory(this.category);

        try {
            if (this.imgFile != null) {
                this.uploadFile();
                book.setImage("upload/" + this.imgFile.getSubmittedFileName());
            }
            if (bookService.addOrSaveBook(book) == true) {
                return "book-list?faces-redirect=true"; // duong link giao dien 
            }
        } catch (IOException ex) {
            Logger.getLogger(BookBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "books"; // trang san pham
    }
//upload

    private void uploadFile() throws IOException {

//        String path = FacesContext.getCurrentInstance().getExternalContext().
//                getRealPath("/resources/images/upload") + "/"
//                + this.imgFile.getSubmittedFileName(); //cach 1
//        String path ="E:\\JSFPROJECT\\Library\\src\\main\\webapp\\resources\\images\\upload\\" + this.imgFile.getSubmittedFileName();
        String path = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getInitParameter("com.ledinhnam.uploadPath") + this.imgFile.getSubmittedFileName();
        try ( InputStream in = this.imgFile.getInputStream();  FileOutputStream out = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            // đọc 1 lần là 1024 b trong cái in bỏ vô biến byteRead đến khi còn dữ liệu để đọc
            int byteRead;
            while ((byteRead = in.read(b)) != -1) {
                out.write(b, 0, byteRead);
            }

        } catch (Exception ex) {
            Logger.getLogger(BookBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // xoa sach
    public String deleteBook(BookEntity book) throws Exception {
        if (bookService.deleteBook(book)) {
            return "Successful";
        }

        throw new Exception("Something Wrong!!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Part getImgFile() {
        return imgFile;
    }

    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }

    public static BookService getBookService() {
        return bookService;
    }

    public static void setBookService(BookService bookService) {
        BookBean.bookService = bookService;
    }


}
