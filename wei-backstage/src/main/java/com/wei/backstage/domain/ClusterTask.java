package com.wei.backstage.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gongw on 2017/2/13.
 */
@Data
@Entity
@Table(name = "t_cluster_task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClusterTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "job_name", length = 150)
    private String jobName;

    @Column(name = "job_priority")
    private Long jobPriority;

    @Column(name = "user", length = 150, nullable = false)
    private String user;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "queue", length = 150, nullable = false)
    private String queue;

    @Column(name = "slot_id", length = 150)
    private String slotId;

    @Column(name = "task_uuid", length = 50, nullable = false)
    private String taskUuid;

    @Column(name = "workflow_id")
    private Long workflowId;

}
