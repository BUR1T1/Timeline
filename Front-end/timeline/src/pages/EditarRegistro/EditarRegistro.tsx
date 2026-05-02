import React, { useState, useEffect, type ChangeEvent, type FormEvent } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./EditarRegistro.css";

type FormData = {
    titulo: string;
    descricao: string;
    dataInicio: string;
    dataFim: string;
    imagemUrl: string;
};

const EditarRegistro: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    const [formData, setFormData] = useState<FormData>({
        titulo: "",
        descricao: "",
        dataInicio: "",
        dataFim: "",
        imagemUrl: ""
    });

    const [loading, setLoading] = useState(true);
    const [erro, setErro] = useState("");
    const [sucesso, setSucesso] = useState("");
    const [salvando, setSalvando] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/");
            return;
        }

        buscarRegistro();
    }, [id, navigate]);

    const buscarRegistro = async () => {
        const token = localStorage.getItem("token");
        try {
            setLoading(true);
            setErro("");

            const response = await fetch(
                `http://localhost:8080/usuario/registros/${id}`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error("Não foi possível carregar o registro.");
            }

            const data = await response.json();
            setFormData({
                titulo: data.titulo,
                descricao: data.descricao,
                dataInicio: data.dataInicio,
                dataFim: data.dataFim || "",
                imagemUrl: data.imagemUrl || ""
            });
        } catch (err: any) {
            setErro(err.message || "Erro ao carregar registro.");
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (
        e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        if (!formData.titulo || !formData.descricao || !formData.dataInicio) {
            setErro("Preencha todos os campos obrigatórios.");
            return;
        }

        setSalvando(true);
        setErro("");
        setSucesso("");

        const token = localStorage.getItem("token");

        try {
            const response = await fetch(
                `http://localhost:8080/usuario/registros/${id}`,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`
                    },
                    body: JSON.stringify(formData)
                }
            );

            if (!response.ok) {
                throw new Error("Não foi possível atualizar o registro.");
            }

            setSucesso("Registro atualizado com sucesso!");
            setTimeout(() => {
                navigate("/minha-timeline");
            }, 1500);
        } catch (err: any) {
            setErro(err.message || "Erro ao atualizar registro.");
        } finally {
            setSalvando(false);
        }
    };

    if (loading) {
        return (
            <div className="editar-registro-page">
                <p className="estado-info">Carregando registro...</p>
            </div>
        );
    }

    return (
        <div className="editar-registro-page">
            <header className="editar-header">
                <div className="editar-header-content">
                    <h1>Editar Registro</h1>
                    <p>Atualize os dados do seu registro de trajetória</p>
                </div>
                <button
                    className="btn-voltar"
                    onClick={() => navigate("/minha-timeline")}
                >
                    ← Voltar
                </button>
            </header>

            <main className="editar-container">
                <div className="editar-form-wrapper">
                    {erro && <div className="alerta-erro">{erro}</div>}
                    {sucesso && <div className="alerta-sucesso">{sucesso}</div>}

                    <form onSubmit={handleSubmit} className="editar-form">
                        <div className="campo">
                            <label htmlFor="titulo">Título *</label>
                            <input
                                id="titulo"
                                type="text"
                                name="titulo"
                                value={formData.titulo}
                                onChange={handleChange}
                                placeholder="Ex: Primeira experiência profissional"
                                required
                            />
                        </div>

                        <div className="campo">
                            <label htmlFor="descricao">Descrição *</label>
                            <textarea
                                id="descricao"
                                name="descricao"
                                value={formData.descricao}
                                onChange={handleChange}
                                placeholder="Descreva esta etapa da sua trajetória em detalhes"
                                rows={6}
                                required
                            />
                        </div>

                        <div className="campo-row">
                            <div className="campo">
                                <label htmlFor="dataInicio">Data de Início *</label>
                                <input
                                    id="dataInicio"
                                    type="date"
                                    name="dataInicio"
                                    value={formData.dataInicio}
                                    onChange={handleChange}
                                    required
                                />
                            </div>

                            <div className="campo">
                                <label htmlFor="dataFim">Data de Término</label>
                                <input
                                    id="dataFim"
                                    type="date"
                                    name="dataFim"
                                    value={formData.dataFim}
                                    onChange={handleChange}
                                    placeholder="Deixe em branco se ainda está em andamento"
                                />
                            </div>
                        </div>

                        <div className="campo">
                            <label htmlFor="imagemUrl">URL da Imagem</label>
                            <input
                                id="imagemUrl"
                                type="url"
                                name="imagemUrl"
                                value={formData.imagemUrl}
                                onChange={handleChange}
                                placeholder="https://exemplo.com/imagem.jpg"
                            />
                            {formData.imagemUrl && (
                                <div className="imagem-preview">
                                    <img
                                        src={formData.imagemUrl}
                                        alt="Preview"
                                        onError={(e) => {
                                            (e.target as HTMLImageElement).style.display = "none";
                                        }}
                                    />
                                </div>
                            )}
                        </div>

                        <div className="acoes-form">
                            <button
                                type="submit"
                                className="btn-salvar"
                                disabled={salvando}
                            >
                                {salvando ? "Salvando..." : "Salvar Alterações"}
                            </button>
                            <button
                                type="button"
                                className="btn-cancelar-form"
                                onClick={() => navigate("/minha-timeline")}
                                disabled={salvando}
                            >
                                Cancelar
                            </button>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    );
};

export default EditarRegistro;
