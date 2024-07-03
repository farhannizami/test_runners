package com.example.test_runners.run;

import com.example.test_runners.TestRunnersApplication;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }
}
