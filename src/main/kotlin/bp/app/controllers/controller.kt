package bp.app.controllers

import bp.app.model.Measurement
import bp.app.model.MeasurementModel
import javafx.collections.FXCollections
import tornadofx.*

class BpController : Controller() {

    val entries = FXCollections.observableArrayList<Measurement>()
    val selectedEntry = MeasurementModel()
    val editEntry = MeasurementModel()

    fun save(item: Measurement) = entries.add(item)

    fun delete(item: Measurement) = entries.remove(item)

    fun update(original: Measurement, item: Measurement) {
        entries.forEachIndexed { index, measurement -> if (measurement == original) {
            entries[index] = item
        }
        }
    }


}