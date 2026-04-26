import React, { useState, type ChangeEvent, type FormEvent } from "react";
import "./FormularioCadastro.css";

import tokyoGhoulImg from "../../assets/tokyoghoul.jpg";
import luarImg from "../../assets/luar.jpg";
import itachiImg from "../../assets/itach.png";
import novaImg1 from "../../assets/estrada.webp";
import novaImg2 from "../../assets/lobo.jpg";

interface FormData {
    nome: string;
    email: string;
    senha: string;
    cpf: string;
}

const FormularioCadastro: React.FC = () => {
    const [formData, setFormData] = useState<FormData>({
        nome: "",
        email: "",
        senha: "",
        cpf: ""
    });

    const [fotoPreview, setFotoPreview] = useState<string | null>(null);
    const [erro, setErro] = useState("");
    const [loading, setLoading] = useState(false);

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleFotoChange = (e: ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setFotoPreview(URL.createObjectURL(file));
        }
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setErro("");
        setLoading(true);

        try {
            const response = await fetch("http://localhost:8080/auth/criarConta", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    nome: formData.nome,
                    email: formData.email,
                    senha: formData.senha
                })
            });

            if (!response.ok) {
                throw new Error("Não foi possível criar a conta.");
            }

            window.location.href = "/";
        } catch (err: any) {
            setErro(err.message || "Erro ao cadastrar.");
        } finally {
            setLoading(false);
        }
    };

    const imagens = [tokyoGhoulImg, luarImg, itachiImg, novaImg1, novaImg2, itachiImg];

    return (
        <div className="registro">
            <div className="fundo-animado">
                {imagens.map((img, index) => (
                    <div key={index} className="bloco">
                        <img src={img} alt="" className="bloco-imagem" />
                    </div>
                ))}
            </div>

            <div className="formulario">
                <h2 className="titulo-cadastro">Criar Conta</h2>

                <form onSubmit={handleSubmit} className="form-container">
                    <div className="campo-foto">
                        <input
                            type="file"
                            id="upload-foto"
                            className="hidden-input"
                            onChange={handleFotoChange}
                            accept="image/*"
                        />
                        <label
                            htmlFor="upload-foto"
                            className="label-foto"
                            style={{
                                backgroundImage: fotoPreview ? `url(${fotoPreview})` : "none",
                                backgroundSize: "cover",
                                backgroundPosition: "center"
                            }}
                        >
                            {!fotoPreview && <span>+</span>}
                        </label>
                    </div>

                    <div className="grid-campos">
                        <div className="campo">
                            <input
                                name="nome"
                                value={formData.nome}
                                onChange={handleInputChange}
                                placeholder="Nome completo"
                                required
                            />
                        </div>

                        <div className="campo">
                            <input
                                name="cpf"
                                value={formData.cpf}
                                onChange={handleInputChange}
                                placeholder="Digite seu CPF"
                                required
                            />
                        </div>

                        <div className="campo">
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                onChange={handleInputChange}
                                placeholder="Digite seu email"
                                required
                            />
                        </div>

                        <div className="campo">
                            <input
                                type="password"
                                name="senha"
                                value={formData.senha}
                                onChange={handleInputChange}
                                placeholder="Sua senha"
                                required
                            />
                        </div>
                    </div>

                    {erro && <p className="erro">{erro}</p>}

                    <button type="submit" className="btn-acessar" disabled={loading}>
                        {loading ? "Criando..." : "Finalizar Registro"}
                    </button>

                    <button
                        type="button"
                        className="btn-criarConta"
                        onClick={() => (window.location.href = "/")}
                    >
                        Já possui conta? Faça login
                    </button>
                </form>
            </div>
        </div>
    );
};

export default FormularioCadastro;