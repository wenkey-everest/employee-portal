import ReactDOM from 'react-dom';
import React from 'react';
import {Routes, Route} from 'react-router-dom'
import SaveEmployeeForm from './components/SaveEmployeeForm';
import EmployeeTable from './components/EmployeeTable';
import "bootstrap/dist/css/bootstrap.css"
import UpdateEmployeeForm from './components/updateEmployeeForm';


export default function RouteNav() {
        return(
            <div>
      <Routes>
            <Route path='/saveEmployee' element={<SaveEmployeeForm/>} />
            <Route path= '/' element={<EmployeeTable/>}/>
            <Route path= '/updateEmployee/:id' element={<UpdateEmployeeForm/>}/>

      </Routes>
        </div>

        );

}