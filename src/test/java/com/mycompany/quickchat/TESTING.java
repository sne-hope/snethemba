/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;

public class TESTING {

    @Test
    public void testCheckUserNameCorrect() {
        Assert.assertEquals(true, Login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserNameIncorrect() {
        Assert.assertEquals(false, Login.checkUserName("kyle!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexityCorrect() {
        Assert.assertEquals(true, Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexityIncorrect() {
        Assert.assertEquals(false, Login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumberCorrect() {
        Assert.assertEquals(true, Login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        Assert.assertEquals(false, Login.checkCellPhoneNumber("08966553"));
    }
    

    @Test
    public void testRegisterUser() {
        String result = Login.registerUser("Yasmine", "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Ipi");
        Assert.assertEquals(true, result.contains("Welcome"));
    }
    
    @Test
    public void testLoginUserCorrect() {
        Login.registerUser("Yasmine", "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Ipi");
        Assert.assertEquals(true, Login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginUserIncorrect() {
        Login.registerUser("Yasmine", "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Ipi");
        Assert.assertEquals(false, Login.loginUser("wrong", "wrong"));
    }
    
    @Test
    public void testReturnLoginStatusSuccess() {
        Login.registerUser("Yasmine", "kyl_1", "Ch&&sec@ke99!", "+27838968976", "Ipi");
        String result = Login.returnLoginStatus(true);
        Assert.assertEquals(true, result.contains("Welcome"));
    }
    
    @Test
    public void testReturnLoginStatusFailed() {
        String result = Login.returnLoginStatus(false);
        Assert.assertEquals(true, result.contains("failed"));
    }
}
