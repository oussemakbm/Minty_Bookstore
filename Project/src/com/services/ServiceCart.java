/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.models.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ihebf
 */
public class ServiceCart {

    private Map<Book, Integer> books;
    private static ServiceCart cart;

    private ServiceCart() {
        books = new HashMap();
    }

    public void addToCart(Book book, int quantity) {
        books.put(book, quantity);
    }

    public void removeFromCart(Book book) {
        books.remove(book);
    }

    public float getTotal() {
        float total = 0;
        for (Map.Entry<Book, Integer> element : books.entrySet()) {
            total += element.getKey().getPrix() * element.getValue();
        }
        return total;
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(Map<Book, Integer> books) {
        this.books = books;
    }
    
    

    public static ServiceCart getInstance() {
        if (cart == null) {
            cart = new ServiceCart();
        }
        return cart;
    }
}
