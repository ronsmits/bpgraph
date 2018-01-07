package bp.app

import bp.view.datetimepicker
import tornadofx.*

class EntryForm : View("Entry Form") {

    val controller: BpController by inject()

    override val root = form {
        fieldset {
            field("sys") { textfield(controller.editEntry.sys) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("dia") { textfield(controller.editEntry.dia) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("pulse") { textfield(controller.editEntry.pulse) { filterInput { it.controlNewText.isInt() && it.controlNewText.toInt() < 255 } } }
            field("Time") { datetimepicker(controller.editEntry.time) }
            buttonbar {
                button("save").action {
                    println(controller.editEntry.isDirty)
//                    controller.editEntry.commit()
                    close()
                }
            }
        }
    }
}