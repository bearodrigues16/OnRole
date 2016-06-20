<?php
	
require "init.php";

$nome = $_POST["nome"];
$email = $_POST["email"];
$senha = $_POST["senha"];

//Executar uma query
$query = "INSERT INTO usuario(nome, email, senha) VALUES ('$nome', '$email', '$senha');";

//echo $query;

if(mysqli_query($conexao, $query)) {
	echo "<br>Usuário cadastrado com sucesso!";
} else {
    echo "Erro";
}

?>