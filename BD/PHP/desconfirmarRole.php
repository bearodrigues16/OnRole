<?php

require "init.php";

$idRole = $_POST["idRole"];
$idUsuario = $_POST["idUsuario"];

$query = "DELETE FROM confirma_em WHERE idRole = '$idRole' AND idUsuario = '$idUsuario';";

$res = mysqli_query($conexao,$query);


mysqli_close($conexao);



?>﻿