package com.kafka.connect.clickhouse.converters.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;

import java.util.List;

public class KafkaConnectRecord {
    @Getter @Setter
    private String tableName;

    @Getter @Setter
    private Struct after;

    @Getter @Setter
    private List<Field> fields;


    public KafkaConnectRecord(String tableName, Struct after, List<Field> fields) {
        this.tableName = tableName;
        this.after = after;
        this.fields = fields;
    }
}
