<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['locker_id']) && isset($_POST['username']) && isset($_POST['availability'])) {
    if ($db->dbConnect()) {
        if ($db->rentConfirmation("lockers", $_POST['locker_id'], $_POST['username'], $_POST['availability'])) {
            $db->addLocker_id("users", $_POST['locker_id'], $_POST['username']);
            echo "Locker Registration Success";
        } else echo "Locker Registration Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
