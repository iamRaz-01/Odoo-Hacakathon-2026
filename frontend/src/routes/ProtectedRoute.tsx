import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
export function ProtectedRoute() { const token = useAuthStore((state) => state.accessToken); const location = useLocation(); return token ? <Outlet/> : <Navigate to="/login" replace state={{ from: location.pathname }}/>; }
