/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Comment;
import com.services.ServiceComment;
import java.util.ArrayList;

/**
 *
 * @author DellXPS
 */
public class CommentTest {

    public static void main(String[] args) {
        ServiceComment sc = ServiceComment.getInstance();
        sc.addComment(new Comment("Nice book"), 1, 2);
        ArrayList<Comment> comments = sc.getComments();
        for (Comment c : comments) {
            System.out.println(c.toString());
        }
        sc.editComment(3, "NiCE BOOK BOOK");
        for (Comment c : sc.getComments()) {
            System.out.println(c.toString());
        }
        sc.deleteComment(3);
    }
}
