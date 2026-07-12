import { Card, CardContent, Container, Typography } from '@mui/material';
import { Navigate, useNavigate } from 'react-router-dom';
import { useMutation } from '@tanstack/react-query';
import { authApi } from './authApi';
import { LoginForm } from './LoginForm';
import { useAuthStore } from '../../store/authStore';
import type { LoginValues } from './loginSchema';
function errorMessage(error: unknown) { return error instanceof Error ? error.message : 'Unable to sign in. Please try again.'; }
export function LoginPage() { const navigate = useNavigate(); const user = useAuthStore((state) => state.user); const setSession = useAuthStore((state) => state.setSession); const login = useMutation({ mutationFn: ({ email, password }: LoginValues) => authApi.login(email, password), onSuccess: (response, values) => { setSession(response, values.remember); navigate('/dashboard', { replace: true }); } }); if (user) return <Navigate to="/dashboard" replace/>; return <Container maxWidth="sm" sx={{ minHeight: '100vh', display: 'grid', placeItems: 'center' }}><Card sx={{ width: '100%' }}><CardContent sx={{ p: 4 }}><Typography variant="h4" component="h1" gutterBottom>TransitOps</Typography><Typography color="text.secondary" sx={{ mb: 3 }}>Sign in to manage transport operations.</Typography><LoginForm onSubmit={login.mutate} isPending={login.isPending} error={login.isError ? errorMessage(login.error) : undefined}/></CardContent></Card></Container>; }
