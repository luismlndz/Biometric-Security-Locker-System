<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['locker_id']) && isset($_POST['availability'])) {
    if ($db->dbConnect()) {
        if ($db->lockerSelection("lockers", $_POST['locker_id'], $_POST['availability'])) {
            echo "Locker Available";
        } else echo "Locker Unavailable";
    } else echo "Error: Database connection";
} else echo "Locker ID required";
?>