# Sistema-de-Gerenciamento-de-Ouvidoria

Este Projeto é um sistema web desenvolvido para gerenciar manifestações em uma ouvidoria, sendo possivel cadastrar elogio, reclamação e sugestão. Ele permite deletar, criar, listar e listar por tipo as manifestações.

## Tecnologia Ultilizadas

- JAVA 17
- SPRING BOOT
- SPRING WEB
- SPRING DATA JPA
- MySQL
- LOMBOK

## Pré requisito

- java 17 instalado
- MySQL instalado e em execução
- Mavem instalado(ou uma IDE como itellij ou eclipse)

## Como executar o projeto

 1 - Crie a tabela no Mysql.
 
	 create table Registro(
		codigo int auto_increment,
		tipo varchar(100),
	    manifestacao varchar(200),
	    autor varchar(20),
	    criado_em date,
	    primary key(codigo)
	);

2 - Abra o projeto Maven no intellij.

## Requisições

GET http://localhost:8080/listar 

	Lista todas as manifestações.

POST http://localhost:8080/criar

	{
	    "manifestacao":"helen",
	    "autor":"exemplo",
	    "tipo":"reclamacao"
	}
	Cria manifestação e retorna o codigo.

GET http://localhost:8080/listarPorTipo/elogio

	Lista por tipo a manifestação.
 

DEL http://localhost:8080/deletar/{codigo}

	Deleta uma manifestação e retorna o registro interio apagado. 

# AUTOR

 - Nome: Helen Luiza Alves Pereira.
 - Curso: Análise e Desenvolvimento de Sistema.
 - Instituição: Unifacisa
 - Data: 31/05/2025
