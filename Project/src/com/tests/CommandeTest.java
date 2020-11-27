/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.CommandList;
import com.services.ServiceCommandList;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class CommandeTest {

    public static void main(String[] args) throws SQLException {
        ServiceCommandList cls = new ServiceCommandList();
        CommandList cli1 = new CommandList("Livré", 4, 1); // (status,price,idUser)
        CommandList cli2 = new CommandList("Livré", 33, 1);
        cls.addCommandList(cli1);
        cls.addCommandList(cli2);
        System.out.println(cls.getCommandList());
        CommandList cli3 = new CommandList(1, "En cours", 4, 1);
        cls.updateCommandList(cli3);
        System.out.println(cls.getCommandList());

        cls.deleteCommandList(4);
        System.out.println(cls.getCommandList());
    }
}
