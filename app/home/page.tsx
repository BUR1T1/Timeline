'use client'

import { useState, useEffect } from 'react'
import { useRouter } from 'next/navigation'

export default function Home() {
  const router = useRouter()
  const [userEmail, setUserEmail] = useState('')
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  useEffect(() => {
    const token = localStorage.getItem('authToken')
    const email = localStorage.getItem('userEmail')

    if (token && email) {
      setIsAuthenticated(true)
      setUserEmail(email)
    } else {
      setIsAuthenticated(false)
    }
  }, [])

  const handleLogout = () => {
    localStorage.removeItem('authToken')
    localStorage.removeItem('userEmail')
    localStorage.removeItem('userData')
    router.push('/login')
  }

  const handleAddRegistro = () => {
    if (isAuthenticated) {
      router.push('/registro')
    } else {
      router.push('/login')
    }
  }

  return (
    <div className="bg-gray-900">

      <div className="relative isolate overflow-hidden px-4 sm:px-6 pt-16 sm:pt-20 lg:px-8">
        <div
          aria-hidden="true"
          className="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80"
        >
          <div
            style={{
              clipPath:
                'polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)',
            }}
            className="relative left-[calc(50%-11rem)] aspect-1155/678 w-144.5 -translate-x-1/2 rotate-30 bg-linear-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-30rem)] sm:w-288.75"
          />
        </div>
        <div className="mx-auto max-w-4xl py-16 sm:py-32 md:py-40 lg:py-56">
          <div className="mb-4 sm:mb-8 flex justify-center">
            <div className="relative rounded-full px-2 py-1 text-xs sm:text-sm/6 text-gray-400 ring-1 ring-white/10 hover:ring-white/20">
              Registre momentos importantes.{' '}
              <a href="#" className="font-semibold text-indigo-400">
                Ver novidades <span aria-hidden="true">&rarr;</span>
              </a>
            </div>
          </div>
          <div className="text-center">
            <h1 className="text-3xl font-bold tracking-tight text-balance text-white sm:text-5xl lg:text-6xl">
              Sua história em uma linha do tempo
            </h1>
            <p className="mt-4 sm:mt-6 text-base sm:text-lg font-medium text-pretty text-gray-400 lg:text-xl">
              Crie, acompanhe e compartilhe momentos que marcaram sua vida ou seus projetos.
              <br />
              Uma plataforma simples para transformar acontecimentos em histórias visuais.
            </p>
            <div className="mt-8 sm:mt-10 flex flex-col sm:flex-row items-center justify-center gap-4 sm:gap-6">
              <button
                onClick={handleAddRegistro}
                className="w-full sm:w-auto rounded-md bg-indigo-500 px-4 sm:px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-xs hover:bg-indigo-400 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-500 transition-colors"
              >
                Criar minha timeline
              </button>
              <a href="#" className="text-sm font-semibold text-white hover:text-indigo-400 transition-colors">
                Explorar timelines <span aria-hidden="true">→</span>
              </a>
            </div>
          </div>
        </div>
        <div
          aria-hidden="true"
          className="absolute inset-x-0 top-[calc(100%-13rem)] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[calc(100%-30rem)]"
        >
          <div
            style={{
              clipPath:
                'polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)',
            }}
            className="relative left-[calc(50%+3rem)] aspect-1155/678 w-144.5 -translate-x-1/2 bg-linear-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%+36rem)] sm:w-288.75"
          />
        </div>
      </div>
    </div>
  )
}
