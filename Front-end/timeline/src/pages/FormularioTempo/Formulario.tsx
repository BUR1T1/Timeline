import React, { useState, type ChangeEvent, type FormEvent } from "react";
import "./Formulario.css";

type Etapa = {
    id: number;
    titulo: string;
    descricao: string;
    dataInicio: string;
    dataFim: string;
    imagemUrl: string;
};

type FormData = {
    titulo: string;
    descricao: string;
    dataInicio: string;
    dataFim: string;
    imagemUrl: string;
};

const Formulario: React.FC = () => {
    const [formData, setFormData] = useState<FormData>({
        titulo: "",
        descricao: "",
        dataInicio: "",
        dataFim: "",
        imagemUrl: ""
    });

    const [etapas, setEtapas] = useState<Etapa[]>([]);

    const handleChange = (
        e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();

        if (!formData.titulo || !formData.descricao || !formData.dataInicio) {
            return;
        }

        const novaEtapa: Etapa = {
            id: Date.now(),
            ...formData
        };

        setEtapas((prev) => [...prev, novaEtapa]);

        setFormData({
            titulo: "",
            descricao: "",
            dataInicio: "",
            dataFim: "",
            imagemUrl: ""
        });
    };

    const removerEtapa = (id: number) => {
        setEtapas((prev) => prev.filter((etapa) => etapa.id !== id));
    };

    return (
        <div className="timeline-page">
            <aside className="timeline-form-panel">
                <div className="panel-content">
                    <h1 className="titulo-formulario">Criar Timeline</h1>
                    <p className="subtitulo-formulario">
                        Adicione etapas da sua trajetória e veja a linha do tempo
                        sendo montada em tempo real.
                    </p>

                    <form className="timeline-form" onSubmit={handleSubmit}>
                        <div className="campo">
                            <label>Título da etapa</label>
                            <input
                                type="text"
                                name="titulo"
                                value={formData.titulo}
                                onChange={handleChange}
                                placeholder="Ex: Início na faculdade"
                            />
                        </div>

                        <div className="campo">
                            <label>Descrição</label>
                            <textarea
                                name="descricao"
                                value={formData.descricao}
                                onChange={handleChange}
                                placeholder="Descreva essa fase da sua trajetória"
                                rows={4}
                            />
                        </div>

                        <div className="dupla-coluna">
                            <div className="campo">
                                <label>Data de início</label>
                                <input
                                    type="date"
                                    name="dataInicio"
                                    value={formData.dataInicio}
                                    onChange={handleChange}
                                />
                            </div>

                            <div className="campo">
                                <label>Data de fim</label>
                                <input
                                    type="date"
                                    name="dataFim"
                                    value={formData.dataFim}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>

                        <div className="campo">
                            <label>Imagem da etapa (URL)</label>
                            <input
                                type="text"
                                name="imagemUrl"
                                value={formData.imagemUrl}
                                onChange={handleChange}
                                placeholder="Cole a URL de uma imagem"
                            />
                        </div>

                        <button type="submit" className="btn-adicionar">
                            Adicionar etapa
                        </button>
                    </form>
                </div>
            </aside>

            <main className="timeline-preview">
                <div className="preview-header">
                    <h2>Sua Timeline</h2>
                    <span>{etapas.length} etapa(s)</span>
                </div>

                {etapas.length === 0 ? (
                    <div className="timeline-vazia">
                        <div className="linha-vazia" />
                        <p>
                            Sua linha do tempo aparecerá aqui conforme você
                            adicionar etapas.
                        </p>
                    </div>
                ) : (
                    <div className="timeline-lista">
                        {etapas.map((etapa, index) => (
                            <div className="timeline-item" key={etapa.id}>
                                <div className="timeline-marker-area">
                                    <div className="timeline-marker" />
                                    {index !== etapas.length - 1 && (
                                        <div className="timeline-line" />
                                    )}
                                </div>

                                <div className="timeline-card">
                                    <div className="timeline-card-topo">
                                        <div>
                                            <h3>{etapa.titulo}</h3>
                                            <p className="timeline-data">
                                                {etapa.dataInicio || "Sem data"}
                                                {etapa.dataFim
                                                    ? ` — ${etapa.dataFim}`
                                                    : " — Em andamento"}
                                            </p>
                                        </div>

                                        <button
                                            className="btn-remover"
                                            onClick={() =>
                                                removerEtapa(etapa.id)
                                            }
                                            type="button"
                                        >
                                            Remover
                                        </button>
                                    </div>

                                    <p className="timeline-descricao">
                                        {etapa.descricao}
                                    </p>

                                    {etapa.imagemUrl && (
                                        <div className="timeline-imagem-box">
                                            <img
                                                src={etapa.imagemUrl}
                                                alt={etapa.titulo}
                                                className="timeline-imagem"
                                            />
                                        </div>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </main>
        </div>
    );
};

export default Formulario;