/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Interaction;
import com.services.ServiceInteraction;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class InteractionTest {

    public static void main(String[] args) throws SQLException {
        ServiceInteraction is = ServiceInteraction.getInstance();
        Interaction i = new Interaction(1, 1, 4, 0);
        Interaction i2 = new Interaction(1, 1, 2, 1);

        try {
            is.addInteraction(i);
            is.addInteraction(i2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(is.getInteractions());

        Interaction i3 = new Interaction(8, 1, 1, 2, 1);
        is.updateInteraction(i3);
        System.out.println(is.getInteraction(8));

        is.deleteInteraction(4);
        System.out.println(is.getInteractions());
    }
}
