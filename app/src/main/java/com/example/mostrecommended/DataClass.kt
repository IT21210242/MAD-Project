package com.example.mostrecommended

//All variable names regarding the visiting places creation form

class DataClass {
    var dataID: String? = null
    var dataTitle: String? = null
    var dataDesc: String? = null
    var dataPriority: String? = null
    var dataImage: String? = null

    constructor(dataID:String, dataTitle: String?,dataDesc: String?,dataPriority: String?,dataImage: String?){
        this.dataID = dataID
        this.dataTitle = dataTitle
        this.dataDesc = dataDesc
        this.dataPriority = dataPriority
        this.dataImage = dataImage
    }

    constructor(){

    }
}