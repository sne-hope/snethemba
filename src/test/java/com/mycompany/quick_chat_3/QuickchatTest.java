/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.quick_chat_3;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Snethemba
 */
public class QuickchatTest {
    

    
    @Test
    public void testSentMessagesArray() {

        // Create an array list to store sent messages
        ArrayList<Message> sentMessages = new ArrayList<>();

        // this is the first test message for the unit test
        Message msg1 = new Message();
        msg1.message = "Did you get the cake?";
        msg1.recipient = "+27834557896";
        msg1.messageID = "0012345678";
        msg1.messageNumber = 1;

        // make a hash for message 1
        msg1.messageHash = Message.generateHash(
                msg1.messageID,
                msg1.messageNumber,
                msg1.message);

        // this id the second test message for the unit test
        Message msg4 = new Message();
        msg4.message = "It is dinner time!";
        msg4.recipient = "0838884567";
        msg4.messageID = "0044444444";
        msg4.messageNumber = 4;

        // make a hash for message 4
        msg4.messageHash = Message.generateHash(
                msg4.messageID,
                msg4.messageNumber,
                msg4.message);

       
        sentMessages.add(msg1);
        sentMessages.add(msg4);

        // this will check if the messages were stored correctly
        assertEquals("Did you get the cake?",
                sentMessages.get(0).message);

        assertEquals("It is dinner time!",
                sentMessages.get(1).message);
    }

    // test if the program finds the longest stored message
    @Test
    public void testLongestStoredMessage() {

        // Create array for stored messages
        ArrayList<Message> storedMessages = new ArrayList<>();

        // Create test messages
        Message msg1 = new Message();
        msg1.message = "Did you get the cake?";

        Message msg2 = new Message();
        msg2.message = "Where are you? You are late! I have asked you to be on time.";

        // Add messages to the array
        storedMessages.add(msg1);
        storedMessages.add(msg2);

        
        Message longest = storedMessages.get(0);

        // Loop through array to find longest message
        for (Message m : storedMessages) {
            if (m.message.length() > longest.message.length()) {
                longest = m;
            }
        }

        // Check if the correct message was found
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest.message);
    }

    // Test searching for a message using the Message ID
    @Test
    public void testSearchMessageID() {

        ArrayList<Message> storedMessages = new ArrayList<>();

        // Create test message
        Message msg4 = new Message();
        msg4.messageID = "0838884567";
        msg4.recipient = "0838884567";
        msg4.message = "It is dinner time!";

        storedMessages.add(msg4);

        String foundMessage = "";

        // Search through the array
        for (Message m : storedMessages) {
            if (m.messageID.equals("0838884567")) {
                foundMessage = m.message;
            }
        }

        // Check if the correct message was found
        assertEquals("It is dinner time!", foundMessage);
    }

    // Test searching for messages by recipient number
    @Test
    public void testSearchByRecipient() {

        ArrayList<Message> storedMessages = new ArrayList<>();

        Message msg2 = new Message();
        msg2.recipient = "+27838884567";
        msg2.message = "Where are you? You are late! I have asked you to be on time.";

        storedMessages.add(msg2);

        String foundMessage = "";

        // Search for recipient number
        for (Message m : storedMessages) {
            if (m.recipient.equals("+27838884567")) {
                foundMessage = m.message;
            }
        }

        // Check if the correct message was found
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                foundMessage);
    }

    // Test deleting a message using its hash
    @Test
    public void testDeleteMessageUsingHash() {

        ArrayList<Message> storedMessages = new ArrayList<>();

        Message msg2 = new Message();
        msg2.messageID = "0022222222";
        msg2.messageNumber = 2;
        msg2.recipient = "+27838884567";
        msg2.message = "Where are you? You are late! I have asked you to be on time.";

        // Generate hash
        msg2.messageHash = Message.generateHash(
                msg2.messageID,
                msg2.messageNumber,
                msg2.message);

        storedMessages.add(msg2);

        String hash = msg2.messageHash;

        // Search for hash and delete message
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equals(hash)) {
                storedMessages.remove(i);
                break;
            }
        }

        // Check if the message was removed
        assertEquals(0, storedMessages.size());
    }

    // Test if the message hash is generated correctly
    @Test
    public void testMessageHash() {

        String hash = Message.generateHash(
                "0012345678",
                1,
                "Hi Mike");

        // Check expected hash value
        assertEquals("00:1:HIMIKE", hash);
    }

    // Test a valid recipient number
    @Test
    public void testRecipientNumberSuccess() {

        boolean result =
                Message.checkRecipientCell("+27718693002");

        // Number should be valid
        assertEquals(true, result);
    }

    // Test an invalid recipient number
    @Test
    public void testRecipientNumberFailure() {

        boolean result =
                Message.checkRecipientCell("08575975889");

        // Number should be invalid
        assertEquals(false, result);
    }
}