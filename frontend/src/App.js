import './App.css';
import RouteNav from './RouteNav';

function App() {
  return (
    <div>
    <nav>
      <h1 className="mt-2 mb-2 ms-2">Employee Portal</h1>
      <h2>{process.env.PUBLIC_IP_BACK_END}</h2>
    </nav>
   
    <RouteNav/>

    </div>
  );
}
export default App;
