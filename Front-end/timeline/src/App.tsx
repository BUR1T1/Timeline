import { BrowserRouter, Routes, Route } from "react-router-dom";
import FormularioLogin from "./pages/FormularioLogin/FromularioLogin";
import FormularioCadastro from "./pages/FormularioCadastro/FormularioCadastro";
import FormularioTempo from "./pages/FormularioTempo/Formulario";
import LinhaTempo from "./pages/LinhaTempo/LinhaTempo";
import Dashboard from "./pages/Dashboard/Dashboard";
import EditarRegistro from "./pages/EditarRegistro/EditarRegistro";
import Metricas from "./pages/Metricas/Metricas";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<FormularioLogin />} />
                <Route path="/cadastro" element={<FormularioCadastro />} />
                <Route path="/minha-timeline" element={<Dashboard />} />
                <Route path="/timeline/criar" element={<FormularioTempo />} />
                <Route path="/timeline/editar/:id" element={<EditarRegistro />} />
                <Route path="/metricas" element={<Metricas />} />
                <Route path="/public/usuarios/:usuarioId/timeline" element={<LinhaTempo />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;