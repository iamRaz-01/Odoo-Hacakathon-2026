export interface Role { id: number; name: string; description: string | null; createdAt: string; updatedAt: string; }
export interface User { id: number; firstName: string; lastName: string; email: string; active: boolean; role: Role; createdAt: string; updatedAt: string; }
export interface LoginResponse { accessToken: string; refreshToken: string; tokenType: string; expiresIn: number; user: User; }
export interface JwtResponse { accessToken: string; refreshToken: string; tokenType: string; expiresIn: number; }
