import axios from "axios";

const API_BASE_URL = 'http://52.12.90.48:8080'

export function fetchEmployees() {
    return axios.get(`${API_BASE_URL}/api/employees`);
}

export function createEmployee(employee) {
    return axios.post(`${API_BASE_URL}/api/employees`, employee);
}

export function deleteEmployee(id) {
    return axios.delete(`${API_BASE_URL}/api/employees/${id}`);
}

export function deleteAllEmployees(id) {
    return axios.delete(`${API_BASE_URL}/api/employees`);
}

export function updateEmployee(id, employee){
    return axios.put(`${API_BASE_URL}/api/employees/${id}`, employee);
}

export function getEmployeeById(id){
    return axios.get(`${API_BASE_URL}/api/employees/${id}`);
}

export function searchEmployee(name){
    return axios.get(`${API_BASE_URL}/api/employees/search?name=${name}`);
}