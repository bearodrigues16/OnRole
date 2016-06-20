<?php

//Variáveis para conexão
/*
$host = 'ggaribeiro.hopto.org';
$nomeUsuario = 'root';
$senhaBanco = '102030';
$nomeBanco = 'search_on_role';
$porta = '3307';
*/

$host = 'www2.bcc.unifal-mg.edu.br';
$nomeUsuario = 'a14014';
$senhaBanco = 'a14014';
$nomeBanco = 'a14014';

//Conectando com o banco
$conexao = mysqli_connect($host, $nomeUsuario, $senhaBanco, $nomeBanco);
//if (!$conexao)
    //die("Erro de conexão: " . mysql_error());
//else
	//echo "Conectado com sucesso ao BD: " . $nomeBanco;


?> 