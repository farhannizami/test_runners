package com.example.test_runners.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
        // The sql can be written in a string var and the use sql(s).query...
    }
    public Optional<Run> findById(Integer id){
        return jdbcClient.sql(
                "select * from run where id=:id"
        ).param("id",id).query(Run.class).optional();
    }
    public void create(Run run){
        var created = jdbcClient.sql(
                "insert into run(id,title,start_on,complete_on,miles,location)"
                + " values(?,?,?,?,?,?)"
        ).params(run.id(),run.title(),run.startOn(),run.completeOn(),run.miles(),run.location().toString()).update();
        Assert.state(created==1,"Failed to create run "+run.title());
    }
    public void update(Run run, Integer id){
        var updated = jdbcClient.sql(
                "update run set title=?, start_on=?, complete_on=?, miles=?, location=? where id=?"
        ).params(run.title(),run.startOn(),run.completeOn(),run.miles(),run.location().toString(),id).update();
        Assert.state(updated==1,"Failed to update run "+run.title()); // updated returns how many rows are updated
    }
    public void delete(Integer id){
        var deleted = jdbcClient.sql(
                "delete from run where id=?"
        ).param(id).update();
        Assert.state(deleted==1,"Failed to update run "+id);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }
    public void saveAll(List<Run> runs){
        //runs.stream().forEach(this::create);
        for(Run run: runs){
            create(run);// easy to understand
        }
    }
}
