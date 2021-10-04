<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (fullname, username, password, email) VALUES ('" . $fullname . "','" . $username . "','" . $password . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function lockerSelection($table, $locker_id, $availability)
    {
        $locker_id = $this->prepareData($locker_id);
        $availability = $this->prepareData($availability);
        $this->sql = "select * from " . $table . " where locker_id = '" . $locker_id . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dblocker_id = $row['locker_id'];
            $dbavailability = $row['availability'];
            if ($dblocker_id == $locker_id && $dbavailability == 'N') {
                $lockerSelection = false;
            } else $lockerSelection = true;
        } else $lockerSelection = true;

        return $lockerSelection;
    }

    function rentConfirmation($table, $locker_id, $username, $availability)
    {
        $locker_id = $this->prepareData($locker_id);
        $username = $this->prepareData($username);
        $availability = $this->prepareData($availability);
        $timestamp = date('Y-m-d g:i:s a');
        $this->sql =
            "INSERT INTO " . $table . " (locker_id, username, availability, time_of_purchase) VALUES ('" . $locker_id . "','" . $username . "','" . $availability . "','" . $timestamp . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addLocker_id($table, $locker_id, $username)
    {
        $locker_id = $this->prepareData($locker_id);
        $username = $this->prepareData($username);
        $this->sql =
            "UPDATE " . $table . " SET locker_id = '" . $locker_id . "' WHERE username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
    }

    function dropLocker_id($table, $locker_id, $username)
    {
        $locker_id = $this->prepareData($locker_id);
        $username = $this->prepareData($username);
        $this->sql =
            "UPDATE " . $table . " SET locker_id = NULL WHERE username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
    }

    function checkLocker($table, $locker_id, $username)
    {
        $locker_id = $this->prepareData($locker_id);
        $username = $this->prepareData($username);
        $this->sql = "SELECT * FROM " . $table . " WHERE locker_id = '" . $locker_id . "' AND username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        $dblocker_id = $row['locker_id'];
        $dbusername = $row['username'];

        if ($dblocker_id == $locker_id && $dbusername == $username) {

             return true;

        } else return false;

    }

    function dropLocker($table, $locker_id, $username)
    {
        $locker_id = $this->prepareData($locker_id);
        $username = $this->prepareData($username);
        $this->sql = "DELETE FROM " . $table . " WHERE locker_id = '" . $locker_id . "' AND username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
    }


}

?>
