CREATE TABLE IF NOT EXISTS cliente (
	id serial PRIMARY KEY,
	nome varchar(50),
	cpf varchar(11)
);

CREATE TABLE IF NOT EXISTS produto(
  idproduto serial PRIMARY KEY,
  nome VARCHAR(45) NOT NULL,
  preco FLOAT NOT NULL,
  categoria VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS imagens (
  idimagens serial PRIMARY KEY,
  imagem_nome VARCHAR(45) NULL,
  produto_idproduto INT NOT NULL,

  CONSTRAINT fk_imagens_produto
    FOREIGN KEY (produto_idproduto)
    REFERENCES produto (idproduto)
    ON DELETE CASCADE
    );
    


