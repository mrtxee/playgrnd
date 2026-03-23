package org.mrtxee.playgrnd.yandexti;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Условие: Есть большой файл логов (GB+). Каждая строка — JSON вида:
 * {"timestamp": 1620000000, "level": "ERROR", "message": "DB timeout"}
 * Требуется:
 * - найти топ‑5 самых частых уровней логов (level);
 * - для каждого уровня посчитать среднее время между событиями.
 * Ограничения: файл не помещается в память целиком.
 */
public class LogAnalyzer {
  private static final ObjectMapper mapper = new ObjectMapper();

  public Map<String, Long> topLevels(String logFilePath, int topN) throws Exception {
    Map<String, Long> levelCounts = new HashMap<>();

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(logFilePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        JsonNode node = mapper.readTree(line);
        String level = node.get("level").asText();

        // Если ключ есть: сумма (старое + 1), если нет: ставит 1. Аналог инкремента.
        levelCounts.merge(level, 1L, (aLong, aLong2) -> aLong + aLong2);
        // aLong  : Существующее значение в Map для ключа level.
        // aLong2 : Новое значение из аргумента merge (здесь 1L).

        //levelCounts.put(level, levelCounts.getOrDefault(level, 1L) + 1L);

      }
    }

    return levelCounts.entrySet().stream()
        // Сортировка по убыванию значений (количество логов)
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        // Оставляем только первые topN элементов
        .limit(topN)
        .collect(Collectors.toMap(
            Map.Entry::getKey,          // Ключ результирующей мапы
            Map.Entry::getValue,        // Значение результирующей мапы
            (e1, e2) -> e1,  // Функция слияния дублей (не сработает, ключи уникальны)
            LinkedHashMap::new          // Фабрика мапы: сохраняет порядок сортировки
            // (иначе будет случайный)
        ));
  }

}
