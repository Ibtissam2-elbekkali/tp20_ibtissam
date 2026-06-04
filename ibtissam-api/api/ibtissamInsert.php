<?php
header("Content-Type: application/json");
require_once __DIR__ . '/../service/IbtissamContactService.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $data = json_decode(file_get_contents("php://input"), true);

    if (!isset($data['ibtissam_name']) || !isset($data['ibtissam_phone'])) {
        echo json_encode([
            "success" => false,
            "message" => "Champs manquants pour Ibtissam"
        ]);
        exit;
    }

    $service = new IbtissamContactService();
    $ok = $service->insert($data['ibtissam_name'], $data['ibtissam_phone'], "mobile_ibtissam");

    echo json_encode([
        "success" => $ok,
        "message" => $ok ? "Contact d'Ibtissam inséré avec succès" : "Erreur d'insertion pour Ibtissam"
    ]);
}
?>
