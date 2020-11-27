/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Book;
import com.services.ServiceBook;
import java.sql.SQLException;


/**
 *
 * @author MediaStudio
 */
public class BookTest {
    
    public static void main(String[] args) {
        
        ServiceBook sb = new ServiceBook();
      

        try {
            
            
            sb.addBook(new Book(1,1, 1, 1, 1, 1, 1, "le long chemin vers la liberté", "Mandela", "IMG", "20/1/2000", 12));
            
            sb.updateBook(new Book(7,1,1, 1, 1, 1, 1, 1, "le long chemin vers la liberté", "Nelson Mandela", "IMG", "20/1/2000", 12));
            sb.deleteBook(4);
            System.out.println(sb.getBook(13));
                System.out.println(sb.getBooks());
            

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        }
    }
    
}
