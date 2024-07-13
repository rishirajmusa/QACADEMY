package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.AddCouponCommand;
import com.geektrust.backend.commands.AddProMembershipCommand;
import com.geektrust.backend.commands.AddProgrammesCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintBillCommand;
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

public class ApplicationConfig {
    private final ICouponRepository couponRepository = new CouponRepository();
    private final IProgrammeRepository programmeRepository = new ProgrammeRepository();
    private final ICartService cartService =new CartService(programmeRepository);
    private final ICouponService couponService = new CouponService(couponRepository, programmeRepository);
    private final ICartCalculatorService cartCalculatorService = new CartCalculatorService( programmeRepository, cartService, couponService);
    private final AddCouponCommand addCouponCommand = new AddCouponCommand(couponService);
    private final AddProgrammesCommand addProgrammesCommand = new AddProgrammesCommand(cartService);
    private final AddProMembershipCommand addProMembershipCommand = new AddProMembershipCommand(cartService);
    private final PrintBillCommand printBillCommand = new PrintBillCommand(cartCalculatorService);
    private final CommandInvoker commandInvoker =new CommandInvoker();


    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("ADD_PROGRAMME", addProgrammesCommand);
        commandInvoker.register("ADD_PRO_MEMBERSHIP", addProMembershipCommand);
        commandInvoker.register("APPLY_COUPON", addCouponCommand);
        commandInvoker.register("PRINT_BILL", printBillCommand);
        return commandInvoker;
    }
}
