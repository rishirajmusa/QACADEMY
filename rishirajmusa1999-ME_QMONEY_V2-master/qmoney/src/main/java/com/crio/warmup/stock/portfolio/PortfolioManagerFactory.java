
package com.crio.warmup.stock.portfolio;

import java.time.LocalDate;
import java.util.List;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.quotes.StockQuoteServiceFactory;
import com.crio.warmup.stock.quotes.StockQuotesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.crio.warmup.stock.quotes.StockQuoteServiceFactory;
import com.crio.warmup.stock.quotes.StockQuotesService;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerFactory {


  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Implement the method to return new instance of PortfolioManager.
  //  Remember, pass along the RestTemplate argument that is provided to the new instance.

  public static PortfolioManager getPortfolioManager(RestTemplate restTemplate) {
    PortfolioManager portfoliomanager= new PortfolioManagerImpl(restTemplate);
    
    return portfoliomanager;
    };

  public static PortfolioManager getPortfolioManager(String provider,
      RestTemplate restTemplate) {
    if(provider == null){
      StockQuotesService stockQuoteService = StockQuoteServiceFactory.INSTANCE
      .getService(provider, restTemplate);
PortfolioManager portfolioManager = new PortfolioManagerImpl(stockQuoteService);
return portfolioManager;
    }
      StockQuotesService stockQuoteService = StockQuoteServiceFactory.INSTANCE
        .getService(provider, restTemplate);
  PortfolioManager portfolioManager = new PortfolioManagerImpl(stockQuoteService);
 return portfolioManager;
    }
    
  }

  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Implement the method to return new instance of PortfolioManager.
  //  Steps:
  //    1. Create appropriate instance of StoockQuoteService using StockQuoteServiceFactory and then
  //       use the same instance of StockQuoteService to create the instance of PortfolioManager.
  //    2. Mark the earlier constructor of PortfolioManager as @Deprecated.
  //    3. Make sure all of the tests pass by using the gradle command below:
  //       ./gradlew test --tests PortfolioManagerFactory


  

