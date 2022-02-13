/*
 * Copyright (c)  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.kafka.connect.clickhouse;


import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;


public class ClickHouseSinkConnector extends SinkConnector {

    @Override
    public String version() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start(Map<String, String> props) {
        // The following activities need to be done here
        // 1. Load configuration (Kafka and Clickhouse specific)
        // 2. Create a connection to Clickhouse.
        
    }

    @Override
    public Class<? extends Task> taskClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ConfigDef config() {
        // TODO Auto-generated method stub
        return null;
    }

    
}