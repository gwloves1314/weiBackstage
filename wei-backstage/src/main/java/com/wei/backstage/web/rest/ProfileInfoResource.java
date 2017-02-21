package com.wei.backstage.web.rest;

import com.wei.backstage.config.DefaultProfileUtil;
import com.wei.backstage.config.JHipsterProperties;
import com.wei.backstage.domain.ClusterTask;
import com.wei.backstage.service.ReceiveQSubTaskService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Resource to return information about the currently running Spring profiles.
 */
@RestController
@RequestMapping("/api")
public class ProfileInfoResource {

    @Inject
    private Environment env;

    @Inject
    private JHipsterProperties jHipsterProperties;

    @Inject
    private ReceiveQSubTaskService receiveQSubTaskService;

    @RequestMapping(value = "/queryClusterTask" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClusterTask> queryClusterTask(@RequestParam Long jobId){
        return new ResponseEntity<ClusterTask>(receiveQSubTaskService.queryClusterTask(jobId), HttpStatus.OK);
    }
}
