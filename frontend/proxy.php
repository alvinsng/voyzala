<?php
$url = "http://voyzala.appspot.com/".$_GET['script']."?";
$data = $_GET;
unset($data['script']);
$url .= http_build_query($data);
header("Content-type: text/json");
echo file_get_contents($url);