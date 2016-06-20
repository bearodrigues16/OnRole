<?php

require "init.php";

$email = $_POST["email"];
$senha = $_POST["senha"];

$query = "SELECT id FROM usuario WHERE email like '$email' AND senha like '$senha'";

$resultado = mysqli_query($conexao, $query);
//$res = array();

$row = mysqli_fetch_array($resultado);
echo $row[0];

//array_push($res, array('id' => $row[0]));
//echo json_encode(array('id' => $resultado));

mysqli_close($conexao);

/*if(mysqli_num_rows($resultado) > 0) {
	//$row = mysqli_fetch_assoc ($resultado);
	echo 'Login efetuado com sucesso';
} else {
	echo 'Falha ao realizar login';
}
*/
?>