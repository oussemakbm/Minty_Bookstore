/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Langue;
import com.services.ServiceLangue;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class LangueTest {
        public static void main(String[] args) {
            ServiceLangue sl = new ServiceLangue();
            try {
            //System.out.println(sl.getLangues());
            /*sl.addLangue(new Langue("EN"));
            sl.addLangue(new Langue("AR"));
            sl.addLangue(new Langue("FR"));
            System.out.println("*************************************");
            System.out.println(sl.getLangues());
            System.out.println("SUPRESSION");
            sl.deleteLangue(3);
            System.out.println(sl.getLangues());
            
            Langue l = new Langue("ES");
            sl.addLangue(l);*/
            Langue l = new Langue(4, "IT");
            sl.updateLangue(l);
        } catch (SQLException e) {
            System.out.println("Exception : "+e.getMessage());
        }

        }
}
