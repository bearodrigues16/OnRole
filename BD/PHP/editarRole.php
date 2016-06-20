<?php

require "init.php";

$nomeRole = $_POST["nome"];
$data = $_POST["data"];
$custo = $_POST["custo"];
$local = $_POST["local"];
$descricao = $_POST["descricao"];
$idRole = $_POST["id"];

$query = "UPDATE roles SET nomeRole='$nomeRole', data='$data', custo='$custo', local='$local', descricao='$descricao' WHERE idrole = '$idRole';";

mysqli_query($conexao,$query);

//$res = mysqli_query($conexao,$query);
//$response = array();
/*while($row=mysqli_fetch_array($res))
{
 echo $row[1];
 echo ":".$row[2];
 echo ":".$row[3];
 echo ":".$row[4];
 echo ":".$row[5].";";


 //array_push($response,array('nome'=>$row[1],'email'=>$row[2], 'local'=>$row[3], 'descricao'=>$row[4], 'data'=>$row[5]));
}
*/
//echo json_encode(array('result'=>$response));
mysqli_close($conexao);
//echo "Hello world...";



?>﻿