package com.kafka.connect.clickhouse.executor;

import com.kafka.connect.clickhouse.db.DbWriter;
import org.apache.kafka.connect.data.Struct;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Runnable object that will be called on
 * a schedule to perform the batch insert of
 * records to Clickhouse.
 */
public class ClickHouseBatchRunnable implements Runnable{

    private ConcurrentLinkedQueue<Struct> records;
    public ClickHouseBatchRunnable(ConcurrentLinkedQueue<Struct> records) {
        this.records = records;
    }

    @Override
    public void run() {
        System.out.println("*************** BULK INSERT TO CLICKHOUSE **************");
        DbWriter writer = new DbWriter();
        writer.insert(this.records);
    }
}
