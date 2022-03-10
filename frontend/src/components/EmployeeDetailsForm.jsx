import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {Form} from 'react-bootstrap'
import "bootstrap/dist/css/bootstrap.css"

const EmployeeDetailsForm = () => {
    return (
    
        <Form className="Form" onSubmit={console.log('hello page')}>
            <Form.Group>
                <Form.Label>User name</Form.Label>
                <Form.Control type = "text" placeholder='enter your username'/>
            </Form.Group>
            <Form.Group>
                <Form.Label>password</Form.Label>
                <Form.Control type = "password" placeholder='enter your password'/>
            </Form.Group>
            <button className="btn btn-primary" type = "submit">login</button>
        </Form>
      );
}
 
export default EmployeeDetailsForm;