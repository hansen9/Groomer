package com.example.groomer

import java.io.Serializable

class Transaction (
    val userName: String,
    val petName: String,
    val pickupLoc: String,
    val pickupTime: String,
    val petType: String,
    val serviceType: String,
    val price: String
):Serializable