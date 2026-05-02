import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

type Registro = {
    id: string;
    titulo: string;
    descricao: string;
    dataInicio: string;
    dataFim?: string;
    imagemUrl?: string;
    likes?: number;
    dislikes?: number;
    comentarios?: number;
};

type Usuario = {
    id: string;
    nome: string;
    email: string;
    foto?: string;
};

function formatarData(data?: string) {
    if (!data) return "Em andamento";
    const partes = data.split("-");
    if (partes.length !== 3) return data;
    const [ano, mes, dia] = partes;
    return `${dia}/${mes}/${ano}`;
}

const Dashboard: React.FC = () => {
    const navigate = useNavigate();
    const [usuario, setUsuario] = useState<Usuario | null>(null);
    const [registros, setRegistros] = useState<Registro[]>([]);
    const [loading, setLoading] = useState(true);
    const [erro, setErro] = useState("");
    const [confirmDelete, setConfirmDelete] = useState<string | null>(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            navigate("/");
            return;
        }

        buscarDados();
    }, [navigate]);

    const buscarDados = async () => {
        const token = localStorage.getItem("token");
        try {
            setLoading(true);
            setErro("");

            // Buscar dados do usuário
            const userResponse = await fetch(
                "http://localhost:8080/usuario/perfil",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!userResponse.ok) {
                throw new Error("Não foi possível carregar perfil do usuário.");
            }

            const userData = await userResponse.json();
            setUsuario(userData);

            // Buscar registros do usuário
            const registrosResponse = await fetch(
                "http://localhost:8080/usuario/registros",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!registrosResponse.ok) {
                throw new Error("Não foi possível carregar os registros.");
            }

            const registrosData = await registrosResponse.json();
            setRegistros(registrosData);
        } catch (err: any) {
            setErro(err.message || "Erro ao carregar dados.");
        } finally {
            setLoading(false);
        }
    };

    const handleDeleteregistro = async (id: string) => {
        const token = localStorage.getItem("token");
        try {
            const response = await fetch(
                `http://localhost:8080/usuario/registros/${id}`,
                {
                    method: "DELETE",
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error("Não foi possível deletar o registro.");
            }

            setRegistros((prev) => prev.filter((r) => r.id !== id));
            setConfirmDelete(null);
        } catch (err: any) {
            setErro(err.message || "Erro ao deletar registro.");
        }
    };

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/");
    };

    const totalLikes = registros.reduce((acc, r) => acc + (r.likes || 0), 0);
    const totalDislikes = registros.reduce(
        (acc, r) => acc + (r.dislikes || 0),
        0
    );
    const totalComentarios = registros.reduce(
        (acc, r) => acc + (r.comentarios || 0),
        0
    );

    return (
        <div className="dashboard-page">
            <header className="dashboard-header">
                <div className="dashboard-header-content">
                    <div className="dashboard-user-info">
                        {usuario?.foto && (
                            <img src={usuario.foto} alt={usuario.nome} />
                        )}
                        <div>
                            <h1>{usuario?.nome || "Usuário"}</h1>
                            <p>{usuario?.email}</p>
                        </div>
                    </div>
                    <button className="btn-logout" onClick={handleLogout}>
                        Sair
                    </button>
                </div>
            </header>

            <main className="dashboard-container">
                <section className="dashboard-stats">
                    <div className="stat-card">
                        <h3>Total de Registros</h3>
                        <p className="stat-number">{registros.length}</p>
                    </div>
                    <div className="stat-card">
                        <h3>Likes Recebidos</h3>
                        <p className="stat-number">{totalLikes}</p>
                    </div>
                    <div className="stat-card">
                        <h3>Dislikes Recebidos</h3>
                        <p className="stat-number">{totalDislikes}</p>
                    </div>
                    <div className="stat-card">
                        <h3>Comentários</h3>
                        <p className="stat-number">{totalComentarios}</p>
                    </div>
                </section>

                <section className="dashboard-actions">
                    <button
                        className="btn-primary"
                        onClick={() => navigate("/timeline/criar")}
                    >
                        + Novo Registro
                    </button>
                    <button
                        className="btn-secondary"
                        onClick={() => navigate(`/metricas`)}
                    >
                        Ver Métricas Detalhadas
                    </button>
                </section>

                {loading && <p className="estado-info">Carregando dados...</p>}
                {erro && !loading && (
                    <p className="estado-erro">{erro}</p>
                )}

                {!loading && !erro && registros.length === 0 && (
                    <div className="estado-vazio">
                        <p>Você ainda não criou nenhum registro.</p>
                        <button
                            className="btn-primary"
                            onClick={() => navigate("/timeline/criar")}
                        >
                            Criar Primeiro Registro
                        </button>
                    </div>
                )}

                {!loading && !erro && registros.length > 0 && (
                    <section className="dashboard-registros">
                        <h2>Meus Registros</h2>
                        <div className="registros-grid">
                            {registros.map((registro) => (
                                <div className="registro-card" key={registro.id}>
                                    {registro.imagemUrl && (
                                        <img
                                            src={registro.imagemUrl}
                                            alt={registro.titulo}
                                            className="registro-imagem"
                                        />
                                    )}
                                    <div className="registro-conteudo">
                                        <h3>{registro.titulo}</h3>
                                        <p className="registro-data">
                                            {formatarData(
                                                registro.dataInicio
                                            )}{" "}
                                            {" — "}
                                            {formatarData(registro.dataFim)}
                                        </p>
                                        <p className="registro-descricao">
                                            {registro.descricao.substring(
                                                0,
                                                100
                                            )}
                                            {registro.descricao.length > 100
                                                ? "..."
                                                : ""}
                                        </p>

                                        <div className="registro-metricas">
                                            <span className="metrica">
                                                ❤️ {registro.likes || 0}
                                            </span>
                                            <span className="metrica">
                                                👎 {registro.dislikes || 0}
                                            </span>
                                            <span className="metrica">
                                                💬 {registro.comentarios || 0}
                                            </span>
                                        </div>

                                        <div className="registro-acoes">
                                            <button
                                                className="btn-editar"
                                                onClick={() =>
                                                    navigate(
                                                        `/timeline/editar/${registro.id}`
                                                    )
                                                }
                                            >
                                                Editar
                                            </button>
                                            <button
                                                className="btn-deletar"
                                                onClick={() =>
                                                    setConfirmDelete(
                                                        registro.id
                                                    )
                                                }
                                            >
                                                Deletar
                                            </button>
                                            <button
                                                className="btn-visualizar"
                                                onClick={() =>
                                                    navigate(
                                                        `/public/usuarios/${usuario?.id}/timeline#${registro.id}`
                                                    )
                                                }
                                            >
                                                Visualizar
                                            </button>
                                        </div>
                                    </div>

                                    {confirmDelete === registro.id && (
                                        <div className="confirmacao-delete">
                                            <p>
                                                Tem certeza que deseja deletar
                                                este registro?
                                            </p>
                                            <div className="confirmacao-botoes">
                                                <button
                                                    className="btn-confirmar"
                                                    onClick={() =>
                                                        handleDeleteregistro(
                                                            registro.id
                                                        )
                                                    }
                                                >
                                                    Sim, Deletar
                                                </button>
                                                <button
                                                    className="btn-cancelar"
                                                    onClick={() =>
                                                        setConfirmDelete(null)
                                                    }
                                                >
                                                    Cancelar
                                                </button>
                                            </div>
                                        </div>
                                    )}
                                </div>
                            ))}
                        </div>
                    </section>
                )}
            </main>
        </div>
    );
};

export default Dashboard;
