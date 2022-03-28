package com.kafka.connect.clickhouse;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kafka.connect.clickhouse.converters.ClickHouseConverter;
import com.kafka.connect.clickhouse.metadata.KafkaSchemaRecordType;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <p>Creates sink service instance, takes records loaded from those 
 * Kafka partitions and ingests to
 * ClickHouse via Sink service
 */
public class ClickHouseSinkTask extends SinkTask{

    private static final Logger LOGGER = LoggerFactory.getLogger(ClickHouseSinkConnector.class);

    @Override
    public String version() {
        return null;
    }

    @Override
    public void start(Map<String, String> props) {
        LOGGER.debug("CLICKHOUSE TASK started");
        
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        LOGGER.debug("CLICKHOUSE received records" + records.size());
        BufferedRecords br = new BufferedRecords();
        for (SinkRecord record: records) {
            new ClickHouseConverter().convert(record);
        }

    }



    @Override
    public void stop() {
        LOGGER.debug("CLICKHOUSE TASK stopped");
    }
}
