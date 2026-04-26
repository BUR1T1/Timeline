import { BrowserRouter, Routes, Route } from "react-router-dom";
import FormularioLogin from "./pages/FormularioLogin/FromularioLogin";
import FormularioCadastro from "./pages/FormularioCadastro/FormularioCadastro";
import FormularioTempo from "./pages/FormularioTempo/Formulario";
import LinhaTempo from "./pages/LinhaTempo/LinhaTempo";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<FormularioLogin />} />
                <Route path="/cadastro" element={<FormularioCadastro />} />
                <Route path="/timeline/criar" element={<FormularioTempo />} />
                <Route path="/public/usuarios/:usuarioId/timeline" element={<LinhaTempo />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;