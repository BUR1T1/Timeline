import { BookOpenIcon, TagIcon, AcademicCapIcon } from '@heroicons/react/24/solid'

export default function Page() {
  return (
    <main className="min-h-screen bg-gray-900 text-gray-100 selection:bg-indigo-500/30">
      {/* Fundo Decorativo */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute -top-[10%] -left-[10%] w-[40%] h-[40%] rounded-full bg-indigo-500/10 blur-[120px]" />
        <div className="absolute top-[20%] -right-[5%] w-[30%] h-[30%] rounded-full bg-fuchsia-500/10 blur-[120px]" />
      </div>

      <div className="relative max-w-7xl mx-auto px-6 pt-20 pb-24 lg:flex lg:items-start lg:gap-x-12">
        
        {/* Lado Esquerdo: Título Fixo (Desktop) */}
        <div className="lg:w-1/3 lg:sticky lg:top-20 mb-12 lg:mb-0">
          <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-white/5 border border-white/10 text-xs font-medium text-indigo-400 mb-6">
            <span className="relative flex h-2 w-2">
              <span className="animate-ping absolute inline-flex h-full w-full rounded-full bg-indigo-400 opacity-75"></span>
              <span className="relative inline-flex rounded-full h-2 w-2 bg-indigo-500"></span>
            </span>
            Projeto Acadêmico
          </div>
          
          <h1 className="text-5xl lg:text-6xl font-extrabold tracking-tight bg-gradient-to-br from-white to-gray-500 bg-clip-text text-transparent">
            Sobre o <br /> 
            <span className="text-indigo-400">Timeline</span>
          </h1>
          
          <p className="mt-6 text-lg text-gray-400 leading-relaxed max-w-md">
            Uma exploração visual sobre como organizamos histórias e dados através do tempo.
          </p>
        </div>

        {/* Lado Direito: Cards de Conteúdo */}
        <div className="lg:w-2/3 space-y-6">
          
          {/* Card 1 */}
          <section className="group relative p-8 rounded-2xl bg-white/[0.02] border border-white/5 hover:border-white/10 transition-all">
            <div className="flex items-start gap-6">
              <div className="p-3 rounded-lg bg-indigo-500/10 text-indigo-400">
                <BookOpenIcon className="h-6 w-6" />
              </div>
              <div>
                <h2 className="text-xl font-bold text-white mb-3">Um Projeto Acadêmico</h2>
                <p className="text-gray-400 leading-relaxed text-balance">
                  Desenvolvido para colocar em prática conhecimentos de frontend moderno, explorando 
                  a construção de interfaces que priorizam a performance e a experiência do usuário.
                </p>
              </div>
            </div>
          </section>

          {/* Card 2 */}
          <section className="group relative p-8 rounded-2xl bg-white/[0.02] border border-white/5 hover:border-white/10 transition-all">
            <div className="flex items-start gap-6">
              <div className="p-3 rounded-lg bg-fuchsia-500/10 text-fuchsia-400">
                <TagIcon className="h-6 w-6" />
              </div>
              <div>
                <h2 className="text-xl font-bold text-white mb-3">A Proposta da Timeline</h2>
                <p className="text-gray-400 leading-relaxed text-balance">
                  Buscamos criar uma experiência intuitiva, focada em contar histórias através do tempo. 
                  Aqui, a clareza e a simplicidade são os pilares de cada interação.
                </p>
              </div>
            </div>
          </section>

          {/* Card 3 */}
          <section className="group relative p-8 rounded-2xl bg-white/[0.02] border border-white/5 hover:border-white/10 transition-all">
            <div className="flex items-start gap-6">
              <div className="p-3 rounded-lg bg-emerald-500/10 text-emerald-400">
                <AcademicCapIcon className="h-6 w-6" />
              </div>
              <div>
                <h2 className="text-xl font-bold text-white mb-3">Educação e Evolução</h2>
                <p className="text-gray-400 leading-relaxed text-balance">
                  Este projeto serve como laboratório para testes de novas tecnologias e metodologias, 
                  respeitando a importância de criar ferramentas que tragam valor real ao aprendizado.
                </p>
              </div>
            </div>
          </section>

        </div>
      </div>
    </main>
  );
}
 