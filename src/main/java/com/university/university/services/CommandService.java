package com.university.university.services;

import com.university.university.entities.Department;
import com.university.university.entities.Worker;
import com.university.university.models.Degree;
import com.university.university.models.WorkerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final DepartmentService departmentService;
    private final WorkerService workerService;

    private final Pattern pattern = Pattern.compile("\\$\\w+");

    private final Map<String, Function<String, String>> commandMap = Map.of(
            "head", this::getDepartmentHead,
            "statistics", this::getDepartmentStatistics,
            "average", this::getAverageSalary,
            "count", this::getEmployeeCount,
            "search", this::globalSearch
    );

    private String getKeyWord(String command) {
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            return matcher.group().substring(1);
        }
        return null;
    }

    private String globalSearch(String command) {
        List<String> workers = workerService.workersByTemplate(command).stream()
                .map(Worker::getName)
                .toList();

        if (workers.isEmpty()) {
            return "No workers found.";
        }

        return String.join(", ", workers);
    }

    private String getEmployeeCount(String departmentName) {
        Optional<Department> departmentOpt = departmentService.getDepartmentByName(departmentName);

        return departmentOpt.map(department -> String.valueOf(department.getWorkers().size())).orElse("Department not found.");

    }

    private String getAverageSalary(String departmentName) {
        Optional<Department> departmentOpt = departmentService.getDepartmentByName(departmentName);

        if (departmentOpt.isEmpty()) {
            return "Department not found.";
        }

        Department department = departmentOpt.get();

        BigDecimal totalSalary = department.getWorkers().stream()
                .map(Worker::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int numberOfLectors = department.getWorkers().size();

        BigDecimal averageSalary = numberOfLectors > 0
                ? totalSalary.divide(BigDecimal.valueOf(numberOfLectors), RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return String.format("The average salary of %s is %s", departmentName, averageSalary);
    }

    private String getDepartmentStatistics(String departmentName) {
        Optional<Department> departmentOpt = departmentService.getDepartmentByName(departmentName);

        if (departmentOpt.isEmpty()) {
            return "Department not found.";
        }

        Map<Degree, Long> degreeLectorsQuantityMap = departmentOpt.get().getWorkers().stream()
                .filter(worker -> worker.getWorkerType() == WorkerType.LECTOR)
                .collect(Collectors.groupingBy(Worker::getDegree, Collectors.counting()));

        if (degreeLectorsQuantityMap.isEmpty()) {
            return "No lectors found.";
        }

        StringBuilder result = new StringBuilder();

        degreeLectorsQuantityMap.forEach(
                (degree, quantity) -> result.append(degree).append(" - ").append(quantity).append("\n")
        );

        return result.toString();
    }

    private String getDepartmentHead(String departmentName) {
        Optional<Department> departmentOpt = departmentService.getDepartmentByName(departmentName);

        if (departmentOpt.isEmpty()) {
            return "Department not found.";
        }

        return String.format("Head of %s department is %s", departmentName, departmentOpt.get().getHead().getName());
    }

    public String executeCommand(String input) {
        for (Map.Entry<String, Function<String, String>> entry : commandMap.entrySet()) {
            if (input.contains(entry.getKey())) {
                String parameter = getKeyWord(input);
                if (parameter == null) {
                    return "No parameter found.";
                }
                return entry.getValue().apply(parameter);
            }
        }
        return "Command not recognized.";
    }
}
