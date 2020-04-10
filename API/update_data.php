<?php

    include_once('connection.php');

    $npm = $_POST['npm'];

    $nama = $_POST['nama'];
    $prodi = $_POST['prodi'];
    $fakultas = $_POST['fakultas'];

    $getdata = mysqli_query($koneksi, "SELECT * FROM tb_mahasiswa WHERE npm = '$npm'");
    $rows = mysqli_num_rows($getdata);

    
    if($rows > 0){
        $query = "UPDATE tb_mahasiswa SET nama = '$nama', prodi = '$prodi', fakultas = '$fakultas' WHERE npm = '$npm'";

        $exequery = mysqli_query($koneksi, $query);

        if($exequery){
            $response['code'] = 1;
            $response['message'] = "Data Berhasil Diupdate";
        }else{
            $response['code'] = 0;
            $response['message'] = "Data Gagal Diupdate";
        }

    }else{
        $response['code'] = 0;
        $response['message'] = "Data Yang Diupdate Tidak Ada";
    }
    echo json_encode($response);

?>