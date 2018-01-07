package bp.view

import bp.app.controllers.BpController
import bp.app.model.Measurement
import tornadofx.*
import java.time.format.DateTimeFormatter

class TableView : View("Table View") {
    val controller: BpController by inject()

    override val root = tableview(controller.entries) {
        prefWidth = 400.0
        columnResizePolicy = SmartResize.POLICY
        bindSelected(controller.selectedEntry)
        column("Date", Measurement::time).cellFormat {
            text = String.format(it.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
        }
        column("Sys", Measurement::sys)
        column("Dia", Measurement::dia)
        column("Pulse", Measurement::pulse)
    }
}