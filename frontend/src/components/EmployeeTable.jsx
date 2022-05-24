import React, { useState, useEffect } from "react";
import * as api from "../api";
import "bootstrap/dist/css/bootstrap.css";
import { Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const EmployeeTable = () => {
  let nav = useNavigate();
  const [employees, setEmployees] = useState([]);

  const [name, setName] = useState("");

  const deleteEmployee = (id) => {
    api
      .deleteEmployee(id)
      .then((response) => {
        console.log("employee deleted successfully", response);
        loadEmployees();
      })
      .catch((e) => console.log("error", e));
  };
  const deleteAllEmployees = (id) => {
    api
      .deleteAllEmployees()
      .then((response) => {
        console.log("employees deleted successfully", response);
        loadEmployees();
      })
      .catch((e) => console.log("error", e));
  };

  const loadEmployees = () => {
    api
      .fetchEmployees()
      .then((response) => {
        console.log("fetched employees successfully", response);
        setEmployees(response.data.data);
      })
      .catch((e) => console.log("error", e));
  };

  const searchEmployee = () => {
    api
      .searchEmployee(name)
      .then((response) => {
        setEmployees(response.data.data);
        console.log(response.data.data);
      })
      .catch((e) => console.log(e));
  };

  useEffect(() => {
    loadEmployees();
  }, []);

  return (
    <div className="container">
      <input
        type="text"
        className="form-control me-3"
        placeholder="Search by name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <div className="input-group-append">
        <button
          className="btn btn-primary mt-2"
          type="button"
          onClick={searchEmployee}
        >
          Search
        </button>
      </div>
      <hr />
      <Button
        className="btn btn-warning col-md mt-3"
        onClick={() => {
          nav("/saveEmployee");
        }}
      >
        Add Employee
      </Button>
      <Button
        className="btn btn-warning ms-4 mt-3 col-md"
        onClick={() => deleteAllEmployees()}
      >
        Delete All Employees
      </Button>

      <div id={"employeeList"}>
        <table className={"table"}>
          <thead>
            <tr>
              <td>Id</td>
              <td>firstName</td>
              <td>lastName</td>
              <td>EverstEmailId</td>
              <td>PersonalEmailId</td>
              <td>Date of Birth</td>
              <td>Date of Join</td>
              <td>Designation</td>
              <td>Experience</td>
              <td>Bio</td>
              <td>Edit</td>
              <td>Delete</td>
            </tr>
          </thead>
          <tbody>
            {employees.map((emp) => {
              return (
                <tr key={emp.empId}>
                  <td>{emp.empId}</td>
                  <td>{emp.firstName}</td>
                  <td>{emp.lastName}</td>
                  <td>{emp.everestEmailId}</td>
                  <td>{emp.personalEmailId}</td>
                  <td>{emp.dateOfBirth}</td>
                  <td>{emp.dateOfJoin}</td>
                  <td>{emp.designation}</td>
                  <td>{emp.experience}</td>
                  <td>{emp.bio}</td>
                  <td>
                    <button
                      className={"btn btn-warning"}
                      onClick={() => {
                        nav("/updateEmployee/" + emp.empId);
                      }}
                    >
                      Edit
                    </button>
                  </td>
                  <td>
                    <button
                      className={"btn btn-danger"}
                      onClick={() => deleteEmployee(emp.empId)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default EmployeeTable;
