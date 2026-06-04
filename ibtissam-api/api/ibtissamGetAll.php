<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../service/IbtissamContactService.php';

$service = new IbtissamContactService();
$result = $service->getAll();

echo json_encode($result);
?>
