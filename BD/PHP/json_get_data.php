<?php
$host  = "localhost";
$user = "#####";
$pass = "######";
$db = "########";

$con = mysqli_connect($host,$user,$pass,$db);

if(!$con)
{
 die("Error ".mysqli_connect_error());
 
}
else
{
 echo "<h3>connection success</h3>";
 
}

$sql = "select * from product_info;";

$res = mysqli_query($con,$sql);
$response = array();
while($row=mysqli_fetch_array($res))
{
 echo "<br>Name : ".$row[0];
 echo "<br>Email : ".$row[1];
 array_push($response,array('name'=>$row[0],'email'=>$row[1]));
}

echo json_encode(array('result'=>$response));
mysqli_close($con);
//echo "Hello world...";



?>﻿