
package com.crio.warmup.stock.quotes;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;
import com.crio.warmup.stock.dto.AlphavantageCandle;
import com.crio.warmup.stock.dto.AlphavantageDailyResponse;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map.Entry;

public class AlphavantageService implements StockQuotesService {
  private RestTemplate restTemplate;
  protected AlphavantageService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
 //Alphavantage Key = HVTM2RKXDMBEW8E1
  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {
    // TODO Auto-generated method stub

    List<Candle> candleList =null;
    // Build the url to communicate with the third-party
    String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+symbol
        + "&outputsize=full&apikey=HVTM2RKXDMBEW8E1";
       // https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&outputsize=full&apikey=demo

      String response = restTemplate.getForObject(url, String.class);

      ObjectMapper objectMapper=new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());

      // AlphavantageCandle[] result =null;
      // List<AlphavantageCandle> testForException =null;
      // try {
      //    result=objectMapper.readValue(response, AlphavantageCandle[].class);
      //    testForException = Arrays.asList(result);
      // } catch (Exception e) {
      //   throw new StockQuoteServiceException("Invalid response from Tiingo API"); 
      // }
  
    //System.out.println("Size of response String in Alphavantage ="+response.toString());
      AlphavantageDailyResponse alphavantageDailyResponse = 
      objectMapper.readValue(response, AlphavantageDailyResponse.class);
    //   if (alphavantageDailyResponse.getCandles() == null) {
    //     throw new StockQuoteServiceException("Alphavantage response contains no data");
    // }
      if (alphavantageDailyResponse.getCandles() == null){
        // System.out.println("alphavantageDailyResponse.getCandles() is giving null");
        // System.out.println("So I am Calling Tiingo");
        TiingoService tService=new TiingoService(restTemplate);
        List<Candle> candle=tService.getStockQuote(symbol, from, to);
      
        return candle;
      }
      alphavantageDailyResponse.getCandles().entrySet()
      .forEach(entry -> entry.getValue().setDate(entry.getKey()));

       candleList = alphavantageDailyResponse.getCandles().entrySet().stream()
       .filter(entry -> entry.getKey().isAfter(from.minusDays(1)) && entry.getKey().isBefore(to.plusDays(1)))
       .map(Map.Entry::getValue).collect(Collectors.toList());


       Collections.reverse(candleList);
    // System.out.println(candleList.toString());
    // Return the list of candles
    return candleList;
  }



  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Implement the StockQuoteService interface as per the contracts. Call Alphavantage service
  //  to fetch daily adjusted data for last 20 years.
  //  Refer to documentation here: https://www.alphavantage.co/documentation/
  //  --
  //  The implementation of this functions will be doing following tasks:
  //    1. Build the appropriate url to communicate with third-party.
  //       The url should consider startDate and endDate if it is supported by the provider.
  //    2. Perform third-party communication with the url prepared in step#1
  //    3. Map the response and convert the same to List<Candle>
  // 4. If the provider does not support startDate and endDate, then the implementation
  //       should also filter the dates based on startDate and endDate. Make sure that
  //       result contains the records for for startDate and endDate after filtering.
  //    5. Return a sorted List<Candle> sorted ascending based on Candle#getDate
  //  IMP: Do remember to write readable and maintainable code, There will be few functions like
  //    Checking if given date falls within provided date range, etc.
  //    Make sure that you write Unit tests for all such functions.
  //  Note:
  //  1. Make sure you use {RestTemplate#getForObject(URI, String)} else the test will fail.
  //  2. Run the tests using command below and make sure it passes:
  //    ./gradlew test --tests AlphavantageServiceTest
  //CHECKSTYLE:OFF
    //CHECKSTYLE:ON
  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  1. Write a method to create appropriate url to call Alphavantage service. The method should
  //     be using configurations provided in the {@link @application.properties}.
  //  2. Use this method in #getStockQuote.

}

