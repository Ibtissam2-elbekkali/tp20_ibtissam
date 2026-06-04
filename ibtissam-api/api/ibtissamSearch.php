<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../service/IbtissamContactService.php';

if (!isset($_GET['ibtissam_keyword'])) {
    echo json_encode([]);
    exit;
}

$ibtissam_keyword = $_GET['ibtissam_keyword'];

$service = new IbtissamContactService();
$result = $service->search($ibtissam_keyword);

echo json_encode($result);
?>
