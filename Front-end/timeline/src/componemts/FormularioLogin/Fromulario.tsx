import React from "react";
import "./Fromulario.css";

import tokyoGhoulImg from "../../assets/tokyoghoul.jpg";
import luarImg from "../../assets/luar.jpg";
import itachiImg from "../../assets/itach.png";
import novaImg1 from "../../assets/estrada.webp";
import novaImg2 from "../../assets/tumb.jpeg";

function Formulario() {
    return (
        <div className="registro">
            <div className="fundo-animado">
                <div className="bloco">
                    <img src={tokyoGhoulImg} alt="" className="bloco-imagem" />
                </div>
                <div className="bloco">
                    <img src={luarImg} alt="" className="bloco-imagem" />
                </div>
                <div className="bloco">
                    <img src={itachiImg} alt="" className="bloco-imagem" />
                </div>
                <div className="bloco">
                    <img src={itachiImg} alt="" className="bloco-imagem" />
                </div>
                <div className="bloco">
                    <img src={novaImg1} alt="" className="bloco-imagem" />
                </div>
                <div className="bloco">
                    <img src={novaImg2} alt="" className="bloco-imagem" />
                </div>
            </div>

            <div className="formulario">
                <div className="campo">
                    <label>Usuário</label>
                    <input name="Email" placeholder="Digite seu e-mail" />
                </div>

                <div className="campo">
                    <label>Senha</label>
                    <input type="password" placeholder="Digite sua senha" />
                </div>

                <button className="btn-acessar">Acessar</button>
                
                <button className="btn-criarConta">Não possui conta? Cadastre-se</button>
            </div>
        </div>
    );
}

export default Formulario;