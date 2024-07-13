
package com.crio.warmup.stock.portfolio;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.lang.annotation.Retention;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.quotes.FilteredAndSortedCandles;
import com.crio.warmup.stock.quotes.StockQuoteServiceFactory;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.crio.warmup.stock.quotes.StockQuotesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {




public  RestTemplate restTemplate;
  private StockQuotesService stockQuotesService ;
  private int numThreads ;
  protected PortfolioManagerImpl(int numThreads) {
    this.numThreads = numThreads;
}

  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
  protected PortfolioManagerImpl(StockQuotesService stockQuotesService) {
    this.stockQuotesService = stockQuotesService;
  }
  //TODO: CRIO_TASK_MODULE_REFACTOR
  // 1. Now we want to convert our code into a module, so we will not call it from main anymore.
  //    Copy your code from Module#3 PortfolioManagerApplication#calculateAnnualizedReturn
  //    into #calculateAnnualizedReturn function here and ensure it follows the method signature.
  // 2. Logic to read Json file and convert them into Objects will not be required further as our
  //    clients will take care of it, going forward.

  // Note:
  // Make sure to exercise the tests inside PortfolioManagerTest using command below:

  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {

          return stockQuotesService.getStockQuote(symbol, from, to);
       
  }

  private AnnualizedReturn getAnnualizedReturn(PortfolioTrade trade,
      LocalDate endDate ) throws StockQuoteServiceException {
        AnnualizedReturn annualizedReturn;
            String Symbol =trade.getSymbol();
      LocalDate startDate = trade.getPurchaseDate();

     try{
      List<Candle> stockStartToendDate ;
      stockStartToendDate = getStockQuote(Symbol, startDate, endDate);
      // stockQuotesService = StockQuoteServiceFactory.INSTANCE.getService("alphavantage", restTemplate);
      // stockStartToendDate=stockQuotesService.getStockQuote(Symbol, startDate, endDate);
      Candle stockStartDate = stockStartToendDate.get(0);
      Candle stockEndDate = stockStartToendDate.get(stockStartToendDate.size()-1);
      Double buyPrice = stockStartDate.getOpen();
      Double sellPrice =stockEndDate.getClose();
      Double totalReturns =(sellPrice-buyPrice)/buyPrice;
      Double numYears = (double) ChronoUnit.DAYS.between(startDate, endDate)/365.24250;
      
      // System.out.println("sellPrice = "+ sellPrice);
      // System.out.println("buyprice = "+ buyPrice);
      // System.out.println("numYears" +numYears);
      // System.out.println("startDate is  = "+ startDate);
      // System.out.println("endDate is  = "+ endDate);
      double annualizedReturns =Math.pow((1 + totalReturns), (1/numYears)) - 1;
      //System.out.println("annualizedReturns = "+ annualizedReturns);
      annualizedReturn = new AnnualizedReturn(Symbol, annualizedReturns,
      totalReturns);
     }catch(JsonProcessingException e){
      annualizedReturn = new AnnualizedReturn(Symbol, Double.NaN, Double.NaN);
     }
   
    return annualizedReturn;
     
      }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate) throws StockQuoteServiceException {
    List<AnnualizedReturn> response = new ArrayList<>();
    for(PortfolioTrade trade : portfolioTrades){  
      response.add(getAnnualizedReturn(trade, endDate));
    }
    return response.stream()
    .sorted(getComparator())
    .collect(Collectors.toList());
}
  private Comparator<AnnualizedReturn> getComparator(){
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturnParallel(
      List<PortfolioTrade> portfolioTrades, LocalDate endDate, int numThreads)
      throws InterruptedException, StockQuoteServiceException {
    // TODO Auto-generated method stub
        ExecutorService ec= Executors.newFixedThreadPool(numThreads);
        List<Future<AnnualizedReturn>> futurelist = new ArrayList<>();

    for (PortfolioTrade trade : portfolioTrades) {
      futurelist.add(ec.submit(new StockQuoteTask(trade, endDate)));
    }

    List<AnnualizedReturn> response = new ArrayList<>();
    for (Future<AnnualizedReturn> future : futurelist) {
        try {
            response.add(future.get());
        } catch (Exception e) {
          throw new StockQuoteServiceException("Invalid response from Tiingo API"); 
        }
    }

    ec.shutdown();
    return response.stream()
    .sorted(getComparator())
    .collect(Collectors.toList());
  }

 
  private class StockQuoteTask implements Callable<AnnualizedReturn> {
    // TODO Auto-generated method stub
    private final PortfolioTrade trade;
    private final LocalDate endDate;

    public StockQuoteTask(PortfolioTrade trade, LocalDate endDate) {
        this.trade = trade;
        this.endDate = endDate;
  }

    @Override
    public AnnualizedReturn call() throws Exception {
      // TODO Auto-generated method stub
      return getAnnualizedReturn(trade, endDate);
    }
  
}  
}

