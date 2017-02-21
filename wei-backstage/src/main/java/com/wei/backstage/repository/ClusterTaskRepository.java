package com.wei.backstage.repository;

import com.wei.backstage.domain.ClusterTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gongw on 2017/2/13.
 */
public interface ClusterTaskRepository extends JpaRepository<ClusterTask, Long> {
    List<ClusterTask> findByUser(String user);
}
