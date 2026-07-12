-- Seed default users for all 6 roles with default password 'TransitOps123!'
INSERT INTO user (role_id, first_name, last_name, email, password_hash, active) VALUES
  (1, 'System', 'Admin', 'admin@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE),
  (2, 'Fleet', 'Manager', 'fleet@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE),
  (3, 'Dispatch', 'Coordinator', 'dispatcher@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE),
  (4, 'John', 'Driver', 'driver@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE),
  (5, 'Safety', 'Officer', 'safety@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE),
  (6, 'Finance', 'Officer', 'finance@transitops.local', '$2a$10$kXDOAsKhMoqgRrFWsZhheOqXFzOET5afMP9b2y/CT0xEuLXKIYh22', TRUE);
