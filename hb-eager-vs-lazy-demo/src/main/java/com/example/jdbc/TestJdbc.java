package com.example.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSl=false&serverTimeZone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try{
            log.info("Connecting to database : " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
            log.info("Connection Successful!");
        }
        catch (Exception e){
            log.error("Exception occurred : "+e);
        }
    }
}
