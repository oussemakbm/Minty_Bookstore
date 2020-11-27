/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tests;

import com.models.Serie;
import com.services.ServiceSerie;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class SerieTest {
    public static void main(String[] args) {
            ServiceSerie sl = new ServiceSerie();
            try {
            /*System.out.println(sl.getSeries());
            sl.addSerie(new Serie("Serie 1"));
            sl.addSerie(new Serie("Serie 2"));
            sl.addSerie(new Serie("Serie 3"));
            System.out.println("*************************************");
            System.out.println(sl.getSeries());
            System.out.println("SUPRESSION");
            sl.deleteSerie(3);
            System.out.println(sl.getSeries());
            
            Serie s = new Serie("ES");
            sl.addSerie(s);*/
            Serie s = new Serie(4, "Serie 4");
            sl.updateSerie(s);
        } catch (SQLException e) {
            System.out.println("Exception : "+e.getMessage());
        }
        /* --- */
        }
}
