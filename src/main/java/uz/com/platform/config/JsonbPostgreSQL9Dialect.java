package uz.com.platform.config;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

public class JsonbPostgreSQL9Dialect extends PostgreSQL9Dialect {
    public JsonbPostgreSQL9Dialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
