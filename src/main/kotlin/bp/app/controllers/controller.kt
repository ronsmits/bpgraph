package bp.app.controllers

import bp.app.model.Measurement
import bp.app.model.MeasurementModel
import tornadofx.*

class BpController : Controller() {

    val entries = mutableListOf<Measurement>().observable()
    val selectedEntry = MeasurementModel()
    val editEntry = MeasurementModel()
    fun save(item: Measurement) = entries.add(item)
    fun delete(item: Measurement) = entries.remove(item)



}