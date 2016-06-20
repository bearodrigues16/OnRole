<?php
	
require "init.php";

$nomeRole = $_POST["nome"];
$data = $_POST["data"];
$custo = $_POST["custo"];
$local = $_POST["local"];
$descricao = $_POST["descricao"];
$origID = $_POST["id"];

//Executar uma query
$query = "INSERT INTO roles(nomeRole, data, custo, local, descricao, origID) VALUES ('$nomeRole', '$data', '$custo', '$local', '$descricao', '$origID');";
$idrole = "SELECT idrole FROM roles WHERE nomeRole LIKE '$nomeRole';";

//echo $query;

if(mysqli_query($conexao, $query)) {
	$row = mysqli_fetch_array(mysqli_query($conexao, $idrole));
	$query2 = "INSERT INTO confirma_em(idUsuario, idRole) VALUES ('$origID', '$row[0]');";
	mysqli_query($conexao, $query2);
	echo "<br>Rolê cadastrado com sucesso!";
} else {
    echo "Erro";
}

?>