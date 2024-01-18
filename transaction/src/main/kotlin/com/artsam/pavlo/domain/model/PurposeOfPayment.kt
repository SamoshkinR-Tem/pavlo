package com.artsam.pavlo.domain.model

import android.net.Uri
import java.util.Date

data class PurposeOfPayment(
    val title: String,
    val operationCode: Int,
    val caseNumber: Int,
    val caseDate: Date,
    val basisDocuments: List<Uri>
)
