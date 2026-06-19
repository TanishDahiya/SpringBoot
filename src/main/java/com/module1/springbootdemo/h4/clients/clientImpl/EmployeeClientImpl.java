package com.module1.springbootdemo.h4.clients.clientImpl;

import com.module1.springbootdemo.h2.dto.EmployeeDto;
import com.module1.springbootdemo.h4.clients.EmployeeClient;
import com.module1.springbootdemo.h4.dto.EmployeeDtoRestClient;
import com.module1.springbootdemo.h4.dto.EmployeeResponseDtoCliennt;
import com.module1.springbootdemo.h4.dto.EmployeeResponseSingleDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements EmployeeClient {

    Logger logger = LoggerFactory.getLogger(EmployeeClientImpl.class);

    private final RestClient restClient;

    @Override
    public EmployeeDtoRestClient getEmployee(Long id) {
        try{

            logger.info("Fetching employee with ID: {}", id);

            EmployeeResponseSingleDto employeeResponse = restClient.get()
                    .uri("/employee/{id}", id)
                    .retrieve()
                    .body(EmployeeResponseSingleDto.class);
            logger.info("Employee response: {}", employeeResponse);
            return employeeResponse.getData();

        }catch (Exception e){
            logger.error("Error fetching employee with ID: {}. Exception: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch employee with ID: " + id, e);
        }

    }

    @Override
    public EmployeeDtoRestClient deleteEmployee(Long id) {
        return null;
    }

    @Override
    public EmployeeDtoRestClient updateEmployee(EmployeeDtoRestClient employee) {
        return null;
    }
//
//    @Override
//    public List<EmployeeDtoRestClient> getAllEmployees() {
//        return restClient.get()
//                .uri("/employees")
//                .retrieve()
//                .body(List.class);
//    }

    @Override
    public List<EmployeeDtoRestClient> getAllEmployees() {

        EmployeeResponseDtoCliennt employeeResponse = restClient.get()
                .uri("/employees")
                .retrieve()
                .body(EmployeeResponseDtoCliennt.class);
        return employeeResponse.getData();
    }


}
