<?php

require "init.php";

$id = $_POST["id"];

$query = "SELECT * FROM roles WHERE idrole NOT IN (SELECT idRole FROM confirma_em WHERE idUsuario = '$id');";

$res = mysqli_query($conexao,$query);
//$response = array();
while($row=mysqli_fetch_array($res))
{
 echo $row[0];
 echo ":".$row[1];
 echo ":".$row[2];
 echo ":".$row[3];
 echo ":".$row[4];
 echo ":".$row[5];
 echo ":".$row[6].";";


 //array_push($response,array('nome'=>$row[1],'email'=>$row[2], 'local'=>$row[3], 'descricao'=>$row[4], 'data'=>$row[5]));
}

//echo json_encode(array('result'=>$response));
mysqli_close($conexao);
//echo "Hello world...";



?>﻿