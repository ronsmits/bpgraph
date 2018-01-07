package bp.app

import bp.view.MainView
import bp.view.Measurement
import bp.view.MeasurementModel
import bp.view.datetimepicker
import tornadofx.*

class BpWorkspace : Workspace() {
    val controller: BpController by inject()
    override fun onDock() {
        dock<MainView>()
    }

    init {
        refreshButton.hide()
        forwardButton.hide()
        backButton.hide()
    }

    override fun onSave() {
        println("save called")
    }

    override fun onCreate() {
        measuremenEditDialog(Measurement()) {
            controller.save(it)
        }
    }

    fun measuremenEditDialog(item: Measurement, op: (item: Measurement) -> Unit) {
        val entry = MeasurementModel()
        entry.item = item
        dialog("edit entry") {
            field("sys") { textfield(entry.sys) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("dia") { textfield(entry.dia) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("pulse") { textfield(entry.pulse) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("Time") { datetimepicker(entry.time) }
            buttonbar {
                button("cancel").action { close() }
                button("save").action {
                    entry.commit()
                    op(entry.item)
                    close()
                }
            }
        }
    }
}