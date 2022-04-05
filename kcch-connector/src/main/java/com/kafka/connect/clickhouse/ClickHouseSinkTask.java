package com.kafka.connect.clickhouse;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import com.kafka.connect.clickhouse.converters.ClickHouseConverter;
import com.kafka.connect.clickhouse.executor.ClickHouseBatchExecutor;
import com.kafka.connect.clickhouse.executor.ClickHouseBatchRunnable;
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

    private ClickHouseBatchExecutor executor;
    private ClickHouseBatchRunnable runnable;
    private ConcurrentLinkedQueue<Struct> records;

    @Override
    public String version() {
        return "1.0.1";
    }

    @Override
    public void start(Map<String, String> props) {
        LOGGER.debug("CLICKHOUSE TASK started");

        this.records = new ConcurrentLinkedQueue();
        this.executor = new ClickHouseBatchExecutor(2);
        this.runnable = new ClickHouseBatchRunnable(this.records);

        this.executor.scheduleAtFixedRate(this.runnable, 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        ClickHouseConverter converter = new ClickHouseConverter();
        LOGGER.debug("CLICKHOUSE received records" + records.size());
        BufferedRecords br = new BufferedRecords();
        for (SinkRecord record: records) {
            this.records.add(converter.convert(record));
        }
    }

    @Override
    public void stop() {
        LOGGER.debug("CLICKHOUSE TASK stopped");
        if(this.executor != null) {
            this.executor.shutdown();
        }
    }
}
