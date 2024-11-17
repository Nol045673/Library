package org.example.library.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class ShedlockTableInitializer {

    private final JdbcTemplate jdbcTemplate;

    public ShedlockTableInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createTableIfNotExists() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS shedlock (
                name VARCHAR(64) NOT NULL,
                lock_until TIMESTAMP NOT NULL,
                locked_at TIMESTAMP NOT NULL,
                locked_by VARCHAR(255) NOT NULL,
                PRIMARY KEY (name)
            );
        """;
        jdbcTemplate.execute(createTableSql);
    }
}

