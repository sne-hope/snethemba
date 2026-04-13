/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

//this part handles the user login and registration logic 
class Login {
    static User registeredUser = new User();

    //Ensuring the username format is correct
    static boolean checkUserName(String username){
        return !(username.length() > 5 || !username.contains("_"));
    }

    //Check the user's password complexity
    static boolean checkPasswordComplexity(String password){
        //This checks for the length,capital letter,special characters and digits in the password
        if(password.length() < 8) return false;
        if(password.equals(password.toLowerCase())) return false;
        if(!password.matches(".*[!@#$%^&()_+].*")) return false;
        if(!password.matches(".*\\d.*")) return false;
        return true;
    }

    //Check the user's cellphone format is correct
    static boolean checkCellPhoneNumber(String cellphone){
        //For example the user can use the +27 format
        if(cellphone.startsWith("+27") && cellphone.length() == 12){
            String numbers = cellphone.substring(3);
            if(numbers.matches("\\d+")){
                return true;
            }
        }
        return false;
    }

    //Register a user with validation 
    static String registerUser(String firstName, String username, String password, String cellphone, String lastName){
        String errorMessages = "";

        //Check the user's input and output the errors
        if(!checkUserName(username)){
            errorMessages += "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters length.\n";
        }
        
        if(!checkPasswordComplexity(password)){
            errorMessages += "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.\n";
        }
        
        if(!checkCellPhoneNumber(cellphone)){
            errorMessages += "Cellphone is not correctly formatted or does not contain international code.\n";
        }
        
        if(!errorMessages.isEmpty()){
            return errorMessages;
        }

        //Check the user's input and output the successes
        String successMessage = "";
        successMessage += "Username successfully captured\n";
        successMessage += "Password successfully captured\n";
        successMessage += "Cellphone successfully captured\n";
        successMessage += "Welcome " + firstName + " " + lastName + " it is great to see you.";

        //Populate user on success
        registeredUser.username = username;
        registeredUser.password = password;
        registeredUser.firstName = firstName;
        registeredUser.lastName = lastName;
        registeredUser.cellphone = cellphone;
        
        return successMessage;
    }

    //Attempt the user login
    static boolean loginUser(String username, String password){
        
        if(username.equals(registeredUser.username) && 
           password.equals(registeredUser.password)){
            return true;
        }else{
            return false;
        }
    }

    //thiss will be the output login status message
    static String returnLoginStatus(boolean isLoggedIn) {
        if(isLoggedIn) {
            
            String firstName = registeredUser.firstName != null ? registeredUser.firstName : "";
            String lastName = registeredUser.lastName != null ? registeredUser.lastName : "";
            //the system's output welcome message
            return "Welcome Back " + firstName + " " + lastName + " " + "it is great to see you  again";
        } else {
            return "Loggin  failed";
        }
    }
}
