import { Dialog, DialogContent, DialogTitle } from '@mui/material';
import type { Role, User } from '../../types/auth';
import { UserForm } from './UserForm';
import type { UserValues } from './userSchema';
interface Props { open: boolean; user?: User; roles: Role[]; pending: boolean; error?: string; onClose: () => void; onSubmit: (values: UserValues) => void; }
export function UserDialog({ open, user, roles, pending, error, onClose, onSubmit }: Props) { return <Dialog open={open} onClose={pending ? undefined : onClose} fullWidth maxWidth="sm"><DialogTitle>{user ? 'Edit user' : 'Create user'}</DialogTitle><DialogContent><UserForm user={user} roles={roles} pending={pending} error={error} onSubmit={onSubmit}/></DialogContent></Dialog>; }
