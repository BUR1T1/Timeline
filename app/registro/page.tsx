'use client'

import { useState, useEffect } from 'react'
import { useRouter } from 'next/navigation'
import {
  CalendarDaysIcon,
  DocumentTextIcon,
  PhotoIcon,
  PlusIcon,
  XMarkIcon,
  ArrowLeftIcon,
  SparklesIcon,
} from '@heroicons/react/24/solid'

export default function AdicionarRegistro() {
  const router = useRouter()
  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const [isLoading, setIsLoading] = useState(true)

  const [formData, setFormData] = useState({
    titulo: '',
    descricao: '',
    dataInicio: '',
    dataFim: '',
    imagens: [] as File[],
  })

  const [preview, setPreview] = useState<string[]>([])

  useEffect(() => {
    const token = localStorage.getItem('authToken')
    const userEmail = localStorage.getItem('userEmail')
    if (!token || !userEmail) {
      router.push('/login')
    } else {
      setIsAuthenticated(true)
    }
    setIsLoading(false)
  }, [router])

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target
    setFormData({ ...formData, [name]: value })
  }

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = Array.from(e.target.files || [])
    const newFiles = [...formData.imagens, ...files]
    setFormData({ ...formData, imagens: newFiles })

    const readers = files.map(file => {
      return new Promise<string>((resolve) => {
        const reader = new FileReader()
        reader.onloadend = () => resolve(reader.result as string)
        reader.readAsDataURL(file)
      })
    })

    Promise.all(readers).then(results => {
      setPreview(prev => [...prev, ...results])
    })
  }

  const removeImage = (index: number) => {
    setFormData({ ...formData, imagens: formData.imagens.filter((_, i) => i !== index) })
    setPreview(preview.filter((_, i) => i !== index))
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log('Dados do registro:', formData)
  }

  if (isLoading) return (
    <div className="min-h-screen bg-gray-950 flex items-center justify-center">
      <div className="w-8 h-8 border-4 border-indigo-500 border-t-transparent rounded-full animate-spin"></div>
    </div>
  )

  if (!isAuthenticated) return null

  return (
    <main className="min-h-screen bg-gray-950 text-gray-200 selection:bg-indigo-500/30">
      {/* Background Decorativo */}
      <div className="fixed inset-0 overflow-hidden pointer-events-none">
        <div className="absolute top-0 right-0 w-[500px] h-[500px] bg-indigo-600/10 blur-[120px] rounded-full -translate-y-1/2 translate-x-1/2" />
        <div className="absolute bottom-0 left-0 w-[400px] h-[400px] bg-fuchsia-600/5 blur-[100px] rounded-full translate-y-1/2 -translate-x-1/2" />
      </div>

      <div className="relative max-w-6xl mx-auto px-6 py-12">
        {/* Header com Voltar */}
          <button 
          onClick={() => router.push('/home')}
          className="flex items-center gap-2 text-gray-400 hover:text-white transition-colors mb-10 group"
        >
          <ArrowLeftIcon className="h-4 w-4 group-hover:-translate-x-1 transition-transform" />
          <span>Voltar para a Timeline</span>
        </button>

        <div className="lg:grid lg:grid-cols-12 lg:gap-16">
          {/* Coluna da Esquerda: Info */}
          <div className="lg:col-span-4 mb-12 lg:mb-0">
            <h1 className="text-4xl font-bold text-white tracking-tight mb-4">
              Novo <span className="text-indigo-400">Momento</span>
            </h1>
            <p className="text-gray-400 leading-relaxed mb-8">
              Preencha os detalhes ao lado para eternizar este capítulo da sua história. Suas fotos e datas ajudam a construir uma narrativa visual clara.
            </p>
            
            <div className="space-y-4">
                <div className="flex items-center gap-3 p-4 rounded-xl bg-white/5 border border-white/10">
                    <div className="p-2 bg-indigo-500/10 rounded-lg text-indigo-400"><PlusIcon className="h-5 w-5" /></div>
                    <p className="text-sm">Campos obrigatórios marcados</p>
                </div>
            </div>
          </div>

          {/* Coluna da Direita: Formulário */}
          <div className="lg:col-span-8">
            <form onSubmit={handleSubmit} className="space-y-8 bg-white/[0.03] border border-white/10 p-8 rounded-2xl backdrop-blur-sm">
              
              {/* Seção 1: Conteúdo */}
              <div className="space-y-6">
                <div className="flex items-center gap-2 text-indigo-400 font-medium mb-2">
                  <DocumentTextIcon className="h-4 w-4" />
                  <h3>Conteúdo Principal</h3>
                </div>
                
                <div className="grid gap-6">
                  <div className="space-y-2">
                    <label className="text-sm font-medium text-gray-300">Título do Momento</label>
                    <input
                      name="titulo"
                      value={formData.titulo}
                      onChange={handleChange}
                      placeholder="Ex: Viagem de Formatura"
                      className="w-full bg-gray-900/50 border border-white/10 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all"
                      required
                    />
                  </div>

                  <div className="space-y-2">
                    <label className="text-sm font-medium text-gray-300">Descrição</label>
                    <textarea
                      name="descricao"
                      value={formData.descricao}
                      onChange={handleChange}
                      rows={4}
                      placeholder="O que aconteceu de especial?"
                      className="w-full bg-gray-900/50 border border-white/10 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-indigo-500/50 focus:border-indigo-500 transition-all resize-none"
                    />
                  </div>
                </div>
              </div>

              {/* Seção 2: Datas */}
              <div className="pt-8 border-t border-white/5 space-y-6">
                <div className="flex items-center gap-2 text-fuchsia-400 font-medium mb-2">
                  <CalendarDaysIcon className="h-4 w-4" />
                  <h3>Quando isso aconteceu?</h3>
                </div>
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                  <div className="space-y-2">
                    <label className="text-sm font-medium text-gray-300">Data de Início</label>
                    <input
                      type="date"
                      name="dataInicio"
                      value={formData.dataInicio}
                      onChange={handleChange}
                      className="w-full bg-gray-900/50 border border-white/10 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-indigo-500/50 transition-all [color-scheme:dark]"
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <label className="text-sm font-medium text-gray-300">Data de Término (Opcional)</label>
                    <input
                      type="date"
                      name="dataFim"
                      value={formData.dataFim}
                      onChange={handleChange}
                      className="w-full bg-gray-900/50 border border-white/10 rounded-xl px-4 py-3 focus:outline-none focus:ring-2 focus:ring-indigo-500/50 transition-all [color-scheme:dark]"
                    />
                  </div>
                </div>
              </div>

              {/* Seção 3: Imagens */}
              <div className="pt-8 border-t border-white/5 space-y-6">
                <div className="flex items-center gap-2 text-emerald-400 font-medium mb-2">
                  <PhotoIcon className="h-4 w-4" />
                  <h3>Registros Visuais</h3>
                </div>
                
                <div className="relative group">
                  <input
                    type="file"
                    multiple
                    accept="image/*"
                    onChange={handleImageChange}
                    className="absolute inset-0 w-full h-full opacity-0 cursor-pointer z-10"
                  />
                  <div className="border-2 border-dashed border-white/10 group-hover:border-indigo-500/50 group-hover:bg-indigo-500/5 rounded-2xl p-8 transition-all text-center">
                    <div className="mx-auto w-12 h-12 bg-white/5 rounded-full flex items-center justify-center mb-4 text-gray-400 group-hover:text-indigo-400 transition-colors">
                      <PlusIcon className="h-6 w-6" />
                    </div>
                    <p className="text-sm font-medium">Clique para subir imagens</p>
                    <p className="text-xs text-gray-500 mt-1">PNG, JPG ou WebP (Múltiplas)</p>
                  </div>
                </div>

                {/* Grid de Preview */}
                {preview.length > 0 && (
                  <div className="grid grid-cols-2 sm:grid-cols-4 gap-4 mt-6">
                    {preview.map((img, index) => (
                      <div key={index} className="relative aspect-square group overflow-hidden rounded-xl border border-white/10">
                        <img src={img} alt="Preview" className="w-full h-full object-cover transition-transform group-hover:scale-110" />
                          <button
                          type="button"
                          onClick={() => removeImage(index)}
                          className="absolute top-2 right-2 p-1.5 bg-red-500 text-white rounded-full opacity-0 group-hover:opacity-100 transition-opacity shadow-lg"
                        >
                          <XMarkIcon className="h-3.5 w-3.5" />
                        </button>
                      </div>
                    ))}
                  </div>
                )}
              </div>

              {/* Botão Final */}
              <div className="pt-4">
                <button
                  type="submit"
                  className="w-full bg-indigo-600 hover:bg-indigo-500 text-white font-bold py-4 rounded-xl shadow-lg shadow-indigo-500/20 transition-all flex items-center justify-center gap-2 group"
                >
                  <SparklesIcon className="h-5 w-5 group-hover:animate-pulse" />
                  Salvar Registro na Timeline
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </main>
  )
}