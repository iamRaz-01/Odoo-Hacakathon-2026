import httpClient from '../../api/httpClient';
import type { ApiResponse } from '../../types/api';
import type { Role, User } from '../../types/auth';
export interface UserPayload { firstName: string; lastName: string; email: string; password?: string; roleId: number; }
export const userApi = { list: async () => (await httpClient.get<ApiResponse<User[]>>('/users')).data.data, create: async (payload: Required<UserPayload>) => (await httpClient.post<ApiResponse<User>>('/users', payload)).data.data, update: async (id: number, payload: UserPayload) => (await httpClient.put<ApiResponse<User>>(`/users/${id}`, payload)).data.data, activate: async (id: number) => (await httpClient.patch<ApiResponse<User>>(`/users/${id}/activate`)).data.data, deactivate: async (id: number) => (await httpClient.patch<ApiResponse<User>>(`/users/${id}/deactivate`)).data.data, roles: async () => (await httpClient.get<ApiResponse<Role[]>>('/roles')).data.data };
