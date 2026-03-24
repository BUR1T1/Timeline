import React, { useState, ChangeEvent, FormEvent } from "react";
import "./FormularioCadastro.css";

// Importações de imagens
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

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleFotoChange = (e: ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setFotoPreview(URL.createObjectURL(file));
        }
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        console.log("Enviando para o backend:", { ...formData, fotoPreview });
    };

    return (
        <div className="registro">
            <BackgroundAnimado />

            <div className="formulario">
                <h2 className="titulo-cadastro">CRIAR CONTA</h2>

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
                                backgroundImage: fotoPreview ? `url(${fotoPreview})` : 'none', 
                                backgroundSize: 'cover',
                                backgroundPosition: 'center'
                            }}
                        >
                            {!fotoPreview && <span>+</span>}
                        </label>
                    </div>

                    <div className="grid-campos">
                        <div className="campo">
                            <input name="nome" value={formData.nome} onChange={handleInputChange} placeholder="Nome completo" required />
                        </div>
                        <br />
                        <div className="campo">
                            <input name="cpf" value={formData.cpf} onChange={handleInputChange} placeholder="digite seu CPF" required />
                        </div>
                        <br />
                        <div className="campo">
                            <input type="email" name="email" value={formData.email} onChange={handleInputChange} placeholder="diite seu email." required />
                        </div>
                        <br />
                        <div className="campo">
                            <input type="password" name="senha" value={formData.senha} onChange={handleInputChange} placeholder="Sua senha" required />
                        </div>
                    </div>

                    <button type="submit" className="btn-acessar">FINALIZAR REGISTRO</button>
                    <button type="button" className="btn-criarConta">Já possui conta? Faça login</button>
                </form>
            </div>
        </div>
    );
};

const BackgroundAnimado: React.FC = () => {
    const imagens = [tokyoGhoulImg, luarImg, itachiImg, novaImg1, novaImg2, itachiImg];
    
    return (
        <div className="fundo-animado">
            {imagens.map((img, index) => (
                <div key={index} className="bloco">
                    <img src={img} alt={`Background ${index}`} className="bloco-imagem" />
                </div>
            ))}
        </div>
    );
};

export default FormularioCadastro;