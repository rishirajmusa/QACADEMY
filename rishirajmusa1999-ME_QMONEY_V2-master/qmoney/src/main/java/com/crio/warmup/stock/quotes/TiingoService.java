
package com.crio.warmup.stock.quotes;
import java.time.LocalDate;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.HttpClientErrorException;

import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class TiingoService implements StockQuotesService {


  private RestTemplate restTemplate;

protected TiingoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {
        String response  = restTemplate.getForObject(buildUri(symbol,from,to),String.class);
    ObjectMapper objectMapper=new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    Candle[] result =null;
    List<Candle> testForException =null;
    try {
       result=objectMapper.readValue(response, TiingoCandle[].class);
       testForException = Arrays.asList(result);
    } catch (Exception e) {
      throw new StockQuoteServiceException("Invalid response from Tiingo API"); 
    }

  //   if (isDouble(testForException.get(0).getOpen())) {
  //     throw new StockQuoteServiceException("Invalid response from Tiingo API");
  // }

  if (testForException.get(0).getOpen() == null) {
      throw new StockQuoteServiceException("Tiingo response contains no data");
  }
    
     if(result == null){
      return new ArrayList<>();
    }
    return Arrays.asList(result);
  }
//   private static boolean isDouble(double value) {
//     // Check if the value has a fractional part
//     return value != (int) value;
// }


  public String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
    String TOKEN = "791a5a0744131bf03ade203248f71436ec945f54";
       String uriTemplate = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
            + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";
            String uri = uriTemplate.replace("$SYMBOL",symbol)
            .replace("$STARTDATE", startDate.toString())
            .replace("$ENDDATE", endDate.toString()).replace("$APIKEY",  TOKEN);
            return uri;
  }
  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Implement getStockQuote method below that was also declared in the interface.

  // Note:
  // 1. You can move the code from PortfolioManagerImpl#getStockQuote inside newly created method.
  // 2. Run the tests using command below and make sure it passes.
  //    ./gradlew test --tests TiingoServiceTest


  //CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Write a method to create appropriate url to call the Tiingo API.






}
