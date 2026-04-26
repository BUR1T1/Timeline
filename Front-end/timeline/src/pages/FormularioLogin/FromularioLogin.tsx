import React, { useState } from "react";
import "./FromularioLogin.css";
import { useNavigate } from "react-router-dom";

import tokyoGhoulImg from "../../assets/tokyoghoul.jpg";
import luarImg from "../../assets/luar.jpg";
import itachiImg from "../../assets/itach.png";
import novaImg1 from "../../assets/estrada.webp";
import novaImg2 from "../../assets/tumb.jpeg";

function FormularioLogin() {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState("");

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();

        if (!email || !senha) {
            setErro("Preencha todos os campos.");
            return;
        }

        setErro("");
        setLoading(true);

        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email: email.trim(), senha })
            });

            if (!response.ok) {
                throw new Error("Usuário ou senha inválidos.");
            }

            const token = await response.text();
            localStorage.setItem("token", token);

            navigate("/timeline/criar");
        } catch (err: any) {
            setErro(err.message || "Erro ao fazer login.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="registro">
            <div className="fundo-animado">
                <div className="bloco"><img src={tokyoGhoulImg} className="bloco-imagem" alt="" /></div>
                <div className="bloco"><img src={luarImg} className="bloco-imagem" alt="" /></div>
                <div className="bloco"><img src={itachiImg} className="bloco-imagem" alt="" /></div>
                <div className="bloco"><img src={itachiImg} className="bloco-imagem" alt="" /></div>
                <div className="bloco"><img src={novaImg1} className="bloco-imagem" alt="" /></div>
                <div className="bloco"><img src={novaImg2} className="bloco-imagem" alt="" /></div>
            </div>

            <form className="formulario" onSubmit={handleLogin}>
                <h1 className="titulo-form">Login</h1>

                <div className="campo">
                    <label>E-mail</label>
                    <input
                        type="email"
                        required
                        placeholder="Digite seu e-mail"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className="campo">
                    <label>Senha</label>
                    <input
                        type="password"
                        required
                        placeholder="Digite sua senha"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                    />
                </div>

                {erro && <p className="erro">{erro}</p>}

                <button type="submit" className="btn-acessar" disabled={loading}>
                    {loading ? "Entrando..." : "Acessar"}
                </button>

                <button
                    type="button"
                    className="btn-criarConta"
                    onClick={() => navigate("/cadastro")}
                >
                    Não possui conta? Cadastre-se
                </button>
            </form>
        </div>
    );
}

export default FormularioLogin;