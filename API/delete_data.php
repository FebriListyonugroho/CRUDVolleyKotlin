<?php

    include_once('connection.php');

    $npm = $_POST['npm'];

    $getdata = mysqli_query($koneksi, "SELECT * FROM tb_mahasiswa WHERE npm = '$npm'");
    $rows = mysqli_num_rows($getdata);

    $delete = "DELETE FROM tb_mahasiswa WHERE npm = '$npm'";
    $exedelete = mysqli_query($koneksi, $delete);

    $response = array();
    if($rows > 0){
        if($exedelete){
            $response['code'] = 1;
            $response['message'] = "Hapus Berhasil";
        }else{
            $response['code'] = 0;
            $response['message'] = "Gagal Menghapus";
        }
    }else{
        $response['code'] = 0;
        $response['message'] = "Data Tidak Ada";
    }
    

    echo json_encode($response);

?>