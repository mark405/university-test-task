package com.university.university.services.impl;

import com.university.university.entities.Worker;
import com.university.university.repositories.WorkerRepository;
import com.university.university.services.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    @Override
    public List<Worker> workersByTemplate(String template) {
        return workerRepository.findByNameContainingIgnoreCase(template);
    }
}
