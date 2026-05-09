import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../components/Header';
import './Landing.css';

const Landing: React.FC = () => {
    const navigate = useNavigate();
    const [timelineUrl, setTimelineUrl] = useState('');

    const handleViewTimeline = () => {
        if (timelineUrl.trim()) {
            // Extract usuarioId from URL like /public/usuarios/123/timeline
            const match = timelineUrl.match(/\/public\/usuarios\/(\d+)\/timeline/);
            if (match) {
                navigate(`/public/usuarios/${match[1]}/timeline`);
            } else {
                alert('URL inválida. Use o formato: /public/usuarios/ID/timeline');
            }
        }
    };

    return (
        <div className="landing-page">
            <Header />
            <main className="landing-main">
                <section className="hero-section">
                    <h1 className="hero-title">Crie e Compartilhe Sua Trajetória</h1>
                    <p className="hero-subtitle">
                        Construa sua linha do tempo profissional e compartilhe com recrutadores e conexões.
                        Mostre sua jornada de forma visual e impactante.
                    </p>
                    <div className="hero-buttons">
                        <button className="btn-primary" onClick={() => navigate('/login')}>
                            Entrar
                        </button>
                        <button className="btn-secondary" onClick={() => navigate('/cadastro')}>
                            Criar Conta
                        </button>
                    </div>
                </section>

                <section className="features-section">
                    <h2>Por que escolher nossa plataforma?</h2>
                    <div className="features-grid">
                        <div className="feature-card">
                            <h3>Visualização Interativa</h3>
                            <p>Apresente sua carreira de forma cronológica e atrativa.</p>
                        </div>
                        <div className="feature-card">
                            <h3>Compartilhamento Fácil</h3>
                            <p>Compartilhe seu link único com recrutadores e profissionais.</p>
                        </div>
                        <div className="feature-card">
                            <h3>Métricas de Engajamento</h3>
                            <p>Acompanhe likes, comentários e interações em seus registros.</p>
                        </div>
                        <div className="feature-card">
                            <h3>Perfil Público</h3>
                            <p>Permita que outros vejam sua trajetória profissional.</p>
                        </div>
                    </div>
                </section>

                <section className="share-section">
                    <h2>Veja Timelines de Outros Usuários</h2>
                    <p>Insira o link de uma timeline pública para visualizá-la:</p>
                    <div className="share-input-group">
                        <input
                            type="text"
                            placeholder="Ex: /public/usuarios/123/timeline"
                            value={timelineUrl}
                            onChange={(e) => setTimelineUrl(e.target.value)}
                            className="share-input"
                        />
                        <button className="btn-share" onClick={handleViewTimeline}>
                            Visualizar
                        </button>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default Landing;