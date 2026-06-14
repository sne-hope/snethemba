/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quick_chat_3;

import java.util.ArrayList;
import java.util.Scanner;

// this is the main class for quickchat
public class Quick_chat_3 {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //these arrays store messages
        ArrayList<Message> sentMessages =
                new ArrayList<>();

        ArrayList<Message> disregardedMessages =
                new ArrayList<>();

        ArrayList<Message> storedMessages =
                new ArrayList<>();

        ArrayList<String> messageHashes =
                new ArrayList<>();

        ArrayList<String> messageIDs =
                new ArrayList<>();

        // this is where the user enter their details and the system wil vallidate it
        System.out.println("=== REGISTER ===");

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

        String regStatus =
                Login.registerUser(
                        fName,
                        uName,
                        pass,
                        cell,
                        lName);

        System.out.println(regStatus);

        if (regStatus.toLowerCase().contains("incorrect")
                || regStatus.toLowerCase().contains("not correctly")) {

            System.out.println("Registration failed.");
            return;
        }

        // this section handles the logging in of the user
        System.out.println("\n=== LOGIN ===");

        System.out.print("Username: ");
        String loginU = input.nextLine();

        System.out.print("Password: ");
        String loginP = input.nextLine();

        boolean isAuth =
                Login.loginUser(
                        loginU,
                        loginP);

        System.out.println(
                Login.returnLoginStatus(isAuth));

        if (!isAuth) {
            return;
        }

        System.out.println("\nWelcome to QuickChat");

        // ask the user ow many messages tey want to send
        System.out.print(
                "How many messages do you want to enter? ");

        int limit =
                Integer.parseInt(input.nextLine());

        int totalSent = 0;
        boolean running = true;

        // tis is the main menu of the code
        while (running) {

            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages");
            System.out.println("4. Quit");

            System.out.print("Choose option: ");
            String option =
                    input.nextLine();

            switch (option) {

                //this is option 1 wich deals with sending messages
                case "1":

                    if (totalSent >= limit) {

                        System.out.println(
                                "You have reached your message limit.");

                        break;
                    }

                    Message msg = new Message();

                    msg.messageNumber =
                            totalSent + 1;

                    msg.messageID =
                            Message.generateMessageID();

                    // this part deals wit the recipent number
                    System.out.print(
                            "Enter recipient number: ");

                    String recipient =
                            input.nextLine();

                    if (!Message.checkRecipientCell(
                            recipient)) {

                        System.out.println(
                                "Cell phone number incorrectly formatted.");

                        break;
                    }

                    msg.recipient = recipient;

                    // Message
                    System.out.print(
                            "Enter message: ");

                    String text =
                            input.nextLine();

                    //this checks the length of the code which cant be onger than 250 characters
                    if (text.length() > 250) {

                        int extra =
                                text.length() - 250;

                        System.out.println(
                                "Message exceeds 250 characters by "
                                        + extra);

                        break;
                    }

                    msg.message = text;

                    //this part of the code generates a unique message hash
                    msg.messageHash =
                            Message.generateHash(
                                    msg.messageID,
                                    msg.messageNumber,
                                    msg.message);

                    
                    System.out.println("\n1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");

                    System.out.print("Choose: ");
                    String action =
                            input.nextLine();

                    if (action.equals("1")) {

                        sentMessages.add(msg);

                        messageHashes.add(
                                msg.messageHash);

                        messageIDs.add(
                                msg.messageID);

                        totalSent++;

                        System.out.println(
                                "Message successfully sent.");

                        System.out.println(
                                "\nMessage ID: "
                                        + msg.messageID);

                        System.out.println(
                                "Message Hash: "
                                        + msg.messageHash);

                        System.out.println(
                                "Recipient: "
                                        + msg.recipient);

                        System.out.println(
                                "Message: "
                                        + msg.message);

                    } else if (action.equals("2")) {

                        disregardedMessages.add(msg);

                        System.out.println(
                                "Press 0 to delete message");

                    } else if (action.equals("3")) {

                        storedMessages.add(msg);

                        Message.storeMessage(msg);

                        System.out.println(
                                "Message successfully stored.");
                    }

                    break;

                    //this option is still being developed
                case "2":

                    System.out.println(
                            "Coming Soon.");

                    break;

                    //this allows te user to use the stored messages feature
                case "3":

                    boolean storedMenu = true;

                    while (storedMenu) {

                        System.out.println(
                                "\n--- STORED MESSAGES ---");

                        System.out.println(
                                "1. Display sender and recipient");

                        System.out.println(
                                "2. Display longest stored message");

                        System.out.println(
                                "3. Search by Message ID");

                        System.out.println(
                                "4. Search by recipient");

                        System.out.println(
                                "5. Delete using message hash");

                        System.out.println(
                                "6. Display full report");

                        System.out.println(
                                "7. Back");

                        System.out.print(
                                "Choose option: ");

                        String storedOption =
                                input.nextLine();

                        switch (storedOption) {

                            case "1":

                                for (Message m
                                        : storedMessages) {

                                    System.out.println(
                                            "Sender: Me");

                                    System.out.println(
                                            "Recipient: "
                                                    + m.recipient);
                                }

                                break;

                            case "2":

                                if (storedMessages.isEmpty()) {

                                    System.out.println(
                                            "No stored messages.");

                                } else {

                                    Message longest =
                                            storedMessages.get(0);

                                    for (Message m
                                            : storedMessages) {

                                        if (m.message.length()
                                                > longest.message.length()) {

                                            longest = m;
                                        }
                                    }

                                    System.out.println(
                                            "Longest message: "
                                                    + longest.message);
                                }

                                break;

                            case "3":

                                System.out.print(
                                        "Enter Message ID: ");

                                String searchID =
                                        input.nextLine();

                                boolean found = false;

                                for (Message m
                                        : storedMessages) {

                                    if (m.messageID.equals(
                                            searchID)) {

                                        System.out.println(
                                                "Recipient: "
                                                        + m.recipient);

                                        System.out.println(
                                                "Message: "
                                                        + m.message);

                                        found = true;
                                    }
                                }

                                if (!found) {

                                    System.out.println(
                                            "Message not found.");
                                }

                                break;

                                //this will output the number if messages 
                            case "4":

                                System.out.print(
                                        "Enter recipient number: ");

                                String recipientSearch =
                                        input.nextLine();

                                for (Message m
                                        : storedMessages) {

                                    if (m.recipient.equals(
                                            recipientSearch)) {

                                        System.out.println(
                                                m.message);
                                    }
                                }

                                break;

                            case "5":

                                System.out.print(
                                        "Enter message hash: ");

                                String hash =
                                        input.nextLine();

                                boolean deleted = false;

                                for (int i = 0;
                                        i < storedMessages.size();
                                        i++) {

                                    if (storedMessages.get(i)
                                            .messageHash
                                            .equals(hash)) {

                                        storedMessages.remove(i);

                                        System.out.println(
                                                "Message deleted.");

                                        deleted = true;
                                        break;
                                    }
                                }

                                if (!deleted) {

                                    System.out.println(
                                            "Message hash not found.");
                                }

                                break;

                            case "6":

                                for (Message m
                                        : storedMessages) {

                                    System.out.println(
                                            "\nHash: "
                                                    + m.messageHash);

                                    System.out.println(
                                            "Recipient: "
                                                    + m.recipient);

                                    System.out.println(
                                            "Message: "
                                                    + m.message);
                                }

                                break;

                            case "7":

                                storedMenu = false;
                                break;

                            default:

                                System.out.println(
                                        "Invalid option.");
                        }
                    }

                    break;

                case "4":

                    running = false;

                    System.out.println(
                            "\nTotal messages sent: "
                                    + totalSent);

                    System.out.println(
                            "Goodbye!");

                    break;

                default:

                    System.out.println(
                            "Invalid option.");
            }
        }

        input.close();
    }
}