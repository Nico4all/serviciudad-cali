CREATE TABLE IF NOT EXISTS facturas_acueducto (
    id SERIAL PRIMARY KEY,
    id_cliente VARCHAR(20) NOT NULL,
    periodo VARCHAR(10),
    consumo_m3 INT,
    valor_pagar DECIMAL(12,2)
);

INSERT INTO facturas_acueducto (id_cliente, periodo, consumo_m3, valor_pagar) VALUES
('0001234567', '202510', 15, 95000.00),
('0009876543', '202510', 20, 115000.00),
('0001122334', '202510', 10, 72000.00);