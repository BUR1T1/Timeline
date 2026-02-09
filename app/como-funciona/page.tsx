import { MapPinIcon, ClockIcon, SparklesIcon, ArrowRightIcon } from '@heroicons/react/24/solid'

export default function Page() {
  return (
    <main className="min-h-screen bg-gray-950 text-gray-200 selection:bg-indigo-500/30 overflow-x-hidden">
      {/* Background dinâmico */}
      <div className="fixed inset-0 z-0">
        <div className="absolute top-[-10%] left-[-10%] w-[40%] h-[40%] rounded-full bg-indigo-500/10 blur-[120px]" />
        <div className="absolute bottom-[10%] right-[-5%] w-[30%] h-[30%] rounded-full bg-fuchsia-500/10 blur-[120px]" />
      </div>

      <div className="relative z-10 max-w-6xl mx-auto px-6 pt-20 pb-32">
        <div className="lg:flex lg:items-start lg:gap-20">
          
          {/* Coluna da Esquerda: Título Fixo */}
          <div className="lg:w-1/3 lg:sticky lg:top-20 mb-16 lg:mb-0">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-indigo-500/10 border border-indigo-500/20 text-xs font-bold text-indigo-400 uppercase tracking-widest mb-6">
              Guia de Uso
            </div>
            <h1 className="text-5xl lg:text-6xl font-black tracking-tighter text-white leading-none">
              Como <br />
              <span className="bg-gradient-to-r from-indigo-400 to-fuchsia-400 bg-clip-text text-transparent">
                funciona
              </span>
            </h1>
            <p className="mt-6 text-lg text-gray-400 leading-relaxed">
              Transforme caos em cronologia. Entenda o fluxo da Timeline para organizar sua trajetória.
            </p>
            
            <div className="mt-10 hidden lg:block">
              <div className="flex -space-x-2">
                {[1, 2, 3, 4].map((i) => (
                  <div key={i} className="w-10 h-10 rounded-full border-2 border-gray-950 bg-gray-800 flex items-center justify-center text-xs font-bold">
                    {i}
                  </div>
                ))}
                <div className="pl-4 text-sm text-gray-500 self-center">+ 500 momentos criados</div>
              </div>
            </div>
          </div>

          {/* Coluna da Direita: Timeline de Passos */}
          <div className="lg:w-2/3 relative">
            {/* Linha vertical conectora (Desktop) */}
            <div className="absolute left-8 top-0 bottom-0 w-px bg-gradient-to-b from-indigo-500/50 via-gray-800 to-transparent hidden md:block" />

            <div className="space-y-12">
              
              {/* Passo 1 */}
              <section className="relative md:pl-20 group">
                <div className="absolute left-4 md:left-5 -translate-x-1/2 w-8 h-8 rounded-full bg-gray-950 border-2 border-indigo-500 flex items-center justify-center z-10 group-hover:scale-110 transition-transform shadow-[0_0_15px_rgba(99,102,241,0.5)]">
                  <MapPinIcon className="h-3.5 w-3.5 text-indigo-400" />
                </div>
                <div className="bg-white/[0.03] border border-white/5 p-8 rounded-2xl hover:bg-white/[0.05] transition-all hover:border-white/10">
                  <h2 className="text-2xl font-bold text-white mb-4">Registre Seus Momentos</h2>
                  <p className="text-gray-400 leading-relaxed">
                    A Timeline foi pensada para transformar acontecimentos em uma história visual. 
                    Registre momentos importantes, anexe memórias e veja sua evolução ganhar forma.
                  </p>
                </div>
              </section>

              {/* Passo 2 */}
              <section className="relative md:pl-20 group">
                <div className="absolute left-4 md:left-5 -translate-x-1/2 w-8 h-8 rounded-full bg-gray-950 border-2 border-fuchsia-500 flex items-center justify-center z-10 group-hover:scale-110 transition-transform shadow-[0_0_15px_rgba(217,70,239,0.5)]">
                  <ClockIcon className="h-3.5 w-3.5 text-fuchsia-400" />
                </div>
                <div className="bg-white/[0.03] border border-white/5 p-8 rounded-2xl hover:bg-white/[0.05] transition-all hover:border-white/10">
                  <h2 className="text-2xl font-bold text-white mb-4">Pontos da Sua História</h2>
                  <p className="text-gray-400 leading-relaxed">
                    Cada registro é um marco. Seja um projeto acadêmico ou uma conquista pessoal, 
                    nossa estrutura permite visualizar o passado para planejar o futuro com clareza.
                  </p>
                </div>
              </section>

              {/* Passo 3 */}
              <section className="relative md:pl-20 group">
                <div className="absolute left-4 md:left-5 -translate-x-1/2 w-8 h-8 rounded-full bg-gray-950 border-2 border-emerald-500 flex items-center justify-center z-10 group-hover:scale-110 transition-transform shadow-[0_0_15px_rgba(16,185,129,0.5)]">
                  <SparklesIcon className="h-3.5 w-3.5 text-emerald-400" />
                </div>
                <div className="bg-white/[0.03] border border-white/5 p-8 rounded-2xl hover:bg-white/[0.05] transition-all hover:border-white/10">
                  <h2 className="text-2xl font-bold text-white mb-4">Simples e Intuitivo</h2>
                  <p className="text-gray-400 leading-relaxed">
                    Sem complexidade desnecessária. Focamos na essência: sua história contada através do tempo 
                    em uma interface limpa e rápida.
                  </p>
                </div>
              </section>

              {/* CTA Final */}
              <div className="md:pl-20 pt-6">
                <a
                  href="/formulario"
                  className="group relative inline-flex items-center gap-3 bg-white text-gray-950 px-8 py-4 rounded-xl font-bold text-lg hover:bg-indigo-50 transition-all shadow-[0_20px_40px_rgba(255,255,255,0.1)]"
                >
                  Comece sua jornada
                  <ArrowRightIcon className="h-5 w-5 group-hover:translate-x-1 transition-transform" />
                </a>
              </div>

            </div>
          </div>
        </div>
      </div>
    </main>
  );
}