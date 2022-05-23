import './App.css';
import RouteNav from './RouteNav';

function App() {
  return (
    <div>
    <nav>
      <h1 className="mt-2 mb-2 ms-2">Employee Portal</h1>
    </nav>
    <p>IP == <br/>{process.env.PUBLIC_IP}</p>
    <RouteNav/>

    </div>
  );
}
export default App;
