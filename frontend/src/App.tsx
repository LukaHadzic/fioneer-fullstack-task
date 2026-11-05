import {BrowserRouter, Route, Routes} from "react-router-dom";
import { AddAccumulationPage } from 'pages/AddAccumulation';
import { AccumulationsTablePage } from 'pages/AccumulationsTablePage'
// import {AccumulationDetails} from "./components/AccumulationDetails";
import {GlobalHeader} from "./components/GlobalHeader";
import {AccumulationsDetailsDynamic} from "./components/AccumulationDetailsDynamic";
import {DashboardPage} from "./pages/DashboardPage";

// const App = () => {
//   return <div>Engagement Hub — Full-Stack Challenge (Frontend)</div>
// }

function App() {
    return (
        <BrowserRouter>
            <GlobalHeader/>

            <Routes>
                <Route path="/accumulations/new" element={<AddAccumulationPage />} />
                <Route path="/accumulations" element={<AccumulationsTablePage />} />
                <Route path="/" element={<DashboardPage />} />
                <Route path="/accumulations/:id" element={<AccumulationsDetailsDynamic />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;
