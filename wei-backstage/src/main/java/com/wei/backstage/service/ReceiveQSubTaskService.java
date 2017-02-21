package com.wei.backstage.service;


import com.wei.backstage.domain.ClusterTask;
import com.wei.backstage.repository.ClusterTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;


@Service
@Component
public class ReceiveQSubTaskService {

    private static final Logger LOG = LoggerFactory.getLogger(ReceiveQSubTaskService.class);

    @Inject
    private EntityManager entityManager;

    @Inject
    private ClusterTaskRepository clusterTaskRepository;

    public ClusterTask queryClusterTask(Long jobId){
        return clusterTaskRepository.findOne(jobId);
    }

}
