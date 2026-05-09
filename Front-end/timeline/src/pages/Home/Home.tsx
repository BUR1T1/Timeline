import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../components/Header';
import './Home.css';

const Home: React.FC = () => {
    const navigate = useNavigate();

    return (
        <div className="home-page">
            <Header />
            <main className="home-main">
                <section className="hero-section">
                    <div className="hero-content">
                        <h1 className="hero-title">Crie Sua Timeline Profissional</h1>
                        <p className="hero-subtitle">
                            Compartilhe sua trajetória de forma visual e impactante.
                            Mostre suas conquistas para recrutadores e conexões profissionais.
                        </p>
                        <div className="hero-buttons">
                            <button className="btn-primary" onClick={() => navigate('/login')}>
                                Entrar
                            </button>
                            <button className="btn-secondary" onClick={() => navigate('/cadastro')}>
                                Criar Conta
                            </button>
                        </div>
                    </div>
                    <div className="hero-visual">
                        <div className="timeline-preview">
                            <div className="timeline-item">
                                <div className="timeline-dot"></div>
                                <div className="timeline-content">
                                    <h3>Graduação</h3>
                                    <p>2018 - 2022</p>
                                </div>
                            </div>
                            <div className="timeline-item">
                                <div className="timeline-dot"></div>
                                <div className="timeline-content">
                                    <h3>Primeiro Emprego</h3>
                                    <p>2022 - 2024</p>
                                </div>
                            </div>
                            <div className="timeline-item">
                                <div className="timeline-dot"></div>
                                <div className="timeline-content">
                                    <h3>Projeto Atual</h3>
                                    <p>2024 - Presente</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section className="features-section">
                    <h2>Por que escolher nossa plataforma?</h2>
                    <div className="features-grid">
                        <div className="feature-card">
                            <div className="feature-icon">📊</div>
                            <h3>Métricas Detalhadas</h3>
                            <p>Acompanhe o engajamento da sua timeline com estatísticas completas.</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">🔗</div>
                            <h3>Compartilhamento Fácil</h3>
                            <p>Compartilhe seu link profissional com recrutadores e conexões.</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">🎨</div>
                            <h3>Design Atraente</h3>
                            <p>Apresente sua carreira de forma visual e profissional.</p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">🔒</div>
                            <h3>Privacidade</h3>
                            <p>Controle quem vê sua timeline e mantenha seus dados seguros.</p>
                        </div>
                    </div>
                </section>

                <section className="cta-section">
                    <h2>Pronto para começar sua jornada?</h2>
                    <p>Crie sua timeline hoje e dê o próximo passo na sua carreira.</p>
                    <button className="btn-primary" onClick={() => navigate('/cadastro')}>
                        Começar Agora
                    </button>
                </section>
            </main>
        </div>
    );
};

export default Home;