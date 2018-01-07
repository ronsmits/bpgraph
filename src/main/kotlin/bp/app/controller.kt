package bp.app

import bp.view.Measurement
import bp.view.MeasurementModel
import tornadofx.*

class BpController : Controller() {

    val entries = mutableListOf<Measurement>().observable()
    val selectedEntry = MeasurementModel()
    val editEntry = MeasurementModel()
    fun save(item: Measurement) = entries.add(item)
    fun delete(item: Measurement) = entries.remove(item)



}