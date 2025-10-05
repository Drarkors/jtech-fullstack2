/*
 *  @(#)TasklistRepository.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.tasklist.adapters.database.repositories;

import br.com.jtech.tasklist.adapters.database.repositories.models.TaskListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * class TasklistRepository
 *
 * @author rafael.zanetti
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskListModel, UUID> {

  Set<TaskListModel> findAllByUserId(UUID userId);

}