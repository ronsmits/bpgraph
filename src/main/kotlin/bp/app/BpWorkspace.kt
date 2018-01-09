package bp.app

import bp.app.controllers.BpController
import bp.app.model.Measurement
import bp.app.model.MeasurementModel
import bp.view.MainView
import bp.view.datetimepicker
import javafx.stage.FileChooser
import tornadofx.*
import java.io.FileInputStream

class BpWorkspace : Workspace() {
    val controller: BpController by inject()
    override fun onDock() {
        dock<MainView>()
    }

    init {
        button("load").action {
            val result = chooseFile(title = "load json data  file", mode = FileChooserMode.Single, filters = arrayOf(FileChooser.ExtensionFilter("json file", "*.json")))
            if (result.isNotEmpty()) {
                val loaded = loadJsonArray(FileInputStream(result[0]))
                controller.entries.setAll(loaded.toModel())
            }
        }
        refreshButton.hide()
        forwardButton.hide()
        backButton.hide()
    }

    override fun onSave() {
        println("save called")
        val result = chooseFile(title = "save as json data", mode = FileChooserMode.Save, filters = arrayOf(FileChooser.ExtensionFilter("json file", "*.json")))

        val toJSON = controller.entries.toJSON()
        println(toJSON.toPrettyString())
    }

    override fun onCreate() {
        measuremenEditDialog(Measurement()) {
            controller.save(it)
        }
    }

    override fun onDelete() {
        controller.delete(controller.selectedEntry.item)
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
