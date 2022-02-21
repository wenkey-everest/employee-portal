package com.everest.employeeportal.models;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class ResultPage {
    private List<Employee> data;
    private Long totalElements;
    private int totalPages;
    private int pageSize;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPrevious;

    public ResultPage(Page<Employee> page) {
        this.data = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.currentPage = page.getNumber()+1;
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }
}
