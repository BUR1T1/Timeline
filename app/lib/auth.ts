// Small client-side auth helper with a predefined admin user
// Usage: import { login, logout, isAuthenticated, getUser } from '~/app/lib/auth'

export type User = {
  username: string
  email: string
  role: 'admin' | 'user'
}

const PREDEFINED = {
  username: 'adm',
  password: '123',
  email: 'adm@example.com',
  role: 'admin' as const,
}

const AUTH_TOKEN_KEY = 'authToken'
const USER_EMAIL_KEY = 'userEmail'
const USER_ROLE_KEY = 'userRole'

export function login(username: string, password: string) {
  // Simple check against the predefined credential set
  if (username === PREDEFINED.username && password === PREDEFINED.password) {
    try {
      const token = btoa(`${username}:${Date.now()}`)
      localStorage.setItem(AUTH_TOKEN_KEY, token)
      localStorage.setItem(USER_EMAIL_KEY, PREDEFINED.email)
      localStorage.setItem(USER_ROLE_KEY, PREDEFINED.role)
      return {
        ok: true,
        user: {
          username: PREDEFINED.username,
          email: PREDEFINED.email,
          role: PREDEFINED.role,
        } as User,
      }
    } catch (e) {
      return { ok: false, error: 'storage_error' }
    }
  }

  return { ok: false, error: 'invalid_credentials' }
}

export function logout() {
  localStorage.removeItem(AUTH_TOKEN_KEY)
  localStorage.removeItem(USER_EMAIL_KEY)
  localStorage.removeItem(USER_ROLE_KEY)
}

export function isAuthenticated() {
  return typeof window !== 'undefined' && !!localStorage.getItem(AUTH_TOKEN_KEY)
}

export function getUser(): User | null {
  if (typeof window === 'undefined') return null
  const email = localStorage.getItem(USER_EMAIL_KEY)
  const role = (localStorage.getItem(USER_ROLE_KEY) as 'admin' | 'user' | null) || null
  if (!email) return null
  return {
    username: PREDEFINED.username,
    email,
    role: (role as 'admin' | 'user') || 'user',
  }
}

// helper for protecting client components/pages (returns true/false)
export function requireAuth(): boolean {
  return isAuthenticated()
}
