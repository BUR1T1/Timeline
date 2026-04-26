import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./LinhaTempo.css";

type Registro = {
    id: string;
    titulo: string;
    descricao: string;
    dataInicio: string;
    dataFim?: string;
    imagemUrl?: string;
};

function formatarData(data?: string) {
    if (!data) return "Em andamento";

    const partes = data.split("-");
    if (partes.length !== 3) return data;

    const [ano, mes, dia] = partes;
    return `${dia}/${mes}/${ano}`;
}

const LinhaTempo: React.FC = () => {
    const { usuarioId } = useParams();
    const [registros, setRegistros] = useState<Registro[]>([]);
    const [loading, setLoading] = useState(true);
    const [erro, setErro] = useState("");

    useEffect(() => {
        const buscarTimeline = async () => {
            if (!usuarioId) {
                setErro("Usuário não informado na URL.");
                setLoading(false);
                return;
            }
            try {
                setLoading(true);
                setErro("");

                const response = await fetch(
                    `http://localhost:8080/public/usuarios/${usuarioId}/timeline`
                );

                if (!response.ok) {
                    throw new Error("Não foi possível carregar a timeline.");
                }

                const data = await response.json();
                setRegistros(data);
            } catch (err: any) {
                setErro(err.message || "Erro ao carregar timeline.");
            } finally {
                setLoading(false);
            }
        };

        buscarTimeline();
    }, [usuarioId]);

    return (
        <div className="linha-tempo-page">
            <header className="linha-tempo-header">
                <div className="linha-tempo-header-content">
                    <p className="linha-tempo-tag">Timeline pública</p>
                    <h1>Trajetória</h1>
                    <p className="linha-tempo-subtitulo">
                        Visualização pública da linha do tempo criada pelo usuário.
                    </p>
                </div>
            </header>

            <main className="linha-tempo-container">
                {loading && <p className="estado-info">Carregando timeline...</p>}

                {erro && !loading && <p className="estado-erro">{erro}</p>}

                {!loading && !erro && registros.length === 0 && (
                    <p className="estado-info">Nenhuma etapa cadastrada.</p>
                )}

                {!loading && !erro && registros.length > 0 && (
                    <div className="linha-tempo-coluna">
                        {registros.map((registro, index) => (
                            <div className="linha-item" key={registro.id}>
                                <div className="linha-marker-area">
                                    <div className="linha-marker" />
                                    {index !== registros.length - 1 && (
                                        <div className="linha-traco" />
                                    )}
                                </div>

                                <article className="linha-card">
                                    <div className="linha-card-topo">
                                        <div>
                                            <h2>{registro.titulo}</h2>
                                            <p className="linha-data">
                                                {formatarData(registro.dataInicio)}{" "}
                                                {" — "}
                                                {registro.dataFim
                                                    ? formatarData(registro.dataFim)
                                                    : "Em andamento"}
                                            </p>
                                        </div>
                                    </div>

                                    <p className="linha-descricao">
                                        {registro.descricao}
                                    </p>

                                    {registro.imagemUrl && (
                                        <div className="linha-imagem-box">
                                            <img
                                                src={registro.imagemUrl}
                                                alt={registro.titulo}
                                                className="linha-imagem"
                                            />
                                        </div>
                                    )}
                                </article>
                            </div>
                        ))}
                    </div>
                )}
            </main>
        </div>
    );
};

export default LinhaTempo;