/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Scanner;

//Main class for the QuickChat APP
public class QuickChat {

    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter first name: ");
    String firstName = scan.next();

    //Prompt the user for their details to register   
    System.out.print("Enter last name: ");
    String lastName = scan.next();
    
    System.out.print("Enter username: ");
    String username = scan.next();
    
    System.out.print("Enter password: ");
    String password = scan.next();
    
    System.out.print("Enter cellphone: ");
    String cellphone = scan.next();
        
    String outputmessages = Login.registerUser(firstName, username, password, cellphone, lastName);
    
    
    System.out.println(outputmessages);
    
    //if registration is successful attempt login for the user
    if(outputmessages.contains("not correctly")){
    System.out.println("Please fix your errors and try again.");
    return; 
    }

    //Prompt the user for their login credentials
    System.out.print("Enter username to Login: ");
    String loginUsername = scan.next();
    
    System.out.print("Enter password to Login: ");
    String loginPassword = scan.next();
    
    
    boolean LoginOutput = Login.loginUser(loginUsername, loginPassword);
   
    System.out.println(Login.returnLoginStatus(LoginOutput));
    
    }
}
