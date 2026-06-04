<?php
require_once __DIR__ . '/../config/IbtissamDatabase.php';

class IbtissamContactService {
    private $conn;
    private $table = "ibtissam_contacts";

    public function __construct() {
        $database = new IbtissamDatabase();
        $this->conn = $database->getConnection();
    }

    public function insert($ibtissam_name, $ibtissam_phone, $ibtissam_source = "mobile_ibtissam") {
        $sql = "INSERT INTO " . $this->table . " (ibtissam_name, ibtissam_phone, ibtissam_source)
                VALUES (:name, :phone, :source)";
        $stmt = $this->conn->prepare($sql);
        return $stmt->execute([
            ':name' => $ibtissam_name,
            ':phone' => $ibtissam_phone,
            ':source' => $ibtissam_source
        ]);
    }

    public function getAll() {
        $sql = "SELECT * FROM " . $this->table . " ORDER BY ibtissam_name ASC";
        $stmt = $this->conn->prepare($sql);
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function search($ibtissam_keyword) {
        $sql = "SELECT * FROM " . $this->table . "
                WHERE ibtissam_name LIKE :keyword OR ibtissam_phone LIKE :keyword
                ORDER BY ibtissam_name ASC";
        $stmt = $this->conn->prepare($sql);
        $stmt->execute([
            ':keyword' => '%' . $ibtissam_keyword . '%'
        ]);
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>
