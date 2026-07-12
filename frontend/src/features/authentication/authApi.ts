import httpClient from '../../api/httpClient';
import type { ApiResponse } from '../../types/api';
import type { JwtResponse, LoginResponse, User } from '../../types/auth';
export const authApi = { login: async (email: string, password: string) => (await httpClient.post<ApiResponse<LoginResponse>>('/auth/login', { email, password })).data.data, logout: async () => { await httpClient.post('/auth/logout'); }, refresh: async (refreshToken: string) => (await httpClient.post<ApiResponse<JwtResponse>>('/auth/refresh', { refreshToken })).data.data, me: async () => (await httpClient.get<ApiResponse<User>>('/auth/me')).data.data };
