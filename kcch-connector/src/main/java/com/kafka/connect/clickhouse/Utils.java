/*
 * Copyright 2021 Kanthi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package com.kafka.connect.clickhouse;

import java.util.HashMap;
import java.util.Map;

class Utils {
public Map<String, String> parseTopicToTableMap(String input) {
    Map<String, String> topic2Table = new HashMap<>();
    boolean isInvalid = false;
    for (String str : input.split(",")) {
      String[] tt = str.split(":");

      if (tt.length != 2 || tt[0].trim().isEmpty() || tt[1].trim().isEmpty()) {
        LOGGER.error(
            Logging.logMessage(
                "Invalid {} config format: {}",
                SnowflakeSinkConnectorConfig.TOPICS_TABLES_MAP,
                input));
        return null;
      }

      String topic = tt[0].trim();
      String table = tt[1].trim();

      if (!isValidSnowflakeTableName(table)) {
        LOGGER.error(
            Logging.logMessage(
                "table name {} should have at least 2 "
                    + "characters, start with _a-zA-Z, and only contains "
                    + "_$a-zA-z0-9",
                table));
        isInvalid = true;
      }

      if (topic2Table.containsKey(topic)) {
        LOGGER.error(Logging.logMessage("topic name {} is duplicated", topic));
        isInvalid = true;
      }

      if (topic2Table.containsValue(table)) {
        // todo: support multiple topics map to one table ?
        LOGGER.error(Logging.logMessage("table name {} is duplicated", table));
        isInvalid = true;
      }
      topic2Table.put(tt[0].trim(), tt[1].trim());
    }
    if (isInvalid) {
      throw SnowflakeErrors.ERROR_0021.getException();
    }
    return topic2Table;
  }
}