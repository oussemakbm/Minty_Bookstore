/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.CommandLine;
import com.services.ServiceCommandLine;
import java.sql.SQLException;

/**
 *
 * @author DellXPS
 */
public class CommandLineTest {

    public static void main(String[] args) throws SQLException {
        ServiceCommandLine scl = ServiceCommandLine.getInstance();
        CommandLine cl1 = new CommandLine(11, 3, 5); // (quantity,idCommandList,idBook)
        CommandLine cl2 = new CommandLine(7, 2, 4);
        scl.addCommandLine(cl1);
        scl.addCommandLine(cl2);
        System.out.println(scl.getCommandLine());
        CommandLine cl3 = new CommandLine();
        cl3.setId(3);
        cl3.setQuantity(10);
        scl.updateCommandLine(cl2);
        System.out.println(scl.getCommandLine());
        scl.deleteCommandLine(2);
        System.out.println(scl.getCommandLine());
    }
}
