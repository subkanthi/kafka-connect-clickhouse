package com.kafka.connect.clickhouse.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ClickHouseBatchExecutor extends ScheduledThreadPoolExecutor {

    public ClickHouseBatchExecutor(int corePoolSize) {
        super(corePoolSize);
    }
}
