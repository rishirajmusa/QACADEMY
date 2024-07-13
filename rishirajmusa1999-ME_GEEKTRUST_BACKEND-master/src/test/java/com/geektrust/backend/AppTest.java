package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }



    @Test
    public void Application_Test() throws Exception{
        // Arrange
        List<String> arguments1 = new ArrayList<>(List.of("sample_input/input1.txt"));
        List<String> arguments2 = new ArrayList<>(List.of("sample_input/input2.txt"));
        List<String> arguments3 = new ArrayList<>(List.of("sample_input/input3.txt"));
        List<String> arguments4 = new ArrayList<>(List.of("sample_input/input4.txt"));
        List<String> arguments5 = new ArrayList<>(List.of("sample_input/input5.txt"));
        List<String> arguments6 = new ArrayList<>(List.of("sample_input/input6.txt"));
        List<String> arguments7 = new ArrayList<>(List.of("sample_input/input7.txt"));
        String expectedOutPut = "SUB_TOTAL 10000.00\n"+
        "COUPON_DISCOUNT DEAL_G20 2000.00\n"+
        "TOTAL_PRO_DISCOUNT 0.00\n"+
        "PRO_MEMBERSHIP_FEE 0.00\n"+
        "ENROLLMENT_FEE 0.00\n"+
        "TOTAL 8000.00\n"+
        "SUB_TOTAL 13000.00\n"+
        "COUPON_DISCOUNT B4G1 2500.00\n"+
        "TOTAL_PRO_DISCOUNT 0.00\n"+
        "PRO_MEMBERSHIP_FEE 0.00\n"+
        "ENROLLMENT_FEE 0.00\n"+
        "TOTAL 10500.00\n"+
        "SUB_TOTAL 8555.00\n"+
        "COUPON_DISCOUNT DEAL_G5 427.75\n"+
        "TOTAL_PRO_DISCOUNT 145.00\n"+
        "PRO_MEMBERSHIP_FEE 200.00\n"+
        "ENROLLMENT_FEE 0.00\n"+
        "TOTAL 8127.25\n"+
        "SUB_TOTAL 5615.00\n"+
        "COUPON_DISCOUNT NONE 0.00\n"+
        "TOTAL_PRO_DISCOUNT 85.00\n"+
        "PRO_MEMBERSHIP_FEE 200.00\n"+
        "ENROLLMENT_FEE 500.00\n"+
        "TOTAL 6115.00\n"+
        "SUB_TOTAL 5000.00\n"+
        "COUPON_DISCOUNT DEAL_G5 250.00\n"+
        "TOTAL_PRO_DISCOUNT 0.00\n"+
        "PRO_MEMBERSHIP_FEE 0.00\n"+
        "ENROLLMENT_FEE 500.00\n"+
        "TOTAL 5250.00\n"+
        "SUB_TOTAL 17790.00\n"+
        "COUPON_DISCOUNT B4G1 2475.00\n"+
        "TOTAL_PRO_DISCOUNT 410.00\n"+
        "PRO_MEMBERSHIP_FEE 200.00\n"+
        "ENROLLMENT_FEE 0.00\n"+
        "TOTAL 15315.00\n"+
        "SUB_TOTAL 10000.00\n"+
        "COUPON_DISCOUNT DEAL_G20 2000.00\n"+
        "TOTAL_PRO_DISCOUNT 0.00\n"+
        "PRO_MEMBERSHIP_FEE 0.00\n"+
        "ENROLLMENT_FEE 0.00\n"+
        "TOTAL 8000.00";
        // Act   
        App.run(arguments1);
        App.run(arguments2);
        App.run(arguments3);
        App.run(arguments4);
        App.run(arguments5);
        App.run(arguments6);
        App.run(arguments7);
        //Assert
        Assertions.assertEquals(expectedOutPut,outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

}
