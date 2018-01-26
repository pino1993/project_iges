/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prezzipazzi.test;

import com.prezzipazzi.database.Database;
import java.sql.*;

/**
 *
 * @author ruggerotammaro
 */
public class TestDb {
    public static void main(String[] args)  {
       try{ 
        Connection conn = Database.getConnessione();
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprezzipazzi","root","");

        Statement myStat = conn.createStatement();
			//esecuzione sql query
			String pass="3";
			myStat.executeUpdate("INSERT INTO Admin (Nofferte,Email_Admin,Password,Nome_Fornitore,Nome,Cognome) VALUES (3,'prova@gmail.it',1234,'Amazon','Ruggero','Tammaro') ");
			myStat.executeUpdate("INSERT INTO Admin (Nofferte,Email_Admin,Password,Nome_Fornitore,Nome,Cognome) VALUES (3,'ok@gmail.it',1234,'Alibaba','Pino','Papaleo') ");
			myStat.executeUpdate("INSERT INTO Admin (Nofferte,Email_Admin,Password,Nome_Fornitore,Nome,Cognome) VALUES (3,'test@gmail.it',1234,'Ebay','Andrea','Valenti') ");
			ResultSet myRs= myStat.executeQuery("select * from Admin Where Nofferte="+pass);
			//esecuzione
			while (myRs.next()){
				System.out.println(myRs.getString("Email_Admin")+","+ myRs.getString("Nome_Fornitore")+","+ myRs.getString("nome")+","+ myRs.getString("cognome"));
			}
			myStat.executeUpdate("DELETE from Admin WHERE Nofferte="+pass);
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
    }
}
