package com.amin.technom.config;

import org.hibernate.dialect.MySQLDialect;

public class MySQLCustomDialect extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci";
    }
}