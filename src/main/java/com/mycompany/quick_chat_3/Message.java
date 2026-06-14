/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quick_chat_3;

import java.io.FileWriter;
import java.io.IOException;

class Message {
    
    
    String messageID;
    int messageNumber;
    String recipient;
    String message;
    String messageHash;

    // this part will generate a 10 digit ID
    public static String generateMessageID() {

        String id = "";

        for (int i = 0; i < 10; i++) {
            id += (int) (Math.random() * 10);
        }

        return id;
    }

    // Check recipient cellphone number
    public static boolean checkRecipientCell(
            String recipient) {
        if (!recipient.startsWith("+27")) {
            return false;
        }

        if (recipient.length() != 12) {
            return false;
        }
        String numbers =
                recipient.substring(3);

        return numbers.matches("\\d+");
    }

    // this part handles the generating of the hash
    public static String generateHash(
            String messageID,
            int messageNumber,
            String messageText) {

        String firstTwo =
                messageID.substring(0, 2);

        String[] words =
                messageText.split(" ");

        String firstWord = words[0];
        String lastWord =
                words[words.length - 1];

        return (firstTwo
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord).toUpperCase();
    }

    // this part saves the messages at the JSON
    public static void storeMessage(
            Message msg) {

        try {

            FileWriter file =
                    new FileWriter(
                            "messages.json",
                            true);

            file.write("{\n");

            file.write("\"Message ID\": \""
                    + msg.messageID
                    + "\",\n");

            file.write("\"Message Hash\": \""
                    + msg.messageHash
                    + "\",\n");

            file.write("\"Recipient\": \""
                    + msg.recipient
                    + "\",\n");

            file.write("\"Message\": \""
                    + msg.message
                    + "\"\n");

            file.write("}\n");

            file.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving message.");
        }
    }
}

