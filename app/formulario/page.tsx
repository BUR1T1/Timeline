'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'

export default function Cadastro() {
  const router = useRouter()
  const [formData, setFormData] = useState({
    nome: '',
    sobrenome: '',
    fotoPerfil: '',
    idade: '',
    cpf: '',
    email: '',
    senha: '',
    confirmarSenha: '',
    apresentacao: '',
    sexo: '',
    consentimento: false,
  })

  const [error, setError] = useState('')

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value, type } = e.target
    if (type === 'checkbox') {
      setFormData({
        ...formData,
        [name]: (e.target as HTMLInputElement).checked,
      })
    } else {
      setFormData({
        ...formData,
        [name]: value,
      })
    }
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    setError('')

    // Validações
    if (formData.senha !== formData.confirmarSenha) {
      setError('As senhas não coincidem')
      return
    }

    if (!formData.consentimento) {
      setError('Você deve concordar com os termos de uso e política de privacidade')
      return
    }

    try {
      // Armazenar dados do usuário no localStorage
      const token = btoa(`${formData.email}:${formData.senha}`)
      localStorage.setItem('authToken', token)
      localStorage.setItem('userEmail', formData.email)
      localStorage.setItem('userData', JSON.stringify({
        nome: formData.nome,
        sobrenome: formData.sobrenome,
        email: formData.email,
        idade: formData.idade,
        cpf: formData.cpf,
        apresentacao: formData.apresentacao,
        sexo: formData.sexo,
      }))

      console.log('Dados do cadastro:', formData)
      
      // Redirecionar para home após cadastro bem-sucedido
      router.push('/home')
    } catch (err) {
      setError('Erro ao criar conta. Tente novamente.')
      console.error('Cadastro error:', err)
    }
  }

  return (
    <div className="isolate bg-gray-900 px-4 sm:px-6 py-24 sm:py-32 lg:px-8 min-h-screen">
      <div
        aria-hidden="true"
        className="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80"
      >
        <div
          style={{
            clipPath:
              'polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)',
          }}
          className="relative left-1/2 -z-10 aspect-1155/678 w-144.5 max-w-none -translate-x-1/2 rotate-30 bg-linear-to-tr from-[#ff80b5] to-[#9089fc] opacity-20 sm:left-[calc(50%-40rem)] sm:w-288.75"
        />
      </div>

      <div className="mx-auto max-w-2xl text-center">
        <h2 className="text-4xl font-semibold tracking-tight text-balance text-white sm:text-5xl">
          Criar conta
        </h2>
        <p className="mt-2 text-lg/8 text-gray-400">
          Crie sua conta para começar a registrar sua linha do tempo
        </p>
      </div>

      <form onSubmit={handleSubmit} className="mx-auto mt-16 max-w-2xl sm:mt-20">
        {error && (
          <div className="mb-6 rounded-md bg-red-500/10 border border-red-500/20 p-4">
            <p className="text-sm text-red-400">{error}</p>
          </div>
        )}

        {/* Campos Básicos */}
        <div className="mb-8">
          <h3 className="text-xl font-semibold text-white mb-6">📥 Informações Pessoais</h3>
          <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
            <div>
              <label htmlFor="nome" className="block text-sm/6 font-semibold text-white">
                Nome
              </label>
              <div className="mt-2.5">
                <input
                  id="nome"
                  name="nome"
                  type="text"
                  value={formData.nome}
                  onChange={handleChange}
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="Seu nome"
                />
              </div>
            </div>

            <div>
              <label htmlFor="sobrenome" className="block text-sm/6 font-semibold text-white">
                Sobrenome
              </label>
              <div className="mt-2.5">
                <input
                  id="sobrenome"
                  name="sobrenome"
                  type="text"
                  value={formData.sobrenome}
                  onChange={handleChange}
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="Seu sobrenome"
                />
              </div>
            </div>

            <div>
              <label htmlFor="idade" className="block text-sm/6 font-semibold text-white">
                Idade
              </label>
              <div className="mt-2.5">
                <input
                  id="idade"
                  name="idade"
                  type="number"
                  value={formData.idade}
                  onChange={handleChange}
                  min="13"
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="18"
                />
              </div>
            </div>

            <div>
              <label htmlFor="cpf" className="block text-sm/6 font-semibold text-white">
                CPF
              </label>
              <div className="mt-2.5">
                <input
                  id="cpf"
                  name="cpf"
                  type="text"
                  value={formData.cpf}
                  onChange={handleChange}
                  placeholder="000.000.000-00"
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                />
              </div>
            </div>

            <div>
              <label htmlFor="fotoPerfil" className="block text-sm/6 font-semibold text-white">
                Foto de Perfil
              </label>
              <div className="mt-2.5">
                <input
                  id="fotoPerfil"
                  name="fotoPerfil"
                  type="file"
                  accept="image/*"
                  value={formData.fotoPerfil}
                  onChange={handleChange}
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                />
              </div>
            </div>

            <div className="sm:col-span-2">
              <label htmlFor="email" className="block text-sm/6 font-semibold text-white">
                Email
              </label>
              <div className="mt-2.5">
                <input
                  id="email"
                  name="email"
                  type="email"
                  autoComplete="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="seu@email.com"
                />
              </div>
            </div>
          </div>
        </div>

        {/* Campos de Senha */}
        <div className="mb-8">
          <h3 className="text-xl font-semibold text-white mb-6">🔐 Segurança</h3>
          <div className="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
            <div>
              <label htmlFor="senha" className="block text-sm/6 font-semibold text-white">
                Senha
              </label>
              <div className="mt-2.5">
                <input
                  id="senha"
                  name="senha"
                  type="password"
                  value={formData.senha}
                  onChange={handleChange}
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="••••••••"
                />
              </div>
            </div>

            <div>
              <label htmlFor="confirmarSenha" className="block text-sm/6 font-semibold text-white">
                Confirmar Senha
              </label>
              <div className="mt-2.5">
                <input
                  id="confirmarSenha"
                  name="confirmarSenha"
                  type="password"
                  value={formData.confirmarSenha}
                  onChange={handleChange}
                  required
                  className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                  placeholder="••••••••"
                />
              </div>
            </div>
          </div>
        </div>

        {/* Informações Adicionais */}
        <div className="mb-8">
          <h3 className="text-xl font-semibold text-white mb-6">🧑‍💬 Sobre Você</h3>
          <div className="sm:col-span-2">
            <label htmlFor="apresentacao" className="block text-sm/6 font-semibold text-white">
              Breve Apresentação
            </label>
            <div className="mt-2.5">
              <textarea
                id="apresentacao"
                name="apresentacao"
                value={formData.apresentacao}
                onChange={handleChange}
                rows={4}
                className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
                placeholder="Conte um pouco sobre você..."
              />
            </div>
          </div>
        </div>

        {/* Identificação */}
        <div className="mb-8">
          <h3 className="text-xl font-semibold text-white mb-6">⚧️ Identificação</h3>
          <div>
            <label htmlFor="sexo" className="block text-sm/6 font-semibold text-white">
              Sexo
            </label>
            <div className="mt-2.5">
              <select
                id="sexo"
                name="sexo"
                value={formData.sexo}
                onChange={handleChange}
                className="block w-full rounded-md bg-white/5 px-3.5 py-2 text-base text-white outline-1 -outline-offset-1 outline-white/10 placeholder:text-gray-500 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-500"
              >
                <option value="">Selecione uma opção</option>
                <option value="masculino">Masculino</option>
                <option value="feminino">Feminino</option>
                <option value="outro">Outro</option>
                <option value="nao-informar">Prefiro não informar</option>
              </select>
            </div>
          </div>
        </div>

        {/* Consentimento */}
        <div className="mb-8">
          <h3 className="text-xl font-semibold text-white mb-6">🔒 Consentimento</h3>
          <div className="flex gap-x-4">
            <div className="flex h-6 items-center">
              <div className="group relative inline-flex w-8 shrink-0 rounded-full bg-white/5 p-px inset-ring inset-ring-white/10 outline-offset-2 outline-indigo-500 transition-colors duration-200 ease-in-out has-checked:bg-indigo-500 has-focus-visible:outline-2">
                <span className="size-4 rounded-full bg-white shadow-xs ring-1 ring-gray-900/5 transition-transform duration-200 ease-in-out group-has-checked:translate-x-3.5" />
                <input
                  id="consentimento"
                  name="consentimento"
                  type="checkbox"
                  checked={formData.consentimento}
                  onChange={handleChange}
                  required
                  aria-label="Concordar com termos"
                  className="absolute inset-0 size-full appearance-none focus:outline-hidden"
                />
              </div>
            </div>
            <label htmlFor="consentimento" className="text-sm/6 text-gray-400">
              Concordo com os{' '}
              <a href="#" className="font-semibold whitespace-nowrap text-indigo-400 hover:text-indigo-300">
                termos de uso
              </a>
              {' '}e{' '}
              <a href="#" className="font-semibold whitespace-nowrap text-indigo-400 hover:text-indigo-300">
                política de privacidade
              </a>
            </label>
          </div>
        </div>

        {/* Botão de Ação */}
        <div className="mt-10">
          <button
            type="submit"
            className="block w-full rounded-md bg-indigo-500 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-xs hover:bg-indigo-400 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-500 transition-colors"
          >
            🚀 Criar conta
          </button>
        </div>

        <p className="text-center text-sm text-gray-400 mt-6">
          Já tem conta?{' '}
          <a href="/login" className="font-semibold text-indigo-400 hover:text-indigo-300">
            Entre aqui
          </a>
        </p>
        <p className="text-center text-sm text-gray-400 mt-4">
          <a href="/" className="hover:text-indigo-400">
            Voltar para home
          </a>
        </p>
      </form>
    </div>
  )
}
