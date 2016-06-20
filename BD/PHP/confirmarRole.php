<?php

require "init.php";

$idRole = $_POST["idRole"];
$idUsuario = $_POST["idUsuario"];

$query = "INSERT INTO confirma_em (idUsuario, idRole) VALUES ('$idUsuario', '$idRole');";

$res = mysqli_query($conexao,$query);


mysqli_close($conexao);



?>﻿