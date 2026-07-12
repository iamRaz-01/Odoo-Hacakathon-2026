import { Button, Container, Typography } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
export function UnauthorizedPage() { return <Container sx={{ py: 10 }}><Typography variant="h3">Access denied</Typography><Typography sx={{ my: 2 }}>Your role does not grant access to this page.</Typography><Button component={RouterLink} to="/dashboard" variant="contained">Return to dashboard</Button></Container>; }
