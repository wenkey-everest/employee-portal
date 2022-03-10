import ReactDOM from 'react-dom';
import React, { Component, useEffect, useState } from 'react';
import * as api from "../api";
import { useNavigate, useParams } from 'react-router-dom';
import { Container, Form, Button } from 'react-bootstrap';


const UpdateEmployeeForm = () => {
    
    let {id} = useParams();
    let nav = useNavigate();

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [everestEmailId, setEverestEmailId] = useState("");
    const [password, setPassword] = useState("");
    const [personalEmailId, setPersonalEmailId] = useState("");
    const [dateOfBirth, setDateOfBirth] = useState("");
    const [dateOfJoin, setDateOfJoin] = useState("");
    const [designation, setDesignation] = useState("");
    const [experience, setExperience] = useState("");
    const [bio, setBio] = useState("");

    const editEmployee = ()=>{
        api.getEmployeeById(id).then(response=>{
            setFirstName(response.data.firstName);
            setLastName(response.data.lastName);
            setEverestEmailId(response.data.everestEmailId);
            setPassword(response.data.password);
            setPersonalEmailId(response.data.personalEmailId);
            setDateOfBirth(response.data.dateOfBirth);
            setDateOfJoin(response.data.dateOfJoin);
            setDesignation(response.data.designation);
            setExperience(response.data.experience);
            setBio(response.data.bio);
        }) 
    }
    const handleSubmit = e => {
        e.preventDefault();
        if(!firstName || !everestEmailId || !password) {
            alert("Enter all fields");
            return;
        }
        const employee = {
            firstName,
            lastName,
            everestEmailId,
            password,
            personalEmailId,
            dateOfBirth,
            dateOfJoin,
            designation,
            experience,
            bio
        };
        api.updateEmployee(id,employee).then(response => {
            console.log("employee updated successfully", response)
            setFirstName("");
            setLastName("");
            setEverestEmailId("");
            setPassword("");
            setPersonalEmailId("");
            setDateOfBirth("");
            setDateOfJoin("");
            setDesignation("");
            setExperience("");
            setBio("");
            window.alert("Updated Successfully");
            nav("/");
        }).catch(e => console.log("error", e));
    };

    useEffect(() => {
        editEmployee();
      }, []);

    return (  
        <Container>
        <Form onSubmit={(e)=>handleSubmit(e)} className="row justify-content-center">
            <Form.Group>
                <Form.Label htmlFor="firstName" >First Name</Form.Label>
                <Form.Control type = "text" placeholder="enter your firstName" value={firstName}
                        onChange={e => setFirstName(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="lastName">Last name</Form.Label>
                <Form.Control type = "text" placeholder="enter your lastName" value={lastName}
                        onChange={e => setLastName(e.target.value)} />
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="everestEmailId">Everest Email Id</Form.Label>
                <Form.Control type = "email" placeholder="Enter your email" value={everestEmailId}
                        onChange={e => setEverestEmailId(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="password">Password</Form.Label>
                <Form.Control type = "password" placeholder="Password" value={password}
                        onChange={e => setPassword(e.target.value)} />
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="personalEmailId">Personal Email Id</Form.Label>
                <Form.Control type = "email" placeholder="Enter your personal email Id"  value={personalEmailId}
                        onChange={e => setPersonalEmailId(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="dateOfBirth">Date of Birth</Form.Label>
                <Form.Control type = "date" placeholder="enter date of birth" value={dateOfBirth}
                        onChange={e => setDateOfBirth(e.target.value)} />
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="dateOfJoin">Date of Join</Form.Label>
                <Form.Control type = "date" placeholder="enter date of join" value={dateOfJoin}
                        onChange={e => setDateOfJoin(e.target.value)} />
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="designation">Designation</Form.Label>
                <Form.Control type = "text" placeholder="Enter your designation" value={designation}
                        onChange={e => setDesignation(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="experience">Experience</Form.Label>
                <Form.Control type = "number" placeholder="Enter your experience" value={experience}
                        onChange={e => setExperience(e.target.value)}/>
            </Form.Group>
            <Form.Group>
                <Form.Label  htmlFor="bio">Bio</Form.Label>
                <Form.Control type = "text" placeholder="Enter your Bio" value={bio}
                        onChange={e => setBio(e.target.value)}/>
            </Form.Group>
            <Button className="form-group col-md-10 mt-4 mb-2" type="submit">Update</Button>
        </Form>
        </Container>
    );
}
 
export default UpdateEmployeeForm;