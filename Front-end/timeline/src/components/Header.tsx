import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Header.css';

const Header: React.FC = () => {
    const navigate = useNavigate();
    const isLoggedIn = !!localStorage.getItem('token');

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    return (
        <header className="header">
            <div className="header-container">
                <h1 className="header-title" onClick={() => navigate('/')} style={{cursor: 'pointer'}}>
                    Timeline
                </h1>
                <nav className="header-nav">
                    {isLoggedIn ? (
                        <>
                            <button className="nav-button" onClick={() => navigate('/minha-timeline')}>
                                Minha Timeline
                            </button>
                            <button className="nav-button" onClick={() => navigate('/timeline/criar')}>
                                Criar Registro
                            </button>
                            <button className="nav-button" onClick={() => navigate('/metricas')}>
                                Métricas
                            </button>
                            <button className="nav-button logout" onClick={handleLogout}>
                                Logout
                            </button>
                        </>
                    ) : (
                        <>
                            <button className="nav-button" onClick={() => navigate('/login')}>
                                Entrar
                            </button>
                            <button className="nav-button" onClick={() => navigate('/cadastro')}>
                                Cadastrar
                            </button>
                        </>
                    )}
                </nav>
            </div>
        </header>
    );
};

export default Header;