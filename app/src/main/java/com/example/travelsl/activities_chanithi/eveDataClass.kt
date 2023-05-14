package com.example.travelsl.activities_chanithi

//event data class includes all the variables regarding event calendar creation form

class eveDataClass {
    var dataTitleE: String? = null
    var dataDescE: String? = null
    var dataPriorityE: String? = null
    var dataImageE: String? = null

    constructor(dataTitle: String?,dataDesc: String?,dataPriority: String?,dataImage: String?){
        this.dataTitleE = dataTitle
        this.dataDescE = dataDesc
        this.dataPriorityE = dataPriority
        this.dataImageE = dataImage
    }

    constructor(){

    }
}
