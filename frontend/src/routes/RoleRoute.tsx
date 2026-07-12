import { Navigate, Outlet } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
export function RoleRoute({ roles }: { roles: string[] }) { const role = useAuthStore((state) => state.user?.role.name); return role && roles.includes(role) ? <Outlet/> : <Navigate to="/unauthorized" replace/>; }
