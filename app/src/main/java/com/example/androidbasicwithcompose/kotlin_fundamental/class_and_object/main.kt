package com.example.androidbasicwithcompose.kotlin_fundamental.class_and_object

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * This is superclass inheritable
 */
open class SmartDevice(
    /**
     * Property name SmartDevice
     */
    val name: String,
    /**
     * Property category SmartDevice
     */
    val category: String
) {

    /**
     * Property deviceStatus
     *
     * will be filled in the value online or offline
     */
    var deviceStatus = "online"

    /**
     * deviceType This property inheritable
     */
    open val deviceType: String = "unknown"

    /**
     * turnOn() method is inheritable
     *
     * this gives an behaviour to reassign property deviceStatus to On
     */
    open fun turnOn() {
        deviceStatus = "on"
    }

    /**
     * turnOff() method is inheritable
     *
     * this gives an behaviour to reassign property deviceStatus to Off
     */
    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}


/**
 * SmartTvDevice class is Subclass owned by SuperClass SmartDevice
 */
class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    var stateDevice = ""

    /**
     * Inherited property call from SmartDevice() class
     *
     * And reassign the value
     */
    override val deviceType: String = "Smart TV"

    /**
     * This property belongs to the SmartTvDevice() class
     *
     * Pure property belongs SmartTvDevice()
     *
     * And delegate object RangeRegulatorClass() to get return value
     */
    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    /**
     * method inherited from SmartDevice() class
     *
     * and change its value method
     */
    override fun turnOn() {
        super.turnOn()
        stateDevice = deviceStatus
        println(
            "$name is turned $stateDevice. Speaker volume set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        stateDevice = deviceStatus
        println("$name turned $stateDevice")
    }

    fun increaseSpeakerVolume() {

        speakerVolume++
        checkStateDevice(speakerVolume, {
            println("Speaker volume increased to $it.")
        }, "volume")

    }

    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        checkStateDevice(channelNumber, {
            println("Channel number increased to $it.")
        }, "next channel")
    }

    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber.")
    }


    fun checkStateDevice (increatedValue: Int,  message: (increatedValue: Int) -> Unit, itemIncreased: String) {
        when (stateDevice) {
            "on" -> {
                message(increatedValue)
            }
            "off" -> {
                println("Please turn on the TV if you want to increased the $itemIncreased")
            }
            else -> {
                println("Salah Ngoding di area line 136")
            }
        }
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType: String = "Smart Light"
    private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name is turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off")
    }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
    }
}

class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    val smartHome = SmartHome(
        SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
        SmartLightDevice(deviceName = "Google light", deviceCategory = "Utility")
    )

    smartHome.turnOnTv()
//    smartHome.turnOnLight()
//    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
//    println()
//
    smartHome.increaseTvVolume()
    smartHome.changeTvChannelToNext()
//    smartHome.increaseLightBrightness()
//    println()
//
//    smartHome.turnOffAllDevices()
//    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}.")
}