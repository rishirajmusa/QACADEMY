package com.crio.warmup.stock.quotes;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class FilteredAndSortedCandles {
   public List<Candle> filteredAndSortedCandles(PortfolioTrade trade, LocalDate endDate, List<Candle> candles) {
        return candles.stream().filter(candle ->
        trade.getPurchaseDate().atStartOfDay().minus(1, SECONDS).isBefore(candle.getDate().atStartOfDay())
        && endDate.plus(1, DAYS).atStartOfDay().isAfter(candle.getDate().atStartOfDay()))
        .sorted(Comparator.comparing(Candle::getDate)).collect(Collectors.toList());
      }
public Candle getLastCandle(List<Candle> sortedAndFilteredCandles) {
        return sortedAndFilteredCandles.get(sortedAndFilteredCandles.size()-1);
}
}
