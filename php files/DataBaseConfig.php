<?php

class DataBaseConfig
{
    public $servername;
    public $username;
    public $password;
    public $databasename;

    //public function __construct()
    //{

    //    $this->servername = 'sql9.freemysqlhosting.net';
    //    $this->username = 'sql9383861';
    //    $this->password = 'KKIXvwwGFK';
    //    $this->databasename = 'sql9383861';

    //}

    public function __construct()
    {

        $this->servername = 'localhost';
        $this->username = 'root';
        $this->password = '';
        $this->databasename = 'loginregister';

    }
}

?>
