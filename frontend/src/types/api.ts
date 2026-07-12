export interface ApiResponse<T> { success: boolean; message: string; timestamp: string; data: T; }
export interface ApiError { success: false; errorCode: string; message: string; timestamp: string; }
