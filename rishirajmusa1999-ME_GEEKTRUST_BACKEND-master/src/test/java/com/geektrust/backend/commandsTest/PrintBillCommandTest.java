package com.geektrust.backend.commandsTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.commands.PrintBillCommand;
import com.geektrust.backend.dtos.BillDto;
import com.geektrust.backend.repositories.CouponRepository;
import com.geektrust.backend.repositories.ICouponRepository;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import com.geektrust.backend.services.CartCalculatorService;
import com.geektrust.backend.services.CartService;
import com.geektrust.backend.services.CouponService;
import com.geektrust.backend.services.ICartCalculatorService;
import com.geektrust.backend.services.ICartService;
import com.geektrust.backend.services.ICouponService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PrintBillCommandTest")
public class PrintBillCommandTest {


    private final PrintStream standardOut = System.out;
    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream outPutStreamCaptor = new ByteArrayOutputStream();

    private IProgrammeRepository programmeRepository = new ProgrammeRepository();
    private ICartService cartService = new CartService(programmeRepository);
    private ICouponRepository couponRepository = new CouponRepository();
    private ICouponService couponService = new CouponService(couponRepository,programmeRepository);
    private ICartCalculatorService cartCalculatorService=
    new CartCalculatorService( programmeRepository,cartService, couponService);
    private PrintBillCommand printBillCommand =new PrintBillCommand(cartCalculatorService);

    @BeforeEach
    void setup(){
        System.setOut(new PrintStream(outPutStreamCaptor));
        System.setErr(new PrintStream(outPutStreamCaptor));
    }


    @Test
    @DisplayName("execute method of PrintBillCommand Should Return message If No Program Added")
    public void execute_ShoudldPrintError_GivenNoProgram(){
        //Arrange
        List<String> tokens =List.of("PRINT_BILL");
        String expected = "No Programs Added, Please Add Programs"+"\n"
        +"TOTAL 0.00";
        //Act
        printBillCommand.execute(tokens);
        //Assert
        Assertions.assertEquals(expected.strip(), outPutStreamCaptor.toString().strip());
    }

    @Test
    @DisplayName("execute method of PrintBillCommand Should Return Bill")
    public void execute_ShoudldPrintBill(){
        //Arrange
        couponService.addCoupon("DEAL_G20");
        cartService.addProgrammesToCart("DEGREE", "2");
        List<String> tokens =List.of("PRINT_BILL");
        Map<String,Double> couponWithDiscount = new HashMap<String,Double>(){
            {
                put("DEAL_G20", 2000.00);
            }
        };
        BillDto expected =new BillDto(10000, couponWithDiscount, 0, 0, 0, 8000);
        //Act
        printBillCommand.execute(tokens);
        //Assert
        Assertions.assertEquals(expected.toString().strip(), outPutStreamCaptor.toString().strip());
    }


    @AfterEach
    void tearDown(){
        System.setOut(standardOut);
        System.setErr(standardErr);

    }
    
}
