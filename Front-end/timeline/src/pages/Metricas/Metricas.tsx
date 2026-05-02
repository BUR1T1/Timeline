import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Metricas.css";

type Registro = {
    id: string;
    titulo: string;
    likes: number;
    dislikes: number;
    comentarios: number;
};

type EstatisticasGerais = {
    totalRegistros: number;
    totalLikes: number;
    totalDislikes: number;
    totalComentarios: number;
    registroMaisPopular?: Registro;
    registroMenosPopular?: Registro;
};

const Metricas: React.FC = () => {
    const navigate = useNavigate();
    const [metricas, setMetricas] = useState<EstatisticasGerais | null>(null);
    const [registros, setRegistros] = useState<Registro[]>([]);
    const [loading, setLoading] = useState(true);
    const [erro, setErro] = useState("");

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/");
            return;
        }

        buscarMetricas();
    }, [navigate]);

    const buscarMetricas = async () => {
        const token = localStorage.getItem("token");
        try {
            setLoading(true);
            setErro("");

            const response = await fetch(
                "http://localhost:8080/usuario/metricas",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error("Não foi possível carregar as métricas.");
            }

            const data = await response.json();
            setMetricas(data);

            // Calcular registros com mais/menos interações
            if (data.registros && data.registros.length > 0) {
                const registrosOrdenados = [...data.registros].sort(
                    (a, b) =>
                        b.likes +
                        b.dislikes +
                        b.comentarios -
                        (a.likes + a.dislikes + a.comentarios)
                );
                setRegistros(registrosOrdenados);
            }
        } catch (err: any) {
            setErro(err.message || "Erro ao carregar métricas.");
            // Dados de exemplo para demonstração
            setMetricas({
                totalRegistros: 5,
                totalLikes: 45,
                totalDislikes: 8,
                totalComentarios: 23
            });
        } finally {
            setLoading(false);
        }
    };

    const calcularEngajamento = (
        likes: number,
        dislikes: number,
        comentarios: number
    ) => {
        return likes + dislikes + comentarios;
    };

    const calcularTaxaAprovacao = (likes: number, dislikes: number) => {
        const total = likes + dislikes;
        if (total === 0) return 0;
        return ((likes / total) * 100).toFixed(1);
    };

    return (
        <div className="metricas-page">
            <header className="metricas-header">
                <div className="metricas-header-content">
                    <h1>Métricas e Estatísticas</h1>
                    <p>Acompanhe o desempenho da sua timeline</p>
                </div>
                <button
                    className="btn-voltar-metricas"
                    onClick={() => navigate("/minha-timeline")}
                >
                    ← Voltar ao Dashboard
                </button>
            </header>

            <main className="metricas-container">
                {loading && <p className="estado-info">Carregando métricas...</p>}

                {erro && !loading && (
                    <p className="estado-info">{erro}</p>
                )}

                {!loading && metricas && (
                    <>
                        <section className="metricas-gerais">
                            <h2>Estatísticas Gerais</h2>
                            <div className="metricas-grid">
                                <div className="metrica-card">
                                    <div className="metrica-icon">📝</div>
                                    <div className="metrica-info">
                                        <h3>Total de Registros</h3>
                                        <p className="metrica-valor">
                                            {metricas.totalRegistros}
                                        </p>
                                    </div>
                                </div>

                                <div className="metrica-card">
                                    <div className="metrica-icon">❤️</div>
                                    <div className="metrica-info">
                                        <h3>Likes Recebidos</h3>
                                        <p className="metrica-valor">
                                            {metricas.totalLikes}
                                        </p>
                                    </div>
                                </div>

                                <div className="metrica-card">
                                    <div className="metrica-icon">👎</div>
                                    <div className="metrica-info">
                                        <h3>Dislikes Recebidos</h3>
                                        <p className="metrica-valor">
                                            {metricas.totalDislikes}
                                        </p>
                                    </div>
                                </div>

                                <div className="metrica-card">
                                    <div className="metrica-icon">💬</div>
                                    <div className="metrica-info">
                                        <h3>Comentários Recebidos</h3>
                                        <p className="metrica-valor">
                                            {metricas.totalComentarios}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </section>

                        {registros.length > 0 && (
                            <>
                                <section className="metricas-detalhes">
                                    <h2>Registros por Engajamento</h2>
                                    <div className="registros-tabela">
                                        <div className="tabela-header">
                                            <div className="col-titulo">Título</div>
                                            <div className="col-likes">❤️ Likes</div>
                                            <div className="col-dislikes">
                                                👎 Dislikes
                                            </div>
                                            <div className="col-comentarios">
                                                💬 Comentários
                                            </div>
                                            <div className="col-engajamento">
                                                Engajamento
                                            </div>
                                            <div className="col-aprovacao">
                                                Taxa Aprovação
                                            </div>
                                        </div>
                                        {registros.map((registro) => (
                                            <div
                                                className="tabela-row"
                                                key={registro.id}
                                            >
                                                <div className="col-titulo">
                                                    {registro.titulo}
                                                </div>
                                                <div className="col-likes">
                                                    {registro.likes}
                                                </div>
                                                <div className="col-dislikes">
                                                    {registro.dislikes}
                                                </div>
                                                <div className="col-comentarios">
                                                    {registro.comentarios}
                                                </div>
                                                <div className="col-engajamento">
                                                    <div className="barra-progresso">
                                                        <div
                                                            className="barra-preenchida"
                                                            style={{
                                                                width: `${Math.min(
                                                                    (calcularEngajamento(
                                                                        registro.likes,
                                                                        registro.dislikes,
                                                                        registro.comentarios
                                                                    ) /
                                                                        Math.max(
                                                                            ...registros.map(
                                                                                (r) =>
                                                                                    calcularEngajamento(
                                                                                        r.likes,
                                                                                        r.dislikes,
                                                                                        r.comentarios
                                                                                    )
                                                            )) *
                                                                        100,
                                                                    100
                                                                )}%`
                                                            }}
                                                        />
                                                    </div>
                                                    <span>
                                                        {calcularEngajamento(
                                                            registro.likes,
                                                            registro.dislikes,
                                                            registro.comentarios
                                                        )}
                                                    </span>
                                                </div>
                                                <div className="col-aprovacao">
                                                    {calcularTaxaAprovacao(
                                                        registro.likes,
                                                        registro.dislikes
                                                    )}
                                                    %
                                                </div>
                                            </div>
                                        ))}
                                    </div>
                                </section>

                                <section className="metricas-insights">
                                    <h2>Insights</h2>
                                    <div className="insights-grid">
                                        <div className="insight-card">
                                            <h3>Registro Mais Popular</h3>
                                            {registros[0] && (
                                                <div className="insight-content">
                                                    <p className="insight-titulo">
                                                        {registros[0].titulo}
                                                    </p>
                                                    <p className="insight-valor">
                                                        {calcularEngajamento(
                                                            registros[0].likes,
                                                            registros[0].dislikes,
                                                            registros[0].comentarios
                                                        )}{" "}
                                                        interações
                                                    </p>
                                                </div>
                                            )}
                                        </div>

                                        <div className="insight-card">
                                            <h3>Taxa Média de Aprovação</h3>
                                            <div className="insight-content">
                                                <p className="insight-valor">
                                                    {(
                                                        (metricas.totalLikes /
                                                            (metricas.totalLikes +
                                                                metricas.totalDislikes || 1)) *
                                                        100
                                                    ).toFixed(1)}
                                                    %
                                                </p>
                                                <p className="insight-descricao">
                                                    Proporção de likes em
                                                    relação ao total de
                                                    reações
                                                </p>
                                            </div>
                                        </div>

                                        <div className="insight-card">
                                            <h3>Engajamento Médio</h3>
                                            <div className="insight-content">
                                                <p className="insight-valor">
                                                    {(
                                                        (metricas.totalLikes +
                                                            metricas.totalDislikes +
                                                            metricas.totalComentarios) /
                                                        metricas.totalRegistros
                                                    ).toFixed(1)}
                                                </p>
                                                <p className="insight-descricao">
                                                    Interações por registro
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </>
                        )}
                    </>
                )}
            </main>
        </div>
    );
};

export default Metricas;
