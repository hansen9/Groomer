package com.example.groomer

interface Communicator {
    fun passDataCom(
        petName: String,
        pickupLoc: String,
        pickupTime: String,
        petType: String,
        serviceType: String
    )

}