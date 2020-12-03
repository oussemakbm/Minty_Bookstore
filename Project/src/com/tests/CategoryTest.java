/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Category;
import com.services.ServiceCategory;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class CategoryTest {

    public static void main(String[] args) throws SQLException {
        ServiceCategory cs = ServiceCategory.getInstance();
        try {
            Category c2 = new Category("Drama"); // (name)
        Category c3 = new Category("Action");

        cs.addCategory(c2);
        cs.addCategory(c3);

        System.out.println(cs.getCategories());

        Category c4 = new Category(2, "Adventure");
        cs.updateCategory(c4);
        System.out.println(cs.getCategories());

        cs.deleteCategory(5);
        System.out.println(cs.getCategories());
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }

}
