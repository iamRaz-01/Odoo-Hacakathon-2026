import axios from 'axios';
import { useAuthStore } from '../store/authStore';
const httpClient = axios.create({ baseURL: import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080/api/v1', timeout: 15_000, headers: { 'Content-Type': 'application/json' } });
httpClient.interceptors.request.use((config) => { const token = useAuthStore.getState().accessToken; if (token) config.headers.Authorization = `Bearer ${token}`; return config; });
httpClient.interceptors.response.use((response) => response, (error) => { if (error.response?.status === 401) useAuthStore.getState().clearSession(); return Promise.reject(error); });
export default httpClient;
