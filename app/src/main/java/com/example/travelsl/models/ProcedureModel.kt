package com.example.travelsl.models

data class ProcedureModel(
    val procedureId : String? = null,
    val procedureName : String? = null,
    val procedureMedCenter: String? = null,
    val procedureMedType : String? = null,
    val procedureRangeUpper : String? = null,
    val procedureRangeLower : String? = null
)
