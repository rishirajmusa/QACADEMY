
package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;

import com.crio.warmup.stock.quotes.*;
import com.crio.warmup.stock.portfolio.PortfolioManagerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerApplication {
  static RestTemplate restTemplate=new RestTemplate();
  // static String URL = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?startDate=$STARTDATE&endDate=$ENDDATE&token=$KEY";

  // static String TOKEN = "8c54f71a4595146aec4c5cd4e8da04c4e6b6b022";

  public static String getToken(){
    String TOKEN = "791a5a0744131bf03ade203248f71436ec945f54";

     return TOKEN;
  }
  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Task:
  //       - Read the json file provided in the argument[0], The file is available in the classpath.
  //       - Go through all of the trades in the given file,
  //       - Prepare the list of all symbols a portfolio has.
  //       - if "trades.json" has trades like
  //         [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  //         Then you should return ["MSFT", "AAPL", "GOOGL"]
  //  Hints:
  //    1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  //       Check if they are of any help to you.
  //    2. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException
  {
   
    try {
      PortfolioTrade[] portfolioTrades = getPortfolioTradesFromFile(args[0]); 
      return Arrays.stream(portfolioTrades).map(PortfolioTrade::getSymbol)
      .collect(Collectors.toList());
    }   
     catch (ArrayIndexOutOfBoundsException e) {
  System.out.println("ArrayIndexOutOfBoundsException happend in mainReadFile");   
 }
    return null;
     }
    
    
      



  private static PortfolioTrade[] getPortfolioTradesFromFile(String file)throws URISyntaxException, IOException {
    String content = readFileContent(file);
    ObjectMapper om=getObjectMapper();
    PortfolioTrade[] tr=om.readValue(content,PortfolioTrade[].class );
    return tr;
  }


  // TODO: CRIO_TASK_MODULE_REST_API
  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.

  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

private static String readFileContent(String filename) throws URISyntaxException, IOException{
  return new String(Files.readAllBytes(resolveFileFromResources(filename).toPath()));

}
  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/home/crio-user/workspace/rishirajmusa1999-ME_QMONEY_V2/qmoney/bin/main/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@1573f9fc";
     String functionNameFromTestFileInStackTrace = "mainReadFile()";
     String lineNumberFromTestFileInStackTrace = "29";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }


  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException,JsonProcessingException {
    try {
      PortfolioTrade[] portfolioTrades = getPortfolioTradesFromFile(args[0]);
    LocalDate enddate = LocalDate.parse(args[1]);
    return Arrays.stream(portfolioTrades).map(trade -> {
     List<Candle> tingoCandles = null;
    try {
      tingoCandles = CandlesFromFactory(enddate, trade);
    } catch (JsonProcessingException | StockQuoteServiceException e) {
      e.printStackTrace();
    }
     FilteredAndSortedCandles fil=new FilteredAndSortedCandles();
     List<Candle> sortedAndFilteredCandles = 
     fil.filteredAndSortedCandles(trade, enddate, tingoCandles);

    return new TotalReturnsDto(trade.getSymbol(),ClosingPrice(fil ,sortedAndFilteredCandles));
    }).sorted(Comparator.comparing(TotalReturnsDto::getClosingPrice))
    .map(TotalReturnsDto::getSymbol).collect(Collectors.toList());
    }   
     catch (NoSuchElementException e) {
  System.out.println("Exception happend in mainReadQuotes");    }
    return null;

    }

   static Double ClosingPrice(FilteredAndSortedCandles fil, List<Candle> tingoCandles) {
    return fil.getLastCandle(tingoCandles).getClose();
  }

   static List<Candle> CandlesFromFactory(LocalDate enddate, PortfolioTrade trade) 
   throws JsonProcessingException, StockQuoteServiceException{
    String provider = "alphavantage";
    String symbol =trade.getSymbol();
    LocalDate from = trade.getPurchaseDate();
    StockQuotesService stockQuotesService = StockQuoteServiceFactory.INSTANCE.getService(provider, restTemplate);
    return stockQuotesService.getStockQuote(symbol, from, enddate);
  }


  // private static TiingoCandle[] getTingoCandles( LocalDate enddate,PortfolioTrade trade) {
  //   RestTemplate restTemplate = new RestTemplate();
  //   if(trade.getPurchaseDate().compareTo(enddate)>=0){
  //     throw new RuntimeException();
  //   }
  //   String uri = urlbuilder(enddate, trade, URL , TOKEN);
  //   TiingoCandle[] tingoCandles=restTemplate.getForObject(uri, TiingoCandle[].class);

  //   return tingoCandles;
  // }


  // private static String urlbuilder(LocalDate enddate, PortfolioTrade trade, String url ,String TOKEN) {
  //   String uri=url.replace("$SYMBOL", trade.getSymbol())
  //   .replace("$STARTDATE", trade.getPurchaseDate().toString())
  //   .replace("$ENDDATE", enddate.toString()).replace("$KEY",  TOKEN);
  //   System.out.println(uri);
  //   return uri;
  // }

  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile
  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    File file=resolveFileFromResources(filename);
    ObjectMapper om=getObjectMapper();
    PortfolioTrade[] tr=om.readValue(file,PortfolioTrade[].class );
    List<PortfolioTrade> newlist=Stream.of(tr).collect(Collectors.toList());
     return newlist;}
  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.




  // TODO:
  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest
  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
    Double OpenPriceOnStartDate = candles.get(0).getOpen();;
  return OpenPriceOnStartDate;
  }
  


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
    Double ClosingPriceOnEndDate = candles.get(candles.size()-1).getClose();
  return ClosingPriceOnEndDate;
  }


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) 
  throws JsonProcessingException {
  //  PortfolioManagerImpl a = PortfolioManagerImpl.getInstance(); // get an object of PortfolioManagerImpl
    // call the public static method of PortfolioManagerImpl
    List<Candle> tingoCandles =null;
    try{
   tingoCandles =CandlesFromFactory(endDate, trade);
    }catch(Exception e){
      try {
        throw new StockQuoteServiceException("Invalid response from Tiingo API");
      } catch (StockQuoteServiceException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } 
    }
    
    return tingoCandles;
  }

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
        PortfolioTrade[] portfolioTrades = getPortfolioTradesFromFile(args[0]);
        LocalDate enddate = LocalDate.parse(args[1]);
      
         return Arrays.stream(portfolioTrades).map(trade -> {
          LocalDate startDate = trade.getPurchaseDate();
          RestTemplate restTemplate=new RestTemplate();
          String symbol = trade.getSymbol();
         StockQuotesService stockQuotesService = 
         StockQuoteServiceFactory.INSTANCE
         .getService("alphavantage", restTemplate);
          // String uri =
          // PortfolioManagerFactory.getPortfolioManager(restTemplate)
          // .buildUri(symbol, startDate, enddate);

         List<Candle> tingoCandles =null;
        try {
          tingoCandles = stockQuotesService.getStockQuote(symbol, startDate, enddate);
        } catch (JsonProcessingException | StockQuoteServiceException e) {
          e.printStackTrace();
        }
        //  PortfolioManagerFactory.getPortfolioManager(restTemplate)
        //  .getTingoCandles(uri, startDate, enddate);
        Double buyPrice = tingoCandles.get(0).getOpen();
        Double sellPrice =tingoCandles.get(tingoCandles.size()-1).getClose();
          AnnualizedReturn totalReturns = calculateAnnualizedReturns(enddate, trade, buyPrice, sellPrice);
          return totalReturns;
        }).sorted(Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed())
        .collect(Collectors.toList());
        // List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
        // LocalDate enddate = LocalDate.parse(args[1]);
        //   RestTemplate restTemplate=new RestTemplate();
        //   return AnnualizedReturnFromFactory(portfolioTrades, enddate, restTemplate);
          
      }

   static List<AnnualizedReturn> AnnualizedReturnFromFactory(List<PortfolioTrade> portfolioTrades,
      LocalDate enddate, RestTemplate restTemplate) throws StockQuoteServiceException {
    return PortfolioManagerFactory.getPortfolioManager(restTemplate).calculateAnnualizedReturn(portfolioTrades, enddate);
  }
    
  

  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {
       double totalReturn = (sellPrice - buyPrice) / buyPrice;
       double total_num_years = 
       ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate)/365.24;
       double annualized_returns = Math.pow((1 + totalReturn),(1 / total_num_years))-1;
      return new AnnualizedReturn(trade.getSymbol(), annualized_returns, totalReturn);
  }



  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.
  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    return "https://api.tiingo.com/tiingo/daily/"+trade.getSymbol()+"/prices?startDate="+trade.getPurchaseDate().toString()+"&endDate="+endDate+"&token="+token;
  }




















  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
        List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
        LocalDate enddate = LocalDate.parse(args[1]);
          RestTemplate restTemplate=new RestTemplate();
          return AnnualizedReturnFromFactory(portfolioTrades, enddate, restTemplate);

    }




  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());




    printJsonObject(mainCalculateReturnsAfterRefactor(args));
  }
}

