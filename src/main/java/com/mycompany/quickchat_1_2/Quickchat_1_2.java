/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat_1_2;

import java.util.ArrayList;
import java.util.Scanner;


public class Quickchat_1_2 {

    public static void main(String[] args) {
       
        
        Scanner input = new Scanner(System.in);

        //Part 1 which is your User Registration 
        System.out.print("Enter first name: ");
        String fName = input.nextLine();

        System.out.print("Enter last name: ");
        String lName = input.nextLine();

        System.out.print("Enter username: ");
        String uName = input.nextLine();

        System.out.print("Enter password: ");
        String pass = input.nextLine();

        System.out.print("Enter cellphone number: ");
        String cell = input.nextLine();

        // Pass variables to login class for validation
        String regStatus = Login.registerUser(fName, uName, pass, cell, lName);
        System.out.println(regStatus);

        // Kill the app if registration is bunk
        if (regStatus.toLowerCase().contains("incorrect") || regStatus.toLowerCase().contains("not correctly")) {
            System.out.println("Fatal: Registration failed. Fix errors and retry.");
            return;
        }

        // --- Part 2: Authentication ---
        System.out.print("\nLogin Username: ");
        String loginU = input.nextLine();

        System.out.print("Login Password: ");
        String loginP = input.nextLine();

        boolean isAuth = Login.loginUser(loginU, loginP);
        System.out.println(Login.returnLoginStatus(isAuth));

        if (!isAuth) return; // Exit early on bad login

        System.out.println("\n--- Welcome to Quickchat ---");

        System.out.print("Set your session message limit: ");
        int limit = Integer.parseInt(input.nextLine()); 

        // Track the messages sent this session
        ArrayList<Message> history = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create Message\n2. History (Coming Soon)\n3. Exit");
            System.out.print("Selection: ");
            String nav = input.nextLine();

            switch (nav) {
                case "1":
                    // Check if user is over their limit
                    if (history.size() >= limit) {
                        System.out.println("Limit reached. Upgrade account for more.");
                        break;
                    }

                    Message newMsg = new Message();
                    newMsg.messageNumber = history.size() + 1;
                    newMsg.messageID = Message.generateMessageID();

                    System.out.print("Recipient Mobile: ");
                    String targetNum = input.nextLine();

                    if (!Message.checkRecipientCell(targetNum)) {
                        System.out.println("Error: Invalid recipient format.");
                        break;
                    }
                    newMsg.recipient = targetNum;

                    System.out.print("Message Body: ");
                    String body = input.nextLine();

                    // Validation for message length
                    if (body.length() > 250) {
                        System.out.println("Error: Message exceeds 250 chars.");
                        break;
                    } 

                    newMsg.message = body;
                    newMsg.messageHash = Message.generateHash(newMsg.messageID, newMsg.messageNumber, newMsg.message);

                    // Message Action Menu
                    System.out.println("\nAction: [1] Send [2] Trash [3] Draft");
                    System.out.print("Choose: ");
                    String action = input.nextLine();

                    if (action.equals("1")) {
                        history.add(newMsg);
                        System.out.println("Sent!");
                        
                        // output the summary
                        System.out.println("ID: " + newMsg.messageID);
                        System.out.println("Hash: " + newMsg.messageHash);
                        System.out.println("To: " + newMsg.recipient);
                    } 
                    else if (action.equals("2")) {
                        System.out.print("Confirm deletion (Type 0): ");
                        if (input.nextLine().equals("0")) System.out.println("Discarded.");
                    } 
                    else if (action.equals("3")) {
                        Message.storeMessage(newMsg);
                        System.out.println("Saved to drafts.");
                    }
                    break;

                case "2":
                    System.out.println("Feature locked. Development in progress.");
                    break;

                case "3":
                    isRunning = false;
                    System.out.println("Session Summary: " + history.size() + " messages sent.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
        input.close();
    }
}
    


