import { CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { lazy, Suspense } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import { AppLayout } from '../layouts/AppLayout';
import { ProtectedRoute } from '../routes/ProtectedRoute';
import { RoleRoute } from '../routes/RoleRoute';
import { UnauthorizedPage } from '../pages/UnauthorizedPage';
const LoginPage = lazy(() => import('../features/authentication/LoginPage').then((module) => ({ default: module.LoginPage })));
const DashboardPage = lazy(() => import('../pages/DashboardPage').then((module) => ({ default: module.DashboardPage })));
const UsersPage = lazy(() => import('../features/users/UsersPage').then((module) => ({ default: module.UsersPage })));
const queryClient = new QueryClient({ defaultOptions: { queries: { retry: 1, refetchOnWindowFocus: false } } });
const theme = createTheme({ colorSchemes: { light: true, dark: true }, cssVariables: true });
export function App() { return <ThemeProvider theme={theme}><CssBaseline/><QueryClientProvider client={queryClient}><BrowserRouter><Suspense fallback={<div>Loading…</div>}><Routes><Route path="/login" element={<LoginPage/>}/><Route element={<ProtectedRoute/>}><Route element={<AppLayout/>}><Route path="/dashboard" element={<DashboardPage/>}/><Route element={<RoleRoute roles={['ADMIN']}/>}><Route path="/users" element={<UsersPage/>}/></Route></Route></Route><Route path="/unauthorized" element={<UnauthorizedPage/>}/><Route path="*" element={<Navigate to="/dashboard" replace/>}/></Routes></Suspense></BrowserRouter></QueryClientProvider></ThemeProvider>; }
