/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Author;
import com.models.AuthorPrefer;
import com.services.ServiceAuthor;
import com.services.ServicePreferAuthor;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class AuthorTest {

    public static void main(String[] args) {
        ServiceAuthor sa = new ServiceAuthor();
        ServicePreferAuthor spa = new ServicePreferAuthor();
        try {
            AuthorPrefer ap = new AuthorPrefer(1, 1);
            spa.addPreferAuthor(ap);
            spa.updateAuthorPrefer(ap);
            spa.deleteAuthorPrefer(1);
            System.out.println(spa.getAuthorPrefer(2));
            sa.addAuthor(new Author("a", "a", ""));
            sa.deleteAuthor(1);
            Author a = new Author(2, "ggg", "gg", "mlml");
            sa.updateAuthor(a);
            System.out.println(sa.getAuthors());
            System.out.println(sa.getAuthor(2));
        } catch (SQLException ex) {
            System.out.println("ereeur");
        }
    }
}
