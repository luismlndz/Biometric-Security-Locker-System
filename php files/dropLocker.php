<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['locker_id']) && isset($_POST['username'])) {
    if ($db->dbConnect()) {
        if ($db->checkLocker("lockers", $_POST['locker_id'], $_POST['username'])) {
            $db->dropLocker("lockers", $_POST['locker_id'], $_POST['username']);
            $db->dropLocker_id("users", $_POST['locker_id'], $_POST['username']);
            echo "Drop Success";
        } else echo "You do not own this locker";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>