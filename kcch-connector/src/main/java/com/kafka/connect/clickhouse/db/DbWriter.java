package com.kafka.connect.clickhouse.db;

import com.clickhouse.client.*;
import com.clickhouse.jdbc.ClickHouseConnection;
import com.clickhouse.jdbc.ClickHouseDataSource;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DbWriter {
    //ClickHouseNode server;
    ClickHouseConnection conn;


    public DbWriter() {
        try {
            String url = "jdbc:ch://localhost/test";
            ClickHouseDataSource dataSource = new ClickHouseDataSource(url, new Properties());
            this.conn = dataSource.getConnection("default", "password");
        } catch(Exception e) {
            // Error initializing connection.
        }
    }

    private ClickHouseNode getConnection() {
        ClickHouseCredentials credentials = ClickHouseCredentials.fromUserAndPassword("admin", "root");
        return ClickHouseNode.builder().credentials(credentials).database("test").port(8123).host("localhost").build();

    }

    public String getInsertQuery(String tableName, int numFields) {
        StringBuffer insertQuery = new StringBuffer().append("insert into ")
                .append(tableName).append(" values(");
        for(int i = 0; i < numFields; i++) {
            insertQuery.append("?");
            if (i == numFields - 1) {
                insertQuery.append(")");
            } else {
                insertQuery.append(",");
            }
        }
        return insertQuery.toString();
    }

    public void insert(String table, Struct afterValue, List<Field> fields){

        try {
//            PreparedStatement ps = this.conn.prepareStatement(sql);
//            for(Field f: fields) {
//                ps.
//
//            }
        } catch(Exception e) {

        }
        try (PreparedStatement ps = this.conn.prepareStatement("insert into mytable values(trim(?),?,?)")) {

            int index = 1;
            for(Field f: fields) {
                Schema.Type fieldType = f.schema().type();
                Object value = afterValue.get(f);

                if(fieldType == Schema.Type.STRING) {
                    ps.setString(index, (String) value);
                } else if(fieldType == Schema.INT8_SCHEMA.type() ||
                            fieldType == Schema.INT16_SCHEMA.type() ||
                            fieldType == Schema.INT32_SCHEMA.type()) {
                    ps.setInt(index, (Integer) value);
                } else if(fieldType == Schema.FLOAT32_SCHEMA.type() ||
                            fieldType == Schema.FLOAT64_SCHEMA.type()) {
                    ps.setFloat(index, (Float) value);
                } else if()
            }
            ps.setString(1, "test"); // id
            ps.setObject(2, LocalDateTime.now()); // timestamp
            ps.setString(3, null); // description
            ps.addBatch(); // append parameters to the query

            ps.executeBatch(); // issue the composed query: insert into mytable values(...)(...)...(...)
        } catch(Exception e) {

        }

        table = "test_hello2";
        String insertQuery = MessageFormat.format("insert into {0} {1} values({2})",
                table, "(id, message)", "1, 'hello'");
//        if(this.server != null) {
//            CompletableFuture<List<ClickHouseResponseSummary>> future = ClickHouseClient.send(this.server, insertQuery);
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        } else {
//            // Error .
//        }

    }
}
