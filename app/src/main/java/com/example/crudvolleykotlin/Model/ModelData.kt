package com.example.crudvolleykotlin.Model

class ModelData {

    var npm: String? = ""
    var nama: String? = ""
    var prodi: String? = ""
    var fakultas: String? = ""

    constructor() {}

    constructor(npm: String, nama: String, prodi : String, fakultas : String) {

        this.npm = npm
        this.nama = nama
        this.prodi = prodi
        this.fakultas = fakultas

    }


}