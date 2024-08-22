package com.university.university.services;

import com.university.university.entities.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> workersByTemplate(String template);
}
